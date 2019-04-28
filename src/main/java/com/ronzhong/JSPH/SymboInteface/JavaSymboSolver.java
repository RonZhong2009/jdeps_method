package com.ronzhong.JSPH.SymboInteface;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.client.ClientEndPoint;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

public class JavaSymboSolver {
	
	private Logger logger = LoggerFactory.getLogger(ClientEndPoint.class);
	private ClassLoaderTypeSolver classloaderdepTypeSolver=null;
	private CombinedTypeSolver comdepTypeSolver=null;
	private JavaParserTypeSolver javaparserdepTypeSolver=null;
	private JarTypeSolver depjarTypeSolver=null;
	 
	static public int SYMBOL_SOLVER_RESOURCE_PATH_TO_CODE = 0X0001;
	static public int SYMBOL_SOLVER_RESOURCE_PATH_TO_JAR = 0X0002;
		
	public JavaSymboSolver(ClassLoader classloader){
		this.classloaderdepTypeSolver  = new ClassLoaderTypeSolver(classloader);
		comdepTypeSolver = new CombinedTypeSolver();
		this.comdepTypeSolver.add(this.classloaderdepTypeSolver);
		
	};
	
	public JavaSymboSolver(int pathType, String filepath) throws IOException{
	
		comdepTypeSolver = new CombinedTypeSolver();

		
		if( pathType == SYMBOL_SOLVER_RESOURCE_PATH_TO_CODE){
			this.javaparserdepTypeSolver = new JavaParserTypeSolver(filepath);
			this.comdepTypeSolver.add(this.javaparserdepTypeSolver);
		}else if( pathType == SYMBOL_SOLVER_RESOURCE_PATH_TO_JAR){
			this.depjarTypeSolver = new JarTypeSolver(filepath);
			this.comdepTypeSolver.add(this.depjarTypeSolver);
		} else {
			logger.error("with wrong type to create JavaSymbolSlover");
		}
	};
	
	public JavaSymboSolver addSymbolSolver(JavaSymboSolver symSolver) {
		if(comdepTypeSolver == null)
			comdepTypeSolver = new CombinedTypeSolver();
		
		//should specify
		if(symSolver.classloaderdepTypeSolver != null) {
			if(this.classloaderdepTypeSolver !=null) {
				logger.error("There have been classloaer slover, another one will raise error");
			} else {
				this.classloaderdepTypeSolver  = symSolver.classloaderdepTypeSolver;
				this.comdepTypeSolver.add(this.classloaderdepTypeSolver);
			}
		}
		
		if(symSolver.depjarTypeSolver != null && symSolver.depjarTypeSolver != this.depjarTypeSolver)  {
			this.comdepTypeSolver.add(symSolver.depjarTypeSolver);
		}
		
		if(symSolver.javaparserdepTypeSolver != null && symSolver.javaparserdepTypeSolver != this.javaparserdepTypeSolver)  {
			this.comdepTypeSolver.add(symSolver.javaparserdepTypeSolver);
		}
		
		//TODO: merge the SymbolSolver into the current instance
		this.comdepTypeSolver.add(symSolver.getJavaParser());
		return this;
	}

	protected CombinedTypeSolver getJavaParser() {
		// TODO Auto-generated method stub
		return this.comdepTypeSolver;
	}
	

}
