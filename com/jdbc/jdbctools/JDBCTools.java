package com.jdbc.jdbctools;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;



public class JDBCTools {
 /*  private static ComboPooledDataSource cpds=null;
	static{
		cpds=new ComboPooledDataSource("c3p0");
	}*/
	
	private static DataSource ds=null;
	static{
		Properties properties = new Properties();
		InputStream is = JDBCTools.class.getClassLoader().getResourceAsStream("dbcp.properties");
		try {
			properties.load(is);
			ds=BasicDataSourceFactory.createDataSource(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JDBCTools() {
	
  }
    
/*	public static Connection getConnectionC3P0() throws SQLException{
    	 return cpds.getConnection();
    	  
    	 }*/
	public static void release(Connection connection) throws SQLException{
		connection.close();
		}
	public static Connection getConnectionDBCP() throws Exception{
		return  ds.getConnection();
        }
	
	public static void beginTransaction(Connection connection) throws SQLException {
		if(connection!=null){
			connection.setAutoCommit(false);
		}
	}
	
	public static void commit(Connection connection) throws SQLException{
		if(connection!=null){
			connection.commit();
		}
	}
	
	public static void rollback(Connection connection) throws SQLException{
		if(connection!=null){
			connection.rollback();
		}
	}
	
}
