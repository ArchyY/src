package com.javaweb.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.org.mozilla.javascript.internal.ast.NewExpression;

import com.jdbc.Biz.BookBiz;
import com.jdbc.Biz.UsersBiz;
import com.jdbc.CostumorDAO.UsersDAO;
import com.jdbc.entity.Book;
import com.jdbc.entity.Users;

/**
 * Servlet implementation class PurchaseServlet
 */
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookBiz biz=new BookBiz();
		Book book=new Book();
		List<Book> books=new ArrayList<>();
		double sum=0;
		@SuppressWarnings("unchecked")
		Map<String, Integer> shopCar=(Map<String, Integer>) request.getSession().getAttribute("shopCar");
		for(String i:shopCar.keySet()){
			book=biz.getBook(i);
			int number=Integer.parseInt(request.getParameter(i));
			book.setBuyCount(number);
			shopCar.put(i, number);
			double p=book.getPrice();
			sum=sum+p*number;
			books.add(book);
		}
		
		Users user=(Users) request.getSession().getAttribute("user");
		double balance=0;
		try {
			balance = new UsersBiz().getBalance(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("books", books);
		request.setAttribute("sumCost", sum);
		request.setAttribute("balance",balance) ;
//		request.getSession().setAttribute("shopCar", shopCar);
	    request.getRequestDispatcher("/main/checkOut.jsp").forward(request, response);
	}

}
