package com.javaweb.controller_back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.UsersBiz;
import com.jdbc.CostumorDAO.UsersDAO;
import com.jdbc.entity.Users;

/**
 * Servlet implementation class UsersManagmentServlet
 */
public class UsersManagmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UsersBiz biz=new UsersBiz();
	List<Users> users=new ArrayList<>();
	
	try {
		users=biz.getUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("main/UsersManagment.jsp").forward(request, response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}



}
