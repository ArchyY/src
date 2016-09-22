package com.jdbc.Biz;

import java.io.IOException;
import java.sql.SQLException;

import com.jdbc.CostumorDAO.PicDAO;
import com.jdbc.jdbctools.JDBCTools;

public class PicBiz {
    PicDAO picDAO=new PicDAO();
    
    public byte[] getPic(String isbn) throws Exception{
	    JDBCTools.release(picDAO.getConnection());
    	return	picDAO.getBookPic(isbn);
    }
}
