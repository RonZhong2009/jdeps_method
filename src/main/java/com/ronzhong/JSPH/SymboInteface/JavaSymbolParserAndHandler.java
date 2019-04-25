/**
 * 
 */
package com.ronzhong.JSPH.SymboInteface;

import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.utils.SourceRoot;

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
	
	//save all the symbols into MogoDB
	//The symbols in the file can be organized to be searched easily. 
	// and they can be restored into the source file.
	public JavaSymbolParserAndHandler(JavaSymbolRepository symRep, JavaSymboSolver symbolSolver, int handlercode) {
//		
		srcrootlist = symRep.getSourceRootList();
		typeSolver = symbolSolver.getJavaParser();
	}


	//handle the symbol after solving it, only for solved symbol
	//only allow client to choose which handling they want for the symbol
	public boolean getfilterOutSymbols(int SymbolType, int SymbolDecAttribute, String pattern, SymbolStorageStrategy strategy) {
		return false;
	}
	
	public SymbolStorageStrategy getSymbolStorageStrategy(int storagetype, String storagepath) {
		
		return null;
	}
    
}
