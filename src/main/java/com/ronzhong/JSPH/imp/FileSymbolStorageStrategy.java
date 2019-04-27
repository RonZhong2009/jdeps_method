package com.ronzhong.JSPH.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

public class FileSymbolStorageStrategy extends SymbolStorageStrategy {
	
	private Logger logger = LoggerFactory.getLogger(FileSymbolStorageStrategy.class);

	
	public FileSymbolStorageStrategy(String storagepath) {
		super(storagepath);		// TODO Auto-generated constructor stub
	}

	public void Save(Symbol sym) {
		
	}

}
