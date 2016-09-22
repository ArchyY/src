package com.jdbc.Biz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import sun.print.resources.serviceui;

import com.javaweb.exception.InsufficientBalanceException;
import com.jdbc.CostumorDAO.BookDAO;
import com.jdbc.CostumorDAO.UsersDAO;
import com.jdbc.entity.Book;
import com.jdbc.entity.BuyDetail;
import com.jdbc.entity.BuyRecord;
import com.jdbc.entity.Users;
import com.jdbc.jdbctools.JDBCTools;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

public class UsersBiz {
     private UsersDAO usersDAO=new UsersDAO();
     
     public List<Users> getUsers() throws Exception {
		List<Users> users=null;
    	 try {
			 users=usersDAO.getUsers();
		} catch (Exception e) {
		  throw e;
		}finally{
			JDBCTools.release(usersDAO.getConnection());
		}
		return users;
	}
     
     
     
     public void registerAccount(Users user) throws Exception {
		try {
			usersDAO.preRegisterAccount(user.getUsername(), user.getPassword(),user.getBalance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void deleteAccount(Users tusers) throws Exception {
		try {
			usersDAO.preDeleteAccount(tusers.getUsername(), tusers.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public Users login(Users tusers) throws Exception {
    	String username=tusers.getUsername();
    	String password=tusers.getPassword();
    	Users cTusers=null;
    	try {
			if(usersDAO.preLogin(username,password)!=null){
				cTusers=new Users();
				cTusers=usersDAO.preLogin(username,password);
				return cTusers;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
    	finally{
    	JDBCTools.release(usersDAO.getConnection());
		}
    	return cTusers;
	}
    
    public void updateBalance(Users user) throws Exception {
		double balance=user.getBalance();
		int userId=user.getUserId();
	    try {
			usersDAO.updateBalance(balance,userId);
		} catch (Exception e) {
			throw e;
		}
	    finally{
	    	JDBCTools.release(usersDAO.getConnection());
	    }
    }
   
    
    public double getBalance(Users user) throws SQLException, Exception {
		int userId=user.getUserId();
		double balance;
		try{
			balance=usersDAO.getBalance(userId);
		}catch(Exception e){
		throw e;
		}finally{
			JDBCTools.release(usersDAO.getConnection());
		}
		return balance;
	}
    
    
    public List<Book> addBuyRecord(Map<String, Integer> shopCar,Users user) throws Exception{
    	double sumCost=new BookBiz().getSumCost(shopCar);
    	   List<Book> books=null;
    	   JDBCTools.beginTransaction(usersDAO.getConnection());
    	   
    	   try{
    	   if(user.getBalance()>=sumCost){
    	   	usersDAO.updateBalance(user.getUserId(), sumCost);
    	    books= new ArrayList<>();
    	    List<BuyDetail> buyDetails=new ArrayList<>();
    	   
    	    BookDAO bDao=new BookDAO();
    	    int totalBuyCount=0;
    	    
    	    for(String isbn:shopCar.keySet()){
    	    Book book=bDao.getBook(isbn);
    	    int num=shopCar.get(isbn);
    	    book.setBuyCount(num);
    	    book.setBkCount(book.getBkCount()-num);
    	    bDao.updateBCount(book.getBkCount(),isbn);
    	    books.add(book);
    	    
    	    totalBuyCount+=num;
    	    
    	    BuyDetail buyDetail=new BuyDetail();
			buyDetail.setIsbn(isbn);
		    buyDetail.setPayMoney(book.getPrice());
		    buyDetail.setbCount(num);
		    buyDetails.add(buyDetail);
		}
    	    
    	    BuyRecord buyRecord=new BuyRecord();
    	    buyRecord.setUserID(user.getUserId());
    	    buyRecord.setBuyCount(totalBuyCount);
    	    buyRecord.setAllMoney(sumCost);
    	    buyRecord.setBuyTime(new Date());
    	    buyRecord.setBuyList(buyDetails);
    	     
    	    usersDAO.addRecord(buyRecord);
    	    for(BuyDetail x:buyDetails ){
    	    usersDAO.addDetail(x);	
    	    }
    	   JDBCTools.commit(usersDAO.getConnection()); 
    	   }else {
    		   throw new InsufficientBalanceException();
		}
    	   
    	}catch(Exception e){
    		JDBCTools.rollback(usersDAO.getConnection());
    		throw e;
    		
    	}finally{
    		JDBCTools.release(usersDAO.getConnection());
    	}
    	
    	  return books;
    	
    }
    
 /*   public int getAllRecord() throws Exception{
    	int allRecord=0;
    	
    	try {
			allRecord=usersDAO.getAllRecord();
		} catch (Exception e) {
		 throw e;
		}finally{
			JDBCTools.release(usersDAO.getConnection());
		}
    	return allRecord;
    }*/

}
