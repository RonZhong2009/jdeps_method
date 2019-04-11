package ronzhong.stubgenerator.scansourcecode;

import com.github.javaparser.ParseResult;
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
import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.ProjectRoot;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ScanSource {
    static FileWriter fr = null;
    private static CombinedTypeSolver comdepTypeSolver=null;
    private static ClassLoaderTypeSolver classloaderdepTypeSolver=null;
    private static JavaParserTypeSolver javaparserdepTypeSolver=null;
    private static JarTypeSolver depjarTypeSolver=null;
    private static HashMap<String, ArrayList<String>> depsymbols = new HashMap<String, ArrayList<String>>();  
	
    public static void main(String[] args) throws Exception {

        //write all the invoked externnal symbols into a file
		Path destipath = CodeGenerationUtils.mavenModuleRoot(ScanSource.class).resolve(Paths.get("output"));
        File file = new File(destipath.toString()+File.separator+ "Modified.java");
        fr = new FileWriter(file);
        
    	for(SourceRoot sr: getAllSourceRoots()) {
    		testonesourceroot(sr.getRoot());
    	}
    	
        try {
        	for(String key: depsymbols.keySet()) {
        		fr.write(key+"-----\n");
        		for(String item:depsymbols.get(key) ) {
        			fr.write("=="+item+"\n");
        		}
        	}
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
    	
    };

    public static List<SourceRoot> getAllSourceRoots() throws Exception{
        // the source root must input the java dir, it mays the dir attribute should be start with {javaidentifier}
        // that's why here ProjectRoot developed for reventing the wheel each time.
        // and attention that: if setup the path into the SourceRoot, and also want to resolve the symbols, at least you should
        // deeper or equal to the level of the path of SourceRoot, otherwise the local symbols can't be resolved normally.
    	if(srcrootlist == null) {
		ProjectRoot projectRoot = new SymbolSolverCollectionStrategy().collect(Paths.get("E:\\my_git_repo\\java_parser\\jdeps_method\\prototype"));
		srcrootlist = projectRoot.getSourceRoots();
    	}
		return srcrootlist;
    	
    }
    
    public static void getTypeSolver() throws Exception{
    	//jar files is the target jar file which we want to figure out what the methods source code invoked in it.
    	depjarTypeSolver= new JarTypeSolver("E:\\my_git_repo\\java_parser\\jdeps_method\\prototype\\lib\\dependent.jar");
    	//to reso;ve some sytem symbols, for instance, String, File, etc. these types are defined in the system jars.
        classloaderdepTypeSolver= new ClassLoaderTypeSolver(ScanSource.class.getClassLoader());
    	comdepTypeSolver = new CombinedTypeSolver();
        //add all the source files which the parsing source file may reference to, even parsing source file and the referenced files may in the same location.
    	for(SourceRoot sr: getAllSourceRoots()) {
    		comdepTypeSolver.add(new JavaParserTypeSolver(sr.getRoot()));
    	}
    	comdepTypeSolver.add(depjarTypeSolver);
    	comdepTypeSolver.add(classloaderdepTypeSolver);
    }
    
    public static void testonesourceroot(Path path) throws Exception{    	   	
		SourceRoot sourceRoot = null; 
		getTypeSolver();
        List<ParseResult<CompilationUnit>> culist = null;
        try {
    		sourceRoot = new SourceRoot(path);
            culist = sourceRoot.tryToParse();
        } catch (IOException e) {
	        e.printStackTrace();
	    }

        for(ParseResult<CompilationUnit> item: culist) {
        	item.getResult().get().accept(new ModifierVisitor<Void>() {
               
                @Override
                public Visitable visit(MethodCallExpr n, Void arg) {
                	try {
                	SymbolReference<ResolvedMethodDeclaration> resMethodDecl = JavaParserFacade.get(comdepTypeSolver)
                            .solve(n);
//                	System.out.print("METHOD_raw DECLARATIONs:"+resMethodDecl.toString()+"\n");
                	if(resMethodDecl.isSolved()) { 	           	
//                		System.out.print("METHOD_DECLARATIONs:"+resMethodDecl.getCorrespondingDeclaration().toString()+"\n");
                		ResolvedMethodDeclaration resolvedmthddec = resMethodDecl.getCorrespondingDeclaration();
                		if(resolvedmthddec instanceof JavassistMethodDeclaration) {//means come from jar file
                			//add a filter, fileter out class name includes "com.ronzhong"
                			
                			JavassistMethodDeclaration jmd = (JavassistMethodDeclaration) resMethodDecl.getCorrespondingDeclaration();
                			String classSymbol= jmd.declaringType().getQualifiedName();
                			if(classSymbol.matches(".*com\\.ronzhong.*")) {
//                				System.out.print("METHOD_DECLARATIONs:from jar class:"+jmd.declaringType().getQualifiedName()+"\n");
//                				System.out.print("METHOD_DECLARATIONs:from getQualifiedName method:"+jmd.getQualifiedName() +"\n");
//                				System.out.print("METHOD_DECLARATIONs:from getSignature method:"+jmd.getSignature() +"\n");
                				String methodSymbol = jmd.getQualifiedSignature();
                				System.out.print("METHOD_DECLARATIONs:from getQualifiedSignature method:"+jmd.getQualifiedSignature() +"\n");
                				if(depsymbols.containsKey(classSymbol)) {
    	            				if(!depsymbols.get(classSymbol).contains(methodSymbol)) {
    	            					depsymbols.get(classSymbol).add(methodSymbol);
    	            				}
                				}else {
                					depsymbols.put(classSymbol, new ArrayList<String>());
                					depsymbols.get(classSymbol).add(methodSymbol);
                				}
                			}
//                			System.out.print("METHOD_DECLARATIONs:from jar:"+((JavassistMethodDeclaration) resMethodDecl.getCorrespondingDeclaration()).toString()+"\n");
                		}else if(resolvedmthddec instanceof JavaParserMethodDeclaration)  {//means come from java code
                			MethodDeclaration methoddecl = ((JavaParserMethodDeclaration) resolvedmthddec).getWrappedNode() ;
//                			System.out.print("METHOD_DECLARATIONs:method:"+methoddecl.getDeclarationAsString()+"\n");
//                			System.out.print("METHOD_DECLARATIONs:class:"+methoddecl.toString()+"\n");
                		}else if(resolvedmthddec instanceof ReflectionMethodDeclaration) { //means come from classloader
//                			System.out.print("METHOD_DECLARATIONs:from classloader:"+resolvedmthddec.toString()+"\n");
                		}
                	}
                	else
                		System.out.print("METHOD_DECLARATIONs: unsupport corresponding:"+resMethodDecl.toString()+"\n");
                	}
                	catch(UnsolvedSymbolException e) {
//                    	System.out.print("warnning: got unsovled methoddecls:"+ e.getMessage() +"\n");
                	}
                	catch(Exception es) {
                		System.out.print("warnning: got unsolved methoddecls:"+ es.getMessage() +"\n");
                	}
                	return super.visit(n, arg);
                }
            }, null);
        }
        
        System.out.println("Final results:  methods:" );
    	for(String key: depsymbols.keySet()) {
    		System.out.println(key+"-----");
    		for(String item:depsymbols.get(key) ) {
    			System.out.println("=="+item);
    		}
    	}

    }
    
}
