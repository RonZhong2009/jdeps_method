package com.ronzhong.JSPH.imp;

import java.util.ArrayList;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterChain;

public class SymbolFilterChainImp implements SymbolFilterChain {

	private int curIndex = 0;

	private ArrayList<SymbolFilter>  filterlist; // which should has current position attribute.

	public void doFilterOut(Symbol sym) {
		 SymbolFilter filter = this.filterlist.get(curIndex++);
		 filter.doFilterOut(sym, this);
		 filter.afterPropertiesSet();
	}

}
