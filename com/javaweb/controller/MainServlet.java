package com.javaweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BookBiz;
import com.jdbc.Biz.UsersBiz;
import com.jdbc.entity.Book;
import com.jdbc.entity.Users;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Cookie[] cookies=request.getCookies();
        String username=null;
        String password=null;
        for(Cookie c:cookies){
			String name=c.getName();
			if(name.equals("username")){
				
			String []str=c.getValue().split("-");
			
			username=str[0];
			password=str[1];
			}
		}
		Users user=new Users();
		user.setUsername(username);
		user.setPassword(password);
		UsersBiz uBiz=new UsersBiz();
		try {
			user=uBiz.login(user);
			if(user!=null){
				request.getSession().setAttribute("user", user);
				request.setAttribute("name", username);
				request.getSession().setAttribute("type", user.getChara());
		}
		
		else{
//			c.setMaxAge(0);
		}
		doPost(request, response);
	   
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	 List<Book> books=null;
	 BookBiz biz=new BookBiz();
	 try {
		books=biz.getForlist();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 String message="No available books";
	 if(books!=null){
		 request.setAttribute("books", books);
		 request.getRequestDispatcher("main/main.jsp").forward(request, response);;
	 }else{
		 request.setAttribute("msg", message);
		 request.getRequestDispatcher("main/error.jsp").forward(request, response);;
	 }
	}

}
