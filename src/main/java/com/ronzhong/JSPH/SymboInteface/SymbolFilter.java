package com.ronzhong.JSPH.SymboInteface;

public interface SymbolFilter {
	

    public void afterPropertiesSet() ;
    
    public boolean isSucceedFilterOut();
	
	public void doFilterOut(Symbol sym, SymbolFilterChain chain);
}
