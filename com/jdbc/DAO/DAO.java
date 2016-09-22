package com.jdbc.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.jdbc.jdbctools.JDBCTools;

public class DAO<T> {
	private  static QueryRunner queryRunner;
    @SuppressWarnings("rawtypes")
	private Class clazz=null;
	private Connection connection=null;
    static{
    	queryRunner=new QueryRunner();
    }
    
    
    @SuppressWarnings("rawtypes")
	public DAO(){
        
    Type type=this.getClass().getGenericSuperclass();	
    ParameterizedType py=(ParameterizedType) type;
    Type []types=py.getActualTypeArguments();
    if(types !=null && types.length>0){
        clazz=(Class) types[0];
    }	
    }
    
    public void openConnection() throws Exception{
    	if(connection==null || connection.isClosed() ){
//    	   connection=JDBCTools.getConnectionC3P0();
    		connection=JDBCTools.getConnectionDBCP();
    	   }	
    }
    
    public Connection getConnection() throws Exception{
    	openConnection();
    	return connection;
    }
    
    public void update(String sql, Object...args) throws Exception{
    	//Connection connection=JDBCTools.getConnectionC3P0();   
        openConnection();
    	queryRunner.update(connection, sql, args);
        JDBCTools.release(connection);
	}
	public T getBean(String sql,Object...args) throws Exception{
		//Connection connection=JDBCTools.getConnectionC3P0();
		openConnection();
		
	    @SuppressWarnings("unchecked")
		T entity=(T) queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
	    JDBCTools.release(connection);
	    return entity;
	}
	
	public List<T> getBeanList(String sql,Object...args) throws Exception{
//		Connection connection=JDBCTools.getConnectionC3P0();
		openConnection();
		@SuppressWarnings("unchecked")
		List<T> list=(List<T>) queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		JDBCTools.release(connection);
		return list;
	}
	
	public Object getForValue(String sql,Object...args) throws Exception{
//		Connection connection=JDBCTools.getConnectionC3P0();
		openConnection();
		Object obj=queryRunner.query(connection, sql, new ScalarHandler(), args);
		return obj;
		}
	public byte[] getPic(String sql,Object...args) throws Exception{
		openConnection();
		Blob blob=(Blob) queryRunner.query(connection, sql, new ScalarHandler(), args);
	    InputStream in=blob.getBinaryStream();
	    byte b[]=null;
	    if(blob!=null){
	    b=new byte[(int) blob.length()]; 
	    in.read(b);
	    }
	    return b;
	}
}
