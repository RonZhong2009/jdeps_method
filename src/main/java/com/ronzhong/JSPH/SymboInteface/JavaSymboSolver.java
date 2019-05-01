package com.ronzhong.JSPH.SymboInteface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.client.ClientEndPoint;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

public class JavaSymboSolver {
	
	private Logger logger = LoggerFactory.getLogger(ClientEndPoint.class);
	private CombinedTypeSolver comdepTypeSolver= new CombinedTypeSolver();;
	private Collection<Object> typesolverllist = new ArrayList<Object>();
	 
	static public int SYMBOL_SOLVER_RESOURCE_PATH_TO_CODE = 0X0001;
	static public int SYMBOL_SOLVER_RESOURCE_PATH_TO_JAR = 0X0002;
		
	public JavaSymboSolver(ClassLoader classloader){
		addSolver(new ClassLoaderTypeSolver(classloader));
	};
	
	public JavaSymboSolver(int pathType, String filepath) throws IOException{
		if( pathType == SYMBOL_SOLVER_RESOURCE_PATH_TO_CODE){
			addSolver(new JavaParserTypeSolver(filepath));
		}else if( pathType == SYMBOL_SOLVER_RESOURCE_PATH_TO_JAR){
			addSolver(new JarTypeSolver(filepath));
		} else {
			logger.error("with wrong type to create JavaSymbolSlover");
		}
	};
	
	private void addSolver(Object solver) {
		this.typesolverllist.add(solver);
		this.comdepTypeSolver.add((TypeSolver) solver);
	}
	
	private boolean searchSolver(Object solver) {
		for(Object item:this.typesolverllist) {
			if(solver == item) {
				return true;			
			}
		}
		return false;
	}
	
	public JavaSymboSolver addSymbolSolver(JavaSymboSolver symSolver) {
		
		for(Object item:symSolver.typesolverllist)
		if(!searchSolver(item)) {
			addSolver(item);
		}
		return this;
	}

	protected CombinedTypeSolver getJavaParser() {
		// TODO Auto-generated method stub
		return this.comdepTypeSolver;
	}
	

}
