package com.ronzhong.JSPH.SymboInteface;

import com.ronzhong.JSPH.imp.MethodSymFilter;

public class JavaSymbolFilterFactory {
	
	//symbol type: method, field, 
	//attribute type: DeclMethod, DeclClass, DeclPackage, DeckMethodBody
	public SymbolFilter createFilter(int symStateSolved, int symDeclarationMethod, String pattern) {
		// TODO Auto-generated method stub
		if(symStateSolved == Symbol.SYM_TYPE_METHOD)
			return new MethodSymFilter(symDeclarationMethod, pattern );
		return null;
	}
}
