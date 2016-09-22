package com.javaweb.controller_back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;
import com.jdbc.entity.Book;

/**
 * Servlet implementation class BKAddServlet
 */
public class BKAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   String isbn=request.getParameter("isbn");	
   String bkCount=request.getParameter("num");
   Book book=new Book();
   book.setIsbn(isbn);
   int tmep=Integer.parseInt(bkCount);	
   book.setBkCount(tmep);
   BookBiz biz=new BookBiz();
   
   try {
	biz.updateBKCount(book);
	 String msg="Update Successfully";
	    request.setAttribute("msg", msg);
	    request.getRequestDispatcher("main/bkCountAdd.jsp").forward(request, response);
} catch (Exception e) {
	e.printStackTrace();
}
   
   }
	
}
