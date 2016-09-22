package com.jdbc.CostumorDAO;

import java.math.BigDecimal;
import java.util.List;

import com.jdbc.DAO.DAO;
import com.jdbc.DTO.BuyInfoList;
import com.jdbc.DTO.SeparatePage;

public class BuyInfoDAO extends DAO<BuyInfoList> {
	private String sql;
	
	public List<BuyInfoList> getBuyInfoList(SeparatePage sp, String username, String startDate, String endDate) throws Exception {
		
		String sql = "select username, bname, bcount, price, press,author, buytime as buydate,allmoney,buyCount from tusers u, tbook b, tbuyrecord r, tbuydetail d where u.userid=r.userid and b.isbn=d.isbn and r.buyid=d.buyid";
		if(username!=null){
			sql=sql+" and username="+"'"+username+"'";		
		}
		if(startDate!=null){
			sql=sql+" and to_char(buytime,'yyyy-MM-dd') >="+"'"+startDate+"'";
		}
		if(endDate!=null){
			sql=sql+" and to_char(buytime,'yyyy-MM-dd') <="+"'"+endDate+"'";
		}
		  sp.setAllPages(getAllRecord(sql));
	
		return getBeanList(getSQL(sql,sp.startPoint,sp.pageSum));
		}

    public String getSQL(String sql, int startPoint, int sum) {
		int endPoint=startPoint+sum-1;
    	String nSql="select * from (select rownum rn, e.* from ("+sql+" ) e) enum where rn>="+startPoint+" and rn<="+endPoint;
	    return nSql;
    }
    
    public int getAllRecord(String temSql) throws Exception{
		String sql="select count(*) from"+" ("+temSql+")";
	    return (int)((BigDecimal)getForValue(sql)).longValue();
	}




}
