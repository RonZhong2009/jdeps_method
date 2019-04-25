/**
 * 
 */
package com.ronzhong.JSPH.imp;

import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

/**
 * @author ron
 *
 */
public class MogoDBSymbolStorageStrategy extends SymbolStorageStrategy {
	private SymbolRepoMogodb mogodbinstance = null;

	public MogoDBSymbolStorageStrategy(String storagepath) {
		super(storagepath);
		// TODO Auto-generated constructor stub
		mogodbinstance = new SymbolRepoMogodb(storagepath);
		
	}

}
