package com.ronzhong.JSPH.SymboInteface;

public class Symbol {

	public static int SY_TYPE_UNKNOW = 0x00000000;//"method";
	
	//handle strategy for the symbol
	public static int SYM_HANDLEWAY_STUB = 0x01000000;//"method";
	public static int SYM_HANDLEWAY_REMOVE = 0x02000000;//"field";
	
	public static int SYM_TYPE_METHOD = 0x00010000;//"method";
	public static int SYM_TYPE_FIELD = 0x00020000;//"field";

	//THE LAST TWO BYTES
	public static int SYM_DECLARATION_PACKAGE = 0x00000001;//"package";
	public static int SYM_DECLARATION_CLASS= 0x00000002;//"class";
	public static int SYM_DECLARATION_METHOD = 0x00000004;//"method";
	 
	//HIGER THAT THE DECLARATION TYPE, TWO BYTES
	public static int SYM_STATE_UNSOLVED = 0x00000100;//"method";
	public static int SYM_STATE_SOLVED = 0x00000200;//"field";
	
	private int type =  0;
	
	private String value = null;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDeclImport() {
		return declImport;
	}

	public void setDeclImport(String declImport) {
		this.declImport = declImport;
	}

	public String getDeclmethod() {
		return declmethod;
	}

	public void setDeclmethod(String declmethod) {
		this.declmethod = declmethod;
	}

	public String getDeclclass() {
		return declclass;
	}

	public void setDeclclass(String declclass) {
		this.declclass = declclass;
	}

	public String getDeclpackage() {
		return declpackage;
	}

	public void setDeclpackage(String declpackage) {
		this.declpackage = declpackage;
	}

	private String declImport = null;

	private String declmethod = null;

	private String declclass = null;

	private String declpackage = null;

	
	public Symbol() {
		type = SY_TYPE_UNKNOW;
	}
	
	public int getType() {
		return type;
	}
	
	
	public int setType(int type) {
		this.type = type;
		return this.type;
	}


}
