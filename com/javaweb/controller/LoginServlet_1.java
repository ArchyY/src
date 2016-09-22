package com.javaweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import org.omg.CORBA.PUBLIC_MEMBER;

import com.jdbc.Biz.UsersBiz;
import com.jdbc.entity.*;

/**
 * Servlet implementation class LoginServlet_1
 */
public class LoginServlet_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet_1(){
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String message="login failed";
		String userCookie=username+"-"+password;
		if(username!=null && !username.trim().equals("")){
			Cookie cookie=new Cookie("username", userCookie);
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
		}
//		System.out.println(username+password);
		Users user=new Users();
		user.setUsername(username);
		user.setPassword(password);
		UsersBiz biz=new UsersBiz();
		try {
			user=biz.login(user);
			request.getSession().setAttribute("user", user);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if(user!=null){
			request.setAttribute("name", username);
			request.getSession().setAttribute("type", user.getChara());
			request.getRequestDispatcher("MainServlet").forward(request, response);
			
		}else{
			request.setAttribute("msg", message);
			request.getRequestDispatcher("main/login.jsp").forward(request, response);
		}
		
	}

}
