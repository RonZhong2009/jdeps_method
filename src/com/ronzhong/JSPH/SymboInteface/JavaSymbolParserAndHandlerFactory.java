package com.ronzhong.JSPH.SymboInteface;

import com.ronzhong.JSPH.imp.JavaSymbolParserAndHandlerImp;

public class JavaSymbolParserAndHandlerFactory {
	 public JavaSymbolParserAndHandler getInstance() {
		 return new JavaSymbolParserAndHandlerImp();
	 };
}
