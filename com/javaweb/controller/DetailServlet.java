package com.javaweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;
import com.jdbc.entity.Book;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn=request.getParameter("isbn");
//        byte b[]=null;
	    Book book=new Book();
	    book.setIsbn(isbn);
        BookBiz biz=new BookBiz();
//        PicBiz picBiz=new PicBiz();
     /*   try {
			b=picBiz.getPic(isbn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    try {
			if(biz.getBook(book)!=null){
			/*	if(b!=null){
					ServletOutputStream stream=response.getOutputStream();
				    stream.write(b);
				    stream.flush();
				    stream.close();
				    }*/
				book=biz.getBook(book);
				request.setAttribute("book", book);
				request.getRequestDispatcher("main/bookDetail.jsp").forward(request, response);
			}else{
				request.setAttribute("msg","PLEASE CONTACT TECHNOLOGY STUFF.");
				request.getRequestDispatcher("main/error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
