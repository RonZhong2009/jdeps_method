package com.client;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ronzhong.JSPH.SymboInteface.JavaSymboSolver;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolFilterFactory;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolParserAndHandler;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolRepository;
import com.ronzhong.JSPH.SymboInteface.JavaSymbolStorageStrategyFactory;
import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
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
															"E:\\my_git_repo\\java_parser\\jdeps_method\\prototype\\lib\\dependent.jar"));
		List<SymbolFilter> filterList =  new ArrayList<SymbolFilter>();
		SymbolStorageStrategy	strategy = new JavaSymbolStorageStrategyFactory().
				createStoragy(SymbolStorageStrategy.SYM_STORAGET_TYPE_MOGODB, "mogodb:\\");
		SymbolFilter filter = new JavaSymbolFilterFactory().createFilter
				(Symbol.SYM_TYPE_FIELD, Symbol.SYM_DECLARATION_METHOD, "*");
		filterList.add(filter);

		int handlercode = 0;//default value
		
		jts.getfilterOutSymbols(filterList, strategy, handlercode);
				
	}
	
}

