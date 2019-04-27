/**
 * 
 */
package com.ronzhong.JSPH.imp;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

/**
 * @author ron
 *
 */
public class MogoDBSymbolStorageStrategy implements SymbolStorageStrategy {
	private SymbolRepoMogodb mogodbinstance = null;

	public MogoDBSymbolStorageStrategy(String storagepath) {
		// TODO Auto-generated constructor stub
		mogodbinstance = new SymbolRepoMogodb(storagepath);
		
	}

	@Override
	public void save(Symbol sym) {
		// TODO Auto-generated method stub
		
	}

}
