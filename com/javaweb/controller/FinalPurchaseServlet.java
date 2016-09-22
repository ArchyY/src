package com.javaweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.exception.InsufficientBalanceException;
import com.jdbc.Biz.BookBiz;
import com.jdbc.Biz.UsersBiz;
import com.jdbc.entity.Book;
import com.jdbc.entity.BuyDetail;
import com.jdbc.entity.BuyRecord;
import com.jdbc.entity.Users;

/**
 * Servlet implementation class FinalPurchaseServlet
 */
public class FinalPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FinalPurchaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<String, Integer> shopCar = (Map<String, Integer>) request
				.getSession().getAttribute("shopCar");
		Users user = (Users) request.getSession().getAttribute("user");
		BookBiz biz = new BookBiz();
		UsersBiz usersBiz=new UsersBiz();
		List<Book> books=new ArrayList<>();
		
		
		try {
			books=usersBiz.addBuyRecord(shopCar, user);
		    double sum=biz.getSumCost(shopCar);	
		    double balance=usersBiz.getBalance(user);
//			Boolean order=true;
			
			request.setAttribute("books", books);
			request.setAttribute("sumCost", sum);
			request.setAttribute("balance", balance);
//			request.setAttribute("order", order);
			request.getRequestDispatcher("/main/checkOutOk.jsp").forward(request,
					response);
		} /*catch (InsufficientBalanceException e) {
			String msg=e.getMessage();
			e.printStackTrace();
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("main/error.jsp").forward(request, response);
		 	
		}*/catch (Exception e) {
			String msg=e.getMessage();
			e.printStackTrace();
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("main/error.jsp").forward(request, response);
       }
		
		

	}

}
