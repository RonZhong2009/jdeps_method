package com.ronzhong.javaParserHandler;

import com.ronzhong.imp.JavaSymbolParserAndHandlerImp;

public class JavaSymbolParserAndHandlerFactory {
	 public JavaSymbolParserAndHandler getInstance() {
		 return new JavaSymbolParserAndHandlerImp();
	 };
}
