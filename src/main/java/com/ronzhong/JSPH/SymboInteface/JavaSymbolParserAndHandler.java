/**
 * 
 */
package com.ronzhong.JSPH.SymboInteface;

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
public interface JavaSymbolParserAndHandler {

// move the following methods to class JavaSymbolRepository:
	//	public boolean setTargetFile(String filePath);
	//	
	//	public boolean setTargetDir(String dirPath);
	//	
	//	public boolean setTargetProject(String projectPath);
	public boolean setSymbolRepository(JavaSymbolRepository symRep);
	
	public boolean addSymbolSolver(JavaSymboSolver symSolver);

	//you can set up multiple strategies together
	public boolean setStorageStrategy(int solvedType, SymbolStorageStrategy strategy);
	
	//handle the symbol after solving it, only for solved symbol
	//only allow client to choose which handling they want for the symbol
	public boolean setSymbolHandler(int handlercode);
	
	//set the rule to get the target symbol
	public boolean addFilterRule(SymbolFilter rule);
	
	// move the following methods to class JavaSymboSolver:	
	//	public boolean setSymbolSymbolResourceCodeFiles(String codefilepath);
	//	
	//	public boolean setSymbolSymbolResourceClaassLoader(ClassLoader classloder);
	//
	//	public boolean setSymbolSymbolResourceJarFiles(String jarfilepath);

}
