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
	
	private boolean modifySymbol(Symbol sym) {
		//TODO: search the symbol from mogodb, if there is, add the reference number,
		// and return it.
		
		return false;
	}
	
	private boolean appendSymbol(Symbol sym) {
		//TODO: add the symbol into mogodb
		
		return true;
	}

	@Override
	public void save(Symbol sym) {
		// TODO Auto-generated method stub
		if (!modifySymbol(sym)) {
			appendSymbol(sym);
		}			
	}

}
