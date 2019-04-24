package com.client;
import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ronzhong.JSPH.SymboInteface.JavaSymboSolver;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolParserAndHandler;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolRepository;
import com.ronzhong.JSPH.SymboInteface.SymbolStorage;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

public class ClientEndPoint {

	 public static void main(String[] args) throws IOException {

		    Logger logger = LoggerFactory.getLogger(ClientEndPoint.class);
		    logger.info("ClientEndPoint:");
		        
		    logger.debug("start time: {}.",  Instant.now().toString());

		/*what customer should know:
		Symbol, MethodSymbol, FieldSymbol.
		*/
		    
		JavaSymbolParserAndHandler jts = new JavaSymbolParserAndHandler(
				new JavaSymbolRepository(JavaSymbolRepository.REPOSITORY_TYPE_PROJECT , 
                                                            "E:\\my_git_repo\\java_parser\\jdeps_method\\prototype"),
				new JavaSymboSolver(JavaSymboSolver.SYMBOL_SOLVER_RESOURCE_PATH_TO_JAR, 
															"E:\\my_git_repo\\java_parser\\jdeps_method\\prototype\\lib\\dependent.jar"), 
				0);
//		jts.setTargetFile(filepath);
//		jts.setRule(RuleFactory.getRule(symbolObjType, attributename, pattern);
//		//there will be a default strategy for each symbol type.
//		jts.setStorageStrategy( solvedType, 
//								new SymbolStorageStrategy(
//										format, 
//										new SymbolStorage(storagePath)));
//		//format can be extended into a strandardized class from symbol to show more.
//		jts.setSymbolHandler(JavaSymbol, );
		
	}
	
}

