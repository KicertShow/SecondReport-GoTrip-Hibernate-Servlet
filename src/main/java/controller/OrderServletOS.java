package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderBean;
import service.OrderHibernateService;
import service.OrderService;

/**
 * Servlet implementation class OrderServletOS
 */
@WebServlet("/OrderServletOS")
public class OrderServletOS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService os;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		os = new OrderHibernateService();   
    /**
     * @see HttpServlet#HttpServlet()
     */}

	 public OrderServletOS() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String looker=request.getParameter("name");
		System.out.println(looker);
		if (looker == null) {
			allQuery(request, response);
		} else if (looker.equals("new")) {
			newOrderForm(request, response);
		} else if (looker.equals("insert")) {
			insertOrder(request, response);
		} else if (looker.equals("edit")) {
			editOrderForm(request, response);
		} else if (looker.equals("update")) {
			updateOrder(request, response);
		} else if (looker.equals("delete")) {
			deleteOreder(request, response);
		} 
		
		
		
	}

	public void allQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<OrderBean> orderBeans = os.selectAll();
		request.setAttribute("orderBeans", orderBeans);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/order.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void newOrderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("NewOrderForm.jsp");
	}
	
	public void insertOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
			System.out.println(timestamp);
			String ordercategory =	request.getParameter("orderCategory");
			Integer userno = (Integer.valueOf(request.getParameter("userNo")));
			Integer orderTotal = (Integer.valueOf(request.getParameter("orderTotal")));
			Integer orderDiscount = (Integer.valueOf(request.getParameter("orderDiscount")));
			Integer couponId = (Integer.valueOf(request.getParameter("couponId")));
			Integer orderStatus = (Integer.valueOf(request.getParameter("orderStatus")));
			Integer orderPaystatus = (Integer.valueOf(request.getParameter("orderPayStatus")));
			OrderBean orderBean= new OrderBean(ordercategory, userno, orderTotal, orderDiscount, couponId, orderStatus, orderPaystatus, timestamp);
			os.insert(orderBean);
			allQuery(request, response);
	}		

	 private void editOrderForm(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			        int orderid = Integer.valueOf(request.getParameter("orderid"));
			        OrderBean orderBean =os.selectOne(orderid);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("NewOrderForm.jsp");
			        request.setAttribute("order", orderBean);
			        dispatcher.forward(request, response);

			    }
 
	 private void updateOrder(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
			        OrderBean orderBean = new OrderBean();
			        orderBean.setOrderId(Integer.valueOf(request.getParameter("orderId")));
			        orderBean.setOrderDatePlus(request.getParameter("orderDatePlus"));
			        orderBean.setOrderCategory(request.getParameter("orderCategory"));
		 			orderBean.setUserNo(Integer.valueOf(request.getParameter("userNo")));
			        orderBean.setOrderTotal(Integer.valueOf(request.getParameter("orderTotal")));
			        orderBean.setOrderDiscount(Integer.valueOf(request.getParameter("orderDiscount")));
			        orderBean.setCouponId(Integer.valueOf(request.getParameter("couponId")));
			        orderBean.setOrderStatus(Integer.valueOf(request.getParameter("orderStatus")));
			        orderBean.setOrderPayStatus((Integer.valueOf(request.getParameter("orderPayStatus"))));
			        os.update(orderBean);
			        allQuery(request, response);
			        
			    }
	 private void deleteOreder(HttpServletRequest request, HttpServletResponse response)
			    throws IOException, ServletException {
			        Integer orderid = Integer.valueOf(request.getParameter("orderid"));
			        os.delete(orderid);
			        allQuery(request, response);
	 }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}


}
