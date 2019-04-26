/**
 * 
 */
package com.ronzhong.JSPH.SymboInteface;

import java.util.List;

import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.utils.SourceRoot;
import com.ronzhong.JSPH.imp.MogoDBSymbolStorageStrategy;

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
	private  SymbolStorageStrategy outstorage = null;
	private  SymbolFilterChain filterchain = null;
	
	
	//Deprecated:save all the symbols into MogoDB
	//The symbols in the file can be organized to be searched easily. 
	// and they can be restored into the source file.
	//==> seems it's too complicated to do this, that means should dive deeper into the AST tree. That's another story.
	public JavaSymbolParserAndHandler(JavaSymbolRepository symRep, JavaSymboSolver symbolSolver) {
		srcrootlist = symRep.getSourceRootList();
		typeSolver = symbolSolver.getJavaParser();
	}


	//handle the symbol after solving it, only for solved symbol
	//only allow client to choose which handling they want for the symbol
	// handlercode works for the target symbol!
	public boolean getfilterOutSymbols(List<SymbolFilter> filterList, SymbolStorageStrategy strategy, int handlercode) {
		filterchain.add(filterList);
		outstorage = strategy;
		//TODO: start to get the symbols and store into storage specified.
		
		return false;
	}
	
	public filter getSymbolFilter(int symType, int attribute, String pattern ) {
		SymbolFilter filter  = (new SymbolFilterFactory).createRule(symType, attribute, pattern);
		return filter;
	}
	
	public SymbolStorageStrategy getSymbolStorageStrategy(int storagetype, String storagepath) {
		//TODO: leave the details to the factory
		if(storagetype == 0) {
			MogoDBSymbolStorageStrategy storage  = new MogoDBSymbolStorageStrategy(storagepath);
			return storage;
		}
		return null;
	}
    
}
