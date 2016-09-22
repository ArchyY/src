package com.javaweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;
import com.jdbc.entity.Book;

/**
 * Servlet implementation class AddShopCar
 */
public class AddSCServlet extends HttpServlet {
/*	private Map<String,String> shopCar= new HashMap<>(); 
	private List<Book> books=new ArrayList<>();
*/	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookBiz biz=new BookBiz();
		Book book=new Book();
		@SuppressWarnings("unchecked")
		Map<String,Integer> shopCar=(Map<String, Integer>) request.getSession().getAttribute("shopCar");
		if(shopCar==null){
			shopCar=new HashMap<>();
		}
//		System.out.println(shopCar);
		
		String isbn=request.getParameter("isbn");
		
	/*	String bookNum=request.getParameter("number");
		Integer num=Integer.parseInt(bookNum);
		for(String i:shopCar.keySet()){
			if(isbn.equals(i)){
            book=biz.getBook(i);
            book.addBuyCount();				
			num+=book.getBuyCount();
			}
		}*/
		if(isbn!=null){
		shopCar.put(isbn,1);
		}
		List<Book> books=new ArrayList<>();
		
        
		
		for(String i:shopCar.keySet()){
			book=biz.getBook(i);
			book.setBuyCount(shopCar.get(i));
			books.add(book);
			
		}
		
		request.setAttribute("books", books);
	    request.getSession().setAttribute("shopCar", shopCar);
		
		
		request.getRequestDispatcher("main/shopCar.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	    
	}

}
