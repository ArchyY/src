package com.javaweb.controller_back;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;
import com.jdbc.entity.Book;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class BookAdd
 */
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
	
    SmartUpload smart=new SmartUpload();
    smart.initialize(getServletConfig(), request, response);
	smart.setCharset("utf-8");
	smart.setMaxFileSize(100*1024);
	smart.setAllowedFilesList("jpg,gif,png,bmp");
	
	try {
		smart.upload();
		Request r=smart.getRequest();
		String isbn=r.getParameter("isbn");
		String bName=r.getParameter("bName");
		String author=r.getParameter("author");
	    String press=r.getParameter("press");
	    String printDate=r.getParameter("printDate");
	    SimpleDateFormat pattern=new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date date=pattern.parse(printDate);
	    double price=Double.parseDouble(r.getParameter("price"));
	    File file=smart.getFiles().getFile(0);
	    byte pic[]=new byte[file.getSize()];
	    for(int i=0;i<file.getSize();i++){
	    	pic[i]=file.getBinaryData(i);
	    }
	    Book book=new Book();
	    book.setIsbn(isbn);
	    book.setAuthor(author);
	    book.setbName(bName);
	    book.setPress(press);
	    book.setPrice(price);
	    book.setPrintDate(date);
	    book.setPic(pic);
	    BookBiz biz=new BookBiz();
	    biz.bookAdd(book);
	    String msg="Update Successfully";
	    request.setAttribute("msg", msg);
	    request.getRequestDispatcher("main/bookAdd.jsp").forward(request, response);
	  
	} catch (SmartUploadException e) {
		e.printStackTrace();
		String msg="Inner Error Occurred";
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("main/error.jsp");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
