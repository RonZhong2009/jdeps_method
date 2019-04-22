package com.ronzhong.JSPH.imp;

import com.ronzhong.JSPH.SymboInteface.JavaSymboSolver;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolParserAndHandler;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolRepository;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

public class JavaSymbolParserAndHandlerImp implements JavaSymbolParserAndHandler{

	private String targetFile;
	
	public String getTargetFile() {
		return targetFile;
	}

	@Override
	public boolean setStorageStrategy(int solvedType, SymbolStorageStrategy strategy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSymbolHandler(int handlercode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addSymbolSolver(JavaSymboSolver symSolver) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSymbolRepository(JavaSymbolRepository symRep) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addFilterRule(SymbolFilter rule) {
		// TODO Auto-generated method stub
		return false;
	}

}
