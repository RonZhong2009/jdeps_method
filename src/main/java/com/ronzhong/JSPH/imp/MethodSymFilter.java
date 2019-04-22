package com.ronzhong.JSPH.imp;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterChain;

public class MethodSymFilter implements SymbolFilter {
	@Override
	public void afterPropertiesSet() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilterOut(Symbol sym, SymbolFilterChain chain) {
		
		//do something
		
		chain.doFilterOut(sym);
	}

	@Override
	public boolean isSucceedFilterOut() {
		// TODO Auto-generated method stub
		return false;
	}

}
