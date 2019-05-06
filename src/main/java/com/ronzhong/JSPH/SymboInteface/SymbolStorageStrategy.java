package com.ronzhong.JSPH.SymboInteface;

public interface SymbolStorageStrategy {
	
	static public int SYM_STORAGET_TYPE_FILE  = 0x0001;
	static public int SYM_STORAGET_TYPE_DB = 0x0002;
	static public int SYM_STORAGET_TYPE_MOGODB= 0x0004;
	
//	 SymbolStorageStrategy(String storagepath);
	//TODO: should support update symbol
	//void update
	
	void save(Symbol sym);
}
