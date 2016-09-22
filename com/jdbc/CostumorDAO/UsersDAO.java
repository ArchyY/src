package com.jdbc.CostumorDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.UpdatedMethodBinding;

import com.jdbc.DAO.DAO;
import com.jdbc.entity.BuyDetail;
import com.jdbc.entity.BuyRecord;
import com.jdbc.entity.Users;

public class UsersDAO extends DAO<Users> {
     
	public List<Users> getUsers() throws Exception{
		String sql="select username, userid, userchara, balance from tusers";
		List<Users> users=null;
		users=getBeanList(sql);
		return users;
	}
	
	
	
	// Register an account:
	public void preRegisterAccount(Object...args) throws Exception{
		String sql="insert into tusers values ((select nvl(max(userid),0)+1 from tusers),?,?,3,?)";
		update(sql,args);
	}
	
	// Delete an account: 
	public void preDeleteAccount(String username, String password) throws Exception{
		if(preLogin(username, password)!=null){
		String sql="delete from tusers where username=?";
		update(sql, username, password);
		}
	}
	
	// Based on the input user name, find a corresponding password:  
	/*public String preFindPassword(String username) throws SQLException {
		String sql="select password from tusers where username=?";
	    String password=(String) getForValue(sql, username);
	    return password;
	}*/
	
	/*
	 *  By calling the method "preFindPassword", get the password from database, and then check if
	 *  the password matches the input password.  
	 */
	
	public Users preLogin(String username,String password) throws Exception{
		String sql="select * from tusers where username=? and password=?";
		Users user=getBean(sql,username,password);
		 return user;
	    }
	
	public void updateBalance(Object...args) throws Exception{
       String sql="update tusers set balance=? where userid=?";		
	   update(sql, args);
	}
			 
   public void updateBalance(int userId,double sumCost) throws Exception {
	   String sql="update tusers set balance=balance+? where userid=?";	
       update(sql,-sumCost,userId);
   }		    
	
   public void addDetail(BuyDetail buyDetail) throws Exception{
	   String isbn=buyDetail.getIsbn();
	   double price=buyDetail.getPayMoney();
	   int bCount=buyDetail.getbCount();
	   String sql="insert into tbuydetail values ((select nvl(max(id),0)+1 from tbuydetail),?,(select max(buyid) from tbuyrecord),?,?)";
  	   update(sql, isbn,bCount,price);
     }
		
   public void addRecord(BuyRecord buyRecord) throws Exception{
	   int userId=buyRecord.getUserID();
	   int buyCount=buyRecord.getBuyCount();
	   double totalMoney=buyRecord.getAllMoney();
	   java.sql.Date buyTime=new java.sql.Date(buyRecord.getBuyTime().getTime());
	   
	   
	   String sql="insert into tbuyrecord values ((select nvl(max(buyid),0)+1 from tbuyrecord),?,?,?,?)";
	   update(sql,userId,buyTime,totalMoney,buyCount);
   }

public double getBalance(int userId) throws Exception {
	double balance;
	String sql="select balance from tusers where userid=?";
	balance=((BigDecimal)getForValue(sql, userId)).longValue();
	
	return balance;
}





}
	
	
	

