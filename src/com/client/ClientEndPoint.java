package com.client;


import com.ronzhong.javaParserHandler.JavaSymbolParserAndHandler;
import com.ronzhong.javaParserHandler.JavaSymbolParserAndHandlerFactory;
import com.ronzhong.javaParserHandler.SymbolStorage;
import com.ronzhong.javaParserHandler.SymbolStorageStrategy;

public class ClientEndPoint {

	static public void main() {

		/*what customer should know:
		Symbol, MethodSymbol, FieldSymbol.
		
		*/
		JavaSymbolParserAndHandler jts = new JavaSymbolParserAndHandlerFactory().getInstance();
//		jts.setTargetFile(filepath);
//		jts.setRule(RuleFactory.getRule(symbolObjType, attributename, pattern);
//		//there will be a default strategy for each symbol type.
//		jts.setStorageStrategy( solvedType, 
//								new SymbolStorageStrategy(
//										format, 
//										new SymbolStorage(storagePath)));
//		//format can be extended into a strandardized class from symbol to show more.
//		jts.setSymbolHandler(JavaSymbol, );
//		
		
	}
	
}

