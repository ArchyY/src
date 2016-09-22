package com.javaweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;
import com.jdbc.entity.Book;

/**
 * Servlet implementation class RemoveBookServlet
 */
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Integer> shopCar=(Map<String, Integer>) request.getSession().getAttribute("shopCar");
	    String isbn=request.getParameter("isbn");
	    shopCar.remove(isbn);
        Book book=null;
        List<Book> books=new ArrayList<>();
        BookBiz biz=new BookBiz();
		for(String i:shopCar.keySet()){
			book=biz.getBook(i);
			books.add(book);
			
		}
		
		request.setAttribute("books", books);
	    request.getRequestDispatcher("main/shopCar.jsp").forward(request, response);
	}

}
