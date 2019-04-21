package com.ronzhong.JSPH.imp;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class JavaSymbolParserAndHandlerImpTest {
	
    private static JavaSymbolParserAndHandlerImp symbolparseAndHandler = new JavaSymbolParserAndHandlerImp();


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testTarget() {
//    	symbolparseAndHandler.setTargetFile("testpath");
    	 assertEquals("testpath", symbolparseAndHandler.getTargetFile());
    }

}
