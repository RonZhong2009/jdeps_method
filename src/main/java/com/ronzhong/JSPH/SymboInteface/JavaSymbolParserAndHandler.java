/**
 * 
 */
package com.ronzhong.JSPH.SymboInteface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.ModifierVisitor;
import com.github.javaparser.ast.visitor.Visitable;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserFieldDeclaration;
import com.github.javaparser.symbolsolver.javaparsermodel.declarations.JavaParserMethodDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistFieldDeclaration;
import com.github.javaparser.symbolsolver.javassistmodel.JavassistMethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionFieldDeclaration;
import com.github.javaparser.symbolsolver.reflectionmodel.ReflectionMethodDeclaration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.utils.SourceRoot;
import com.ronzhong.JSPH.imp.OutputSymFilter;
import com.ronzhong.JSPH.imp.SymbolFilterChainImp;




/**
 * @author ronzhong
 * Need to think about the interface, design it easy to use and extend.
 * Since java doesn't need header file. Appending methods is not that hard for 
 * extending the original functionalities.
 * The layer and its responsibilities should be taken into account carefully.
 * For me, the client code, can use interface classes(seems it's hard to get the
 * implementation directly, factory pattern is the only way?), and some classes definitely 
 * need to be implemented , and the classes can be known and accessed by client. 
 * For the class not implemented from Interface class, if want to append the methods later
 * just append it, but it may cause hard to deprecate the original class due to the
 * compatibility issues. 
 * Means we don't have to provide only one class to meet all the requirement!
 */
public class JavaSymbolParserAndHandler {
    private  CombinedTypeSolver typeSolver =  null;
	private  List<SourceRoot> srcrootlist = null;
//	private  SymbolStorageStrategy outstorage = null;
	private  SymbolFilterChainImp filterchain = null;
    private  HashMap<String, ArrayList<String>> depsymbols = new HashMap<String, ArrayList<String>>();  

	
	
	//Deprecated:save all the symbols into MogoDB
	//The symbols in the file can be organized to be searched easily. 
	// and they can be restored into the source file.
	//==> seems it's too complicated to do this, that means should dive deeper into the AST tree. That's another story.
	public JavaSymbolParserAndHandler(JavaSymbolRepository symRep, JavaSymboSolver symbolSolver) {
		srcrootlist = symRep.getSourceRootList();
		typeSolver = symbolSolver.getJavaParser();
		filterchain = new SymbolFilterChainImp();
	}


	//handle the symbol after solving it, only for solved symbol
	//only allow client to choose which handling they want for the symbol
	// handlercode works for the target symbol!
	// filterList: will be created by cusotmer from JavaSymbolFilterFactory
	// strategy  : will be created by customer from JavaSymbolStorageStrategyFactory
	public boolean getfilterOutSymbols(List<SymbolFilter> filterList, SymbolStorageStrategy strategy, int handlercode) throws Exception {
		filterList.add(new OutputSymFilter(strategy));
		filterchain.add(filterList);
		
    	for(SourceRoot sr: this.srcrootlist) {
    		handleSourceroot(sr);
    	}
    	
//		outstorage = strategy;
		//TODO: start to get the symbols and store into storage specified.
		
		return false;
	}
	

    private void handleSourceroot(SourceRoot sourceRoot) throws Exception{    	  
    	
        List<ParseResult<CompilationUnit>> culist = null; 
        culist = sourceRoot.tryToParse();

        for(ParseResult<CompilationUnit> item: culist) {
        	item.getResult().get().accept(new ModifierVisitor<Void>() {

                @Override
                public Visitable visit(FieldAccessExpr n, Void arg) {
                	try {
                	SymbolReference<ResolvedValueDeclaration> resValueDecl = JavaParserFacade.get(typeSolver)
                            .solve(n);
//                	System.out.print("METHOD_raw DECLARATIONs:"+resMethodDecl.toString()+"\n");
                	if(resValueDecl.isSolved()) { 	           	
//                		System.out.print("METHOD_DECLARATIONs:"+resMethodDecl.getCorrespondingDeclaration().toString()+"\n");
                		ResolvedValueDeclaration resolvedvadec = resValueDecl.getCorrespondingDeclaration();
                		if(resolvedvadec instanceof JavassistFieldDeclaration) {//means come from jar file
                		                			
                			JavassistFieldDeclaration jmd = (JavassistFieldDeclaration) resolvedvadec;
                			String classSymbol= jmd.declaringType().getQualifiedName();
                			Symbol sym = new Symbol();
                			sym.setDeclclass(classSymbol);
                			sym.setType(Symbol.SYM_TYPE_FIELD);
                			sym.setValue(jmd.getName());
                			filterchain.doFilterOut(sym);
                			               			
                		}else if(resolvedvadec instanceof JavaParserFieldDeclaration)  {//means come from java code
                			FieldDeclaration fielddecl = ((JavaParserFieldDeclaration) resolvedvadec).getWrappedNode() ;
//                			System.out.print("FIELD_DECLARATIONs:from:"+resValueDecl.getDeclarationAsString()+"\n");
//                			System.out.print("FIELD_DECLARATIONs:from:"+resValueDecl.toString()+"\n");
                		}else if(resolvedvadec instanceof ReflectionFieldDeclaration) { //means come from classloader
//                			System.out.print("FIELD_DECLARATIONs:from classloader:"+resolvedmthddec.toString()+"\n");
                		}
                	}
                	else
                		System.out.print("FIELD_DECLARATIONs: unsupport corresponding:"+resValueDecl.toString()+"\n");
                	}
                	catch(UnsolvedSymbolException e) {
//                    	System.out.print("warnning: got unsolved valuecls:"+ e.getMessage() +"\n");
                	}
		        catch(Exception es) {
                		System.out.print("warnning: got unsolved valuecls:"+ es.getMessage() +"\n");
                	}

//                	System.out.print("what we got valuecls:"+n.toString()+"\n");
                	return super.visit(n, arg);
                }
			
				
               
                @Override
                public Visitable visit(MethodCallExpr n, Void arg) {
                	try {
                	SymbolReference<ResolvedMethodDeclaration> resMethodDecl = JavaParserFacade.get(typeSolver)
                            .solve(n);
//                	System.out.print("METHOD_raw DECLARATIONs:"+resMethodDecl.toString()+"\n");
                	if(resMethodDecl.isSolved()) { 	           	
//                		System.out.print("METHOD_DECLARATIONs:"+resMethodDecl.getCorrespondingDeclaration().toString()+"\n");
                		ResolvedMethodDeclaration resolvedmthddec = resMethodDecl.getCorrespondingDeclaration();
                		if(resolvedmthddec instanceof JavassistMethodDeclaration) {//means come from jar file
                			
                			JavassistMethodDeclaration jmd = (JavassistMethodDeclaration) resMethodDecl.getCorrespondingDeclaration();
                			String classSymbol= jmd.declaringType().getQualifiedName();
                			Symbol sym = new Symbol();
                			sym.setDeclclass(classSymbol);
                			sym.setType(Symbol.SYM_TYPE_METHOD);
                			sym.setValue(jmd.getName());
                			filterchain.doFilterOut(sym);
                			
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
