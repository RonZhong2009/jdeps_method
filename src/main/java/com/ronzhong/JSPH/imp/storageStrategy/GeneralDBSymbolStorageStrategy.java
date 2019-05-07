/**
 * 
 */
package com.ronzhong.JSPH.imp.storageStrategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ronzhong.JSPH.SymboInteface.Symbol;
import com.ronzhong.JSPH.SymboInteface.SymbolStorageStrategy;
import com.ronzhong.JSPH.imp.storage.SymbolRepoGeneraldb;

/**
 * @author ron
 * derby db
 */
public class GeneralDBSymbolStorageStrategy implements SymbolStorageStrategy {

	@Override
	public void save(Symbol sym) {
		// TODO Auto-generated method stub
		 try(Connection conn = getConnection()) 
		 { 
			 Statement stmt = conn.createStatement(); 
			 //TODO: should design a schema , then insert the symbol into db
//			 ResultSet rs = stmt.executeQuery("insert"); 
//			 while (rs.next()){ 
//				 System.out.println(rs.getString(1)); 
//			 
//			 } 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	 private static String driver = "org.apache.derby.jdbc.ClientDriver"; 
	 private static String url = "jdbc:derby://localhost:1527/default"; 
	 private SymbolRepoGeneraldb dbinstance = null;

	 
		public GeneralDBSymbolStorageStrategy(String url) {
			// TODO Auto-generated constructor stub
			dbinstance = new SymbolRepoGeneraldb(url);
		}
	 
	 private Connection getConnection() throws SQLException 
	 { 
		 try{ 
			 Class.forName(driver); 
		 }catch(Exception ex){ 
			 ex.printStackTrace(); 
			 } 
		 return DriverManager.getConnection(url); 
		 } 
	 
	 public void query() throws SQLException 
	 { 
		 try(Connection conn = getConnection()) 
		 { 
			 Statement stmt = conn.createStatement(); 
			 ResultSet rs = stmt.executeQuery("SELECT * FROM default"); 
			 while (rs.next()){ 
				 System.out.println(rs.getString(1)); 
			 
			 } 
		 } 
		 
	 }
	 
	 


}
