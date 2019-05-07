package com.ronzhong.JSPH.SymboInteface;

import com.ronzhong.JSPH.imp.storageStrategy.FileSymbolStorageStrategy;
import com.ronzhong.JSPH.imp.storageStrategy.MogoDBSymbolStorageStrategy;

public class JavaSymbolStorageStrategyFactory {
	public SymbolStorageStrategy createStoragy(int storagetype, String storagepath) {
		if(storagetype == SymbolStorageStrategy.SYM_STORAGET_TYPE_MOGODB) {
			MogoDBSymbolStorageStrategy storage  = new MogoDBSymbolStorageStrategy(storagepath);
			return storage;
		}
		
		if(storagetype == SymbolStorageStrategy.SYM_STORAGET_TYPE_FILE) {
			FileSymbolStorageStrategy storage  = new FileSymbolStorageStrategy(storagepath);
			return storage;
		}

		
		return null;
		
	}
}
