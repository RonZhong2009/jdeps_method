package com.ronzhong.JSPH.SymboInteface;

public abstract class SymbolStorageStrategy {
	
	static public int SYM_STORAGET_TYPE_FILE;
	static public int SYM_STORAGET_TYPE_DB;
	static public int SYM_STORAGET_TYPE_MOGODB;
	
	protected SymbolStorageStrategy(String storagepath) {
		
	};
	
	public void Save(Symbol sym) {
		
	}
}
