package com.jdbc.CostumorDAO;

import java.util.List;

import com.jdbc.DAO.DAO;
import com.jdbc.DTO.FChart;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.bcel.internal.generic.RETURN;



public class FChartDAO extends DAO<FChart>{
   
	public List<FChart> getCharts(String startDate, String endDate) throws Exception {
	String sql="select * from (select rownum rn, e.* from ("
   		+ getSQL(startDate,endDate)+ ") e) where"
   		+ " rn<=3 order by bcount desc";
	return getBeanList(sql);
	}

	public String getSQL(String startDate, String endDate){
		String sql="select tbook.bname,sum(tbuydetail.bcount) bcount "
				+ "from tbook, tbuydetail,tbuyrecord where "
				+ "tbook.isbn = tbuydetail.isbn and "
				+ "tbuyrecord.buyid = tbuydetail.buyid group by bname";
				
	if(startDate!=null && startDate.trim()!=""){
		 sql= sql+" and to_char(buytime,'yyyy-MM-dd')>="+startDate; 
	}	
	if(endDate !=null && endDate.trim()!=""){
		sql= sql+" and to_char(buytime,'yyyy-MM-dd')<="+endDate;
	}
	   return sql;
	}
  

}
