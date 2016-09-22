package com.jdbc.Biz;

import java.util.List;


import com.jdbc.CostumorDAO.BuyInfoDAO;
import com.jdbc.DTO.BuyInfoList;
import com.jdbc.DTO.SeparatePage;
import com.jdbc.jdbctools.JDBCTools;

public class BuyInfoBiz {
     BuyInfoDAO bDao=new BuyInfoDAO();
     
     public List<BuyInfoList> getBuyInfoList(SeparatePage sp, String username, String startDate, String endDate) throws Exception {
    	 if(sp!=null){
    	 sp.startPoint=(sp.currentPage-1)*sp.pageSum+1;
    	 }
    	 
    	 List<BuyInfoList> list=null;
    	 try {
//    		 System.out.println(username);
			list=bDao.getBuyInfoList(sp, username, startDate, endDate);
		} catch (Exception e) {
		throw e;
		}finally{
			JDBCTools.release(bDao.getConnection());
		}
    	 return list;
	}
     
    
   
}
