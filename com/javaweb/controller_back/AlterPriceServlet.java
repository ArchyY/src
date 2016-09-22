package com.javaweb.controller_back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;

/**
 * Servlet implementation class AlterPriceServlet
 */
public class AlterPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn=request.getParameter("isbn");
		double price=Double.parseDouble(request.getParameter("price"));
		BookBiz biz=new BookBiz();
		try {
			biz.alterPrice(isbn, price);
			String msg="Update Successfully.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("main/alterBookPrice.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
