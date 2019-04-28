package com.ronzhong.JSPH.imp;

import java.util.ArrayList;
import java.util.List;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterChain;

public class SymbolFilterChainImp implements SymbolFilterChain {

	private int curIndex = 0;

	private List<SymbolFilter>  filterlist; // which should has current position attribute.

	public void doFilterOut(Symbol sym) {
	
		if(curIndex <= (this.filterlist.size() - 1)) {
			SymbolFilter filter = null;
			filter = this.filterlist.get(curIndex++);
			filter.doFilterOut(sym, this);
			filter.afterPropertiesSet();
		}

	}
	
	public void startFilter(Symbol sym) {
		this.curIndex = 0;
		doFilterOut(sym);
	}

	@Override
	public void add(List<SymbolFilter> filterList) {
		// TODO Auto-generated method stub
		this.filterlist = filterList;
	}

}
