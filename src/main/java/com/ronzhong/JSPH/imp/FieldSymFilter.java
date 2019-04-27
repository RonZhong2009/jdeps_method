package com.ronzhong.JSPH.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterChain;

public class FieldSymFilter implements SymbolFilter {
	
	private Logger logger = LoggerFactory.getLogger(FieldSymFilter.class);
	
	private int symDecType;
	private String  pattern;
	
	public FieldSymFilter(int symDeclarationType, String pattern) {
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
		if(sym.getType() != Symbol.SYM_TYPE_FIELD) {
			logger.info("FieldSymFilter: skip the symbol not field.");
			chain.doFilterOut(sym);
		}else {
			//check whether the attraibute matches the pattern
			//TODO: stil other attributes need to be checked, there will be more else statments later
			if(symDecType ==Symbol.SYM_DECLARATION_CLASS &&sym.getDeclclass().matches(pattern)){
				chain.doFilterOut(sym);
			}else {
			    logger.info("FieldSymFilter: symbol dosen't match the pattern string.");
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