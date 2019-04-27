package com.ronzhong.JSPH.imp;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.client.ClientEndPoint;
import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;

public class FileSymbolStorageStrategy implements SymbolStorageStrategy {

	private File file = null;
	private String filepath = null;
	private Logger logger = LoggerFactory.getLogger(FileSymbolStorageStrategy.class);

	public FileSymbolStorageStrategy(String storagepath) {
		this.filepath =  storagepath;
	}

	public void save(Symbol sym) {
		try {
		    FileOutputStream fos = new FileOutputStream(this.filepath);
		    DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		    outStream.writeUTF(sym.getValue());
		    outStream.close();
		}catch(Exception e) {
			logger.error("FileSymbolStorageStrategy writing error:" +  e.getStackTrace().toString());
		}
	}

}
