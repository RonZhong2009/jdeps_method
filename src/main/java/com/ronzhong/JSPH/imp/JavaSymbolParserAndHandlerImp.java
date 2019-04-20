package com.ronzhong.JSPH.imp;

import com.ronzhong.JSPH.SymboInteface.JavaSymbolParserAndHandler;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterRule;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

public class JavaSymbolParserAndHandlerImp implements JavaSymbolParserAndHandler{

	private String targetFile;
	
	@Override
	public boolean setTargetFile(String filePath) {
		// TODO Auto-generated method stub
		targetFile = filePath;
		return false;
	}

	public String getTargetFile() {
		return targetFile;
	}

	@Override
	public boolean setTargetDir(String dirPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTargetProject(String projectPath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setStorageStrategy(String solvedType, SymbolStorageStrategy strategy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setFilterRule(SymbolFilterRule rule) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSymbolSymbolResourceCodeFiles(String codefilepath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSymbolSymbolResourceClaassLoader(ClassLoader classloder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSymbolSymbolResourceJarFiles(String jarfilepath) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSymbolHandler(int handlercode) {
		// TODO Auto-generated method stub
		return false;
	}

}
