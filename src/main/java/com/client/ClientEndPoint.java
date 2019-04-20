package com.client;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.ronzhong.JSPH.SymboInteface.JavaSymbolParserAndHandler;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolParserAndHandlerFactory;
import com.ronzhong.JSPH.SymboInteface.SymbolStorage;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

public class ClientEndPoint {

	 public static void main(String[] args) {

		    Logger logger = LoggerFactory.getLogger(ClientEndPoint.class);
		    logger.info("ClientEndPoint:");
		        
		    logger.debug("start time: {}.",  Instant.now().toString());

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

