package com.jdbc.Biz;


import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.jdbc.CostumorDAO.FChartDAO;
import com.jdbc.DTO.FChart;
import com.jdbc.jdbctools.JDBCTools;

public class FChartBiz {
	FChartDAO fDao=new FChartDAO();
	
	public List<FChart> getCharts(String startDate,String endDate) throws Exception{
         List<FChart> charts=null;   
		try {
				
            charts=	fDao.getCharts(startDate, endDate);
			
			} catch (Exception e) {
			   throw e;
			 }finally{
				 JDBCTools.release(fDao.getConnection());
				 }
		return charts;
	}
	
	
	public DefaultPieDataset getPieChartData(List<FChart> charts){
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(FChart c:charts){
		dataset.setValue(c.getbName(),c.getbCount());
		}
		return dataset;		
	}
	
	public DefaultCategoryDataset  getBarChartData(List<FChart> charts){
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int num=1;
		for(FChart c:charts){
			dataset.setValue(c.getbCount(),"row"+num,c.getbName());
		    num++;	
		}
		return dataset;
		
	}
	public XYDataset  getLineChartData(List<FChart> charts){
		
		XYSeries xy = new XYSeries("first");		
		int num=10;
		for(FChart c:charts){
			xy.add(num,c.getbCount());
		    num+=10;	
		}	
		XYSeriesCollection xycollection = new XYSeriesCollection();
		xycollection.addSeries(xy);
		return xycollection;
	}
}
