package com.ronzhong.JSPH.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.client.ClientEndPoint;
import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterChain;

public class MethodSymFilter implements SymbolFilter {
	private Logger logger = LoggerFactory.getLogger(MethodSymFilter.class);
	
	private int symDecType;
	private String  pattern;
	
	public MethodSymFilter(int symDeclarationType, String pattern) {
		this.symDecType = symDeclarationType;
		this.pattern =  pattern;
	}
	
	@Override
	public void afterPropertiesSet() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilterOut(Symbol sym, SymbolFilterChain chain) {
		
		//do something
		//TODO: according to the symbol and the symDecType to check whether it matches the pattern it required
		if(sym.getType() != Symbol.SYM_TYPE_METHOD) {
			logger.info("MethodSymFilter: skip the symbol not method.");
			return;
		}else {
			//check whether the attraibute matches the pattern
			if(symDecType ==Symbol.SYM_DECLARATION_CLASS &&sym.getDeclclass().matches(pattern)){
				chain.doFilterOut(sym);
			}else {
			    logger.info("MethodSymFilter: symbol dosen't match the pattern string.");
				return;
			}
		}
	}

	@Override
	public boolean isSucceedFilterOut() {
		// TODO Auto-generated method stub
		return false;
	}

}
