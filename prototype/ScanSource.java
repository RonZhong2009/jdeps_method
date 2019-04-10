package ronzhong.stubgenerator.scansourcecode;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistMethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionMethodDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ScanSource {
    static FileWriter fr = null;
    private static CombinedTypeSolver comdepTypeSolver=null;
    private static ClassLoaderTypeSolver classloaderdepTypeSolver=null;
    private static JavaParserTypeSolver javaparserdepTypeSolver=null;
    private static JarTypeSolver depjarTypeSolver=null;
	
	private static void testjavaparser() {
        // SourceRoot is a tool that read and writes Java files from packages on a certain root directory.
        // In this case the root directory is found by taking the root from the current Maven module,
        // with src/main/resources appended.
		Path destipath = CodeGenerationUtils.mavenModuleRoot(ScanSource.class).resolve(Paths.get("output"));
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(ScanSource.class).resolve("src/main/resources"));

        // Our sample is in the root of this directory, so no package name.
        CompilationUnit cu = sourceRoot.parse("", "testjava.java");
        
        File file = new File(destipath.toString()+File.separator+ "Modified.java");
        
        try {
        	fr = new FileWriter(file);
        	depjarTypeSolver= new JarTypeSolver("test.jar");
        	classloaderdepTypeSolver= new ClassLoaderTypeSolver(ScanSource.class.getClassLoader());
        	comdepTypeSolver = new CombinedTypeSolver();
        	javaparserdepTypeSolver = new JavaParserTypeSolver("./src");
        	comdepTypeSolver.add(depjarTypeSolver);
        	comdepTypeSolver.add(classloaderdepTypeSolver);
        	comdepTypeSolver.add(javaparserdepTypeSolver);
        } catch (IOException e) {
	        e.printStackTrace();
	    }
       
        
        cu.accept(new ModifierVisitor<Void>() {
            /**
             * For every if-statement, see if it has a comparison using "!=".
             * Change it to "==" and switch the "then" and "else" statements around.
             */
            @Override
            public Visitable visit(IfStmt n, Void arg) {
                // Figure out what to get and what to cast simply by looking at the AST in a debugger! 
                n.getCondition().ifBinaryExpr(binaryExpr -> {
                    if (binaryExpr.getOperator() == BinaryExpr.Operator.NOT_EQUALS && n.getElseStmt().isPresent()) {
                        /* It's a good idea to clone nodes that you move around.
                            JavaParser (or you) might get confused about who their parent is!
                        */
                        Statement thenStmt = n.getThenStmt().clone();
                        Statement elseStmt = n.getElseStmt().get().clone();
                        n.setThenStmt(elseStmt);
                        n.setElseStmt(thenStmt);
                        binaryExpr.setOperator(BinaryExpr.Operator.EQUALS);
                    }
                });
                return super.visit(n, arg);
            }
            
//            @Override
//            public Visitable visit(MethodDeclaration n, Void arg) {
//            	n.setBody(new BlockStmt()); // make it to be stub function
//                return super.visit(n, arg);
//            }
//            
            @Override
            public Visitable visit(MethodCallExpr n, Void arg) {
            	try {
            	SymbolReference<ResolvedMethodDeclaration> resMethodDecl = JavaParserFacade.get(comdepTypeSolver)
                        .solve(n);
            	System.out.print("METHOD_raw DECLARATIONs:"+resMethodDecl.toString()+"\n");
            	if(resMethodDecl.isSolved()) { 	           	
            		System.out.print("METHOD_DECLARATIONs:"+resMethodDecl.getCorrespondingDeclaration().toString()+"\n");
            		ResolvedMethodDeclaration resolvedmthddec = resMethodDecl.getCorrespondingDeclaration();
            		if(resolvedmthddec instanceof JavassistMethodDeclaration) {//means come from jar file
            			System.out.print("METHOD_DECLARATIONs:from jar"+((JavassistMethodDeclaration) resMethodDecl.getCorrespondingDeclaration()).declaringType().toString()+"\n");
            		}else if(resolvedmthddec instanceof JavaParserMethodDeclaration)  {//means come from java code
            			MethodDeclaration methoddecl = ((JavaParserMethodDeclaration) resolvedmthddec).getWrappedNode() ;
            			System.out.print("METHOD_DECLARATIONs:method:"+methoddecl.getDeclarationAsString()+"\n");
            			System.out.print("METHOD_DECLARATIONs:class:"+methoddecl.toString()+"\n");
            		}else if(resolvedmthddec instanceof ReflectionMethodDeclaration) { //means come from classloader
            			System.out.print("METHOD_DECLARATIONs:from classloader:"+resolvedmthddec.toString()+"\n");
            		}
            	}
            	else
            		System.out.print("METHOD_DECLARATIONs: unsport corresponding:"+resMethodDecl.toString()+"\n");
            	}
            	catch(UnsolvedSymbolException e) {
//                	System.out.print("warnning: got unsovled methoddecls:"+ e.getMessage() +"\n");
            	}

//            	System.out.print("what we got methodcalls:"+n.toString()+"\n");
            	return super.visit(n, arg);
            }
        }, null);
        
        
//        cu.accept(new ModifierVisitor<Void>() {
//            /**
//             * For every if-statement, see if it has a comparison using "!=".
//             * Change it to "==" and switch the "then" and "else" statements around.
//             */
//            @Override
//            public Visitable visit(MethodDeclaration n, Void arg) {
//            	n.setBody(new BlockStmt()); // make it to be stub function
//                return super.visit(n, arg);
//            }
//        }, null);   
        
        
        try {
            fr.write(cu.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        //close resources
	        try {
	            fr.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
        
        //System.out.print("what we got imports:"+cu.getImports().toString()+"\n");

        //add what we need into CompilationUnit, then save into the output folder
//        dstcu.setImports(cu.getImports());
//        destiRoot.saveAll();

//        // This saves all the files we just read to an output directory.  
//        sourceRoot.saveAll(
//                // The path of the Maven module/project which contains the ScanSource class.
//                CodeGenerationUtils.mavenModuleRoot(ScanSource.class)
//                        // appended with a path to "output"
//                        .resolve(Paths.get("output")));
    }
	
		
	
    public static void main(String[] args) throws IOException {
//    	testscancallerjavaparser();//scan the call java, then save the import parckage into map---> find the files, to replace it,
    	testjavaparser();
    }

}
