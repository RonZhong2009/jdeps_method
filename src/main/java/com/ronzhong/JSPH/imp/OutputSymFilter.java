package com.ronzhong.JSPH.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolFilter;
import com.ronzhong.JSPH.SymboInteface.SymbolFilterChain;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

//make sure OutputSymFilter is the last filter to be processed in the Filter list
public class OutputSymFilter implements SymbolFilter {

	private Logger logger = LoggerFactory.getLogger(OutputSymFilter.class);
	
	private SymbolStorageStrategy storage = null;

	public OutputSymFilter(SymbolStorageStrategy storage) {
		this.storage = storage;

	}
	
	@Override
	public void afterPropertiesSet() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSucceedFilterOut() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doFilterOut(Symbol sym, SymbolFilterChain chain) {
		// since it can run to this step, it means it has passed all the filter,
		// and now it's time to store it into the storage client specified.
		storage.save(sym);
		
		//TODO: maybe check out whether OutputSymFilter is the last item of chain.

	}

}
