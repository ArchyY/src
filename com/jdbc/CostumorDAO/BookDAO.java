package com.jdbc.CostumorDAO;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.DAO.DAO;
import com.jdbc.entity.Book;
import com.jdbc.entity.BuyDetail;

public class BookDAO extends DAO<Book> {
    public Book getBook(Object...args) throws Exception{
	String sql="select isbn,bname,press,price,author,introduce, bkcount from tbook where isbn=?";	
    Book book=getBean(sql, args);	
    	return book;
    	}    
    
    
    public List<Book> getForList(Object...args) throws Exception{
    	String sql="select isbn,bname,price,press from tbook";
    	List<Book> bookList=getBeanList(sql, args);
		return bookList;
    }
    
    public void addBook(Object...args) throws Exception{
    	String sql="insert into tbook values (?,?,?,?,?,null,?,null,?)";
    	update(sql, args);
    }
    
    public void updateBCount(Object...args) throws Exception{
    	String sql="update tbook set bkcount=? where isbn=?";
    	update(sql, args);
    }
    // called by BookAddServlet.
    public void updateBKCount(String isbn, int bkCount) throws Exception{
		String sql="update tbook set bkcount=nvl(bkcount,0)+? where isbn=?";
	    update(sql,bkCount,isbn );
    }
    public void alterPrice(String isbn,double price) throws Exception {
		String sql="update tbook set price=? where isbn=?";
		update(sql, price, isbn);
	}
    public void alterStatus(String isbn) throws Exception{
		String sql="update tbook set status=0 where isbn=?";
		update(sql, isbn);
	}
    
 
}
