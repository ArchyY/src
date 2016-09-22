package com.javaweb.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.tomcat.jni.User;

public class GuestCountListner implements HttpSessionListener {

	public GuestCountListner() {

	}

	public void sessionCreated(HttpSessionEvent se) {
		@SuppressWarnings("unchecked")
		Map<String, User> userMap=(Map<String, User>) se.getSession().getServletContext().getAttribute("UserMap");
         
         if(userMap==null){
        	 userMap=new HashMap<String, User>();
         }
                String sessionId=se.getSession().getId();
            	User user=(User) se.getSession().getAttribute("user");
            	userMap.put(sessionId, user);
                se.getSession().getServletContext().setAttribute("UserMap",userMap);
            	int guestCounts=userMap.size();
             	se.getSession().getServletContext().setAttribute("guestsCounts",guestCounts);
        	 }
 

		
	/*	Object objCount=se.getSession().getServletContext().getAttribute("guestsCounts");
        if(objCount==null){
        	Object objC=se.getSession().getServletContext().getAttribute("guestsCounts",1);
        }  */
    
    

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
