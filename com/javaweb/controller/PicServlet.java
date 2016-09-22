package com.javaweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.PicBiz;

/**
 * Servlet implementation class PicServlet
 */
public class PicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String isbn=request.getParameter("isbn");
    byte b[]=null;
    PicBiz biz=new PicBiz();
    try {
		b=biz.getPic(isbn);
	    if(b!=null){
	    	ServletOutputStream stream=response.getOutputStream();
            stream.write(b);
            stream.flush();
            stream.close();
	    }
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		request.setAttribute("msg", "PLEASE CONTACT TECHNOLOGY SUPPORT DEPARTMENT");
		request.getRequestDispatcher("main/error.jsp").forward(request, response);
	}
	}

}
