/**
 * 
 */
package com.ronzhong.javaParserHandler;

/**
 * @author ronzhong
 *
 */
public interface JavaSymbolParserAndHandler {
	
	public boolean setTargetFile(String filePath);
	
	public boolean setTargetDir(String dirPath);
	
	public boolean setTargetProject(String projectPath);

	//you can set up multiple strategies together
	public boolean setStorageStrategy(String solvedType, SymbolStorageStrategy strategy);
	
	//handle the symbol after solving it, only for solved symbol
	public boolean setSymbolHandler();
	
	//set the rule to get the target symbol
	public boolean setFilterRule(SymbolFilterRule rule);
	
	public boolean setSymbolSymbolResourceCodeFiles(String codefilepath);
	
	public boolean setSymbolSymbolResourceClaassLoader(ClassLoader classloder);

	public boolean setSymbolSymbolResourceJarFiles(String jarfilepath);

}
