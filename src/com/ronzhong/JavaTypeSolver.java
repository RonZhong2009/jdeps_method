/**
 * 
 */
package com.ronzhong;

/**
 * @author ronzhong
 *
 */
public interface JavaTypeSolver {
	public boolean addJarFile(String jarpath);

	public boolean addSourceCodePath(String sourepath);
	
	public boolean addClassLoader(ClassLoader classloader);
	
	public Object get();
}
