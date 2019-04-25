package com.ronzhong.JSPH.SymboInteface;

import java.nio.file.Paths;
import java.util.List;

import com.github.javaparser.symbolsolver.utils.SymbolSolverCollectionStrategy;
import com.github.javaparser.utils.ProjectRoot;
import com.github.javaparser.utils.SourceRoot;

public class JavaSymbolRepository {
	
	static public int REPOSITORY_TYPE_FILE = 0x0001;
	static public int REPOSITORY_TYPE_DIR = 0x0002;
	static public int REPOSITORY_TYPE_PROJECT = 0x0004;
	private  List<SourceRoot> srcrootlist = null;

	public JavaSymbolRepository(int repostoryType, String filepath) {
		if(repostoryType == REPOSITORY_TYPE_PROJECT ) {
			ProjectRoot projectRoot = new SymbolSolverCollectionStrategy().collect(Paths.get(filepath));
			srcrootlist = projectRoot.getSourceRoots();
		
		}else if(repostoryType == REPOSITORY_TYPE_DIR ) {
			
		}else if(repostoryType == REPOSITORY_TYPE_FILE ) {
			
		}
	
	}

	protected List<SourceRoot> getSourceRootList() {
		// TODO Auto-generated method stub
		return null;
	};
}
