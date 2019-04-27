package com.ronzhong.JSPH.SymboInteface;

import com.ronzhong.JSPH.imp.MogoDBSymbolStorageStrategy;

public class JavaSymbolStorageStrategyFactory {
	public SymbolStorageStrategy createStoragy(int storagetype, String storagepath) {
		if(storagetype == 0) {
		MogoDBSymbolStorageStrategy storage  = new MogoDBSymbolStorageStrategy(storagepath);
		return storage;
	}
		return null;
		
	}
}
