package com.ronzhong.JSPH.SymboInteface;

import java.util.List;

public interface SymbolFilterChain {
	public void doFilterOut(Symbol sym);

	public void add(List<SymbolFilter> filterList);
}
