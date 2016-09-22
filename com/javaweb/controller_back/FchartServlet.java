package com.javaweb.controller_back;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.jdbc.Biz.FChartBiz;
import com.jdbc.DTO.FChart;

/**
 * Servlet implementation class FchartServlet
 */
public class FchartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FchartServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
		OutputStream out=response.getOutputStream();
	    String type=request.getParameter("type");
	    FChartBiz biz=new FChartBiz();
	    
	    List<FChart> charts=null;
	    
	    try {
			charts=biz.getCharts(null,null);
	    if(type!=null){
	    	if(type.equals("1")){
	    	JFreeChart chart=ChartFactory.createPieChart("Pie Sample",biz.getPieChartData(charts));
	    		ChartUtilities.writeChartAsPNG(out, chart, 800, 500);
	    	}
	    	if(type.equals("2")){
	    		JFreeChart chart=ChartFactory.createBarChart("Bar Sample","categroy","value",biz.getBarChartData(charts));
	    		ChartUtilities.writeChartAsPNG(out, chart, 800, 500);
	    	}
	    	if(type.equals("3")){
	    		JFreeChart chart=ChartFactory.createXYLineChart("Line Sample",null,"value",biz.getLineChartData(charts));
	    		ChartUtilities.writeChartAsPNG(out, chart, 800, 500);
	    	}
	    	
	    }else{
	    	type="1";
	    	JFreeChart chart=ChartFactory.createPieChart("Pie Sample",biz.getPieChartData(charts));
    		ChartUtilities.writeChartAsPNG(out, chart, 800, 500);
	    }
	    request.setAttribute("ChartType", type);
	    out.flush();
	    out.close();
	    } catch (Exception e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String value=request.getParameter("pie");
    request.setAttribute("ChartType", value);
    System.out.println(value);
	request.getRequestDispatcher("main/topSales.jsp").forward(request, response);
	}

}
