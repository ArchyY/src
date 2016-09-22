package com.jdbc.CostumorDAO;

import java.io.IOException;
import java.sql.SQLException;

import com.jdbc.DAO.DAO;
import com.jdbc.entity.Book;

public class PicDAO extends DAO<Book> {
   public byte[] getBookPic(String isbn) throws Exception{
	   String sql="select pic from tbook where isbn=?";
	   return getPic(sql, isbn);
   }
	
}
