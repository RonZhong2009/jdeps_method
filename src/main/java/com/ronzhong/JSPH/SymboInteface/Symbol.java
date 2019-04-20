package com.ronzhong.JSPH.SymboInteface;

public interface Symbol {

	//handle strategy for the symbol
	static int SYM_HANDLEWAY_STUB = 0x01000000;//"method";
	static int SYM_HANDLEWAY_REMOVE = 0x02000000;//"field";
	
	
	static int SYM_TYPE_METHOD = 0x00010000;//"method";
	static int SYM_TYPE_FIELD = 0x00020000;//"field";

	//THE LAST TWO BYTES
	static int SYM_DECLARATION_PACKAGE = 0x00000001;//"package";
	static int SYM_DECLARATION_CLASS= 0x00000002;//"class";
	static int SYM_DECLARATION_METHOD = 0x00000004;//"method";
	
	//HIGER THAT THE DECLARATION TYPE, TWO BYTES
	static int SYM_STATE_UNSOLVED = 0x00000100;//"method";
	static int SYM_STATE_SOLVED = 0x00000200;//"field";

}
