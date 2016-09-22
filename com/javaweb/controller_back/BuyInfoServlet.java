package com.javaweb.controller_back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.Biz.BuyInfoBiz;
import com.jdbc.Biz.UsersBiz;
import com.jdbc.DTO.BuyInfoList;
import com.jdbc.DTO.SeparatePage;
import com.jspsmart.upload.Request;

/**
 * Servlet implementation class BuyInfoServlet
 */
public class BuyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<BuyInfoList> list=null;
	BuyInfoBiz biz=new BuyInfoBiz();
	int currentPage;
	
	String page=request.getParameter("page");
    String jumpPage=request.getParameter("jumpPage");
    String username=request.getParameter("username");
	String startDate=request.getParameter("startDate");
	String endDate=request.getParameter("endDate");
    
	if(username==null || username.equals("")){
		username=null;
	}
	
	if( startDate==null||startDate.equals("")){
		startDate=null;
	}
	
	if(endDate==null || endDate.equals("")){
		endDate=null;
	}
	if(jumpPage==null || jumpPage.equals("")){
		jumpPage=null;
	}
	

    
	if(page==null){
    	currentPage=1;
    }else{
    	currentPage=Integer.parseInt(page);
    }
	
	if(jumpPage!=null){
		currentPage=Integer.parseInt(jumpPage);
	}
	
	int allRecordCount;
	SeparatePage sp=new SeparatePage();
	int pageSum=2;
	sp.pageSum=pageSum;
	
	
	try {
//		allRecordCount=uBiz.getAllRecord();
		
		
		sp.currentPage=currentPage;
		list=biz.getBuyInfoList(sp,username,startDate,endDate);
		allRecordCount=sp.allPages;
		sp.totalNum=allRecordCount;
		int allPages=((allRecordCount-1)/pageSum)+1;
		sp.allPages=allPages;
		
		int endPage=allPages;
		
		if(currentPage>=allPages){
			currentPage=endPage;
		}
		if(currentPage<1){
			currentPage=1;
		}
		/*sp.currentPage=currentPage;
		list=biz.getBuyInfoList(sp,username,startDate,endDate);*/
		request.setAttribute("currentPage",currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("allPages", allPages);
		request.setAttribute("list", list);
		request.setAttribute("allRecordCount", allRecordCount);
		request.setAttribute("username", username);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.getRequestDispatcher("main/buyInfoList.jsp").forward(request, response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}
	


}
