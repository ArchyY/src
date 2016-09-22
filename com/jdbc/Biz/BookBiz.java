package com.jdbc.Biz;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jdbc.CostumorDAO.BookDAO;
import com.jdbc.entity.Book;
import com.jdbc.jdbctools.JDBCTools;

public class BookBiz {
  BookDAO bDAO=new BookDAO();
 
  public Book getBook(Book book) throws Exception{
	  String isbn=book.getIsbn();
      Book tempBook=null;
      try {
		if(bDAO.getBook(isbn)!=null){
			  tempBook=bDAO.getBook(isbn);
			  return tempBook;
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	  return tempBook;
      
  } 	

   public List<Book> getForlist(Object...args) throws Exception{
	  List<Book> bookList=null;
	  try {
		if(bDAO.getForList(args)!=null){
            bookList=bDAO.getForList(args);			
			return bookList;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  finally{
		  JDBCTools.release(bDAO.getConnection());
	  }
	return bookList;   
   }
   
   public Book getBook(String isbn) {
	   Book book=null;
	   try {
		book=bDAO.getBook(isbn);
		if(book!=null){
			return book;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return book;

   }
   
   
   public void updateBCount(Book book) throws Exception{
	   int bKCount=book.getBkCount();
	   String isbn=book.getIsbn();
	   
	   try {
		bDAO.updateBCount(bKCount,isbn);
	} catch (Exception e) {
	   throw e;
	}finally{
		JDBCTools.release(bDAO.getConnection());
	}
   }
   
   public List<Book> getBooks(Map<String, Integer> shopCar){
	   List<Book> books=new ArrayList<>();
	   for(String isbn:shopCar.keySet()){
		   Book book=new Book();
		   book=getBook(isbn);            //watch out.
	       int num=shopCar.get(isbn);
	       book.setBuyCount(num);
	       books.add(book);
	   }
	   return books;
   }
   
   public double getSumCost(Map<String, Integer> shopCar){
	   double sum=0;
	   for(String isbn:shopCar.keySet()){
		   double price=getBook(isbn).getPrice();
	       int num=shopCar.get(isbn);
	       sum+=price*num;
	   }
	   return sum;
   }

	
   
   
   
   
   public void bookAdd(Book book) throws Exception{
		String isbn=book.getIsbn();
		String bName=book.getbName();
		String press=book.getPress();
		double price=book.getPrice();
		String author=book.getAuthor();
		Date printDate =new Date(book.getPrintDate().getTime());
		byte[] pic=book.getPic();
		try {
			bDAO.addBook(isbn,bName,press,price,printDate,author,pic);
		} catch (Exception e) {
		   throw e;
		}finally{
			JDBCTools.release(bDAO.getConnection());
		}
	}
   
   //called by BookAddServlet.
   public void updateBKCount(Book book) throws Exception {
    String isbn=book.getIsbn();
    int bkCount=book.getBkCount();
    
    try {
		bDAO.updateBKCount(isbn,bkCount);
	} catch (Exception e) {
		throw e;
	}finally{
		JDBCTools.release(bDAO.getConnection());
	}
}
   public void  alterPrice(String isbn,double price) throws Exception {
	try {
		bDAO.alterPrice(isbn, price);
	} catch (Exception e) {
	   throw e;
	}finally{
		JDBCTools.release(bDAO.getConnection());
	}
}
   public void alterStatus(String isbn) throws Exception {
	try {
		bDAO.alterStatus(isbn);
	} catch (Exception e) {
	 throw e;
	}finally{
		JDBCTools.release(bDAO.getConnection());
	}
}
   
}
  

