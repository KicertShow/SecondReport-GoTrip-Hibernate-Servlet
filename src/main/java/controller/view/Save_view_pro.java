package controller.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.HibernateServiceImpl;
import service.ViewService;
import model.ViewBean;

@WebServlet("/Save_view_pro")
public class Save_view_pro extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String prod_name = request.getParameter("prod_name");
		String prod_Intro = request.getParameter("prod_Intro");
//		轉碼問題
		String open_time = request.getParameter("open_time");
		String end_time = request.getParameter("end_time");
		String phone = request.getParameter("phone");
		String Country = request.getParameter("Country");
		String City = request.getParameter("City");
		String Location = request.getParameter("Location");
		String address = request.getParameter("address");
		String priceStr = request.getParameter("price");
		
		StringBuilder sb = new StringBuilder();
		for(String key : request.getParameterMap().keySet()) {
			if(key.contains("week")) {
				sb.append(key.substring(4)).append(',');
			}
		}
		String open_week =  sb.length() > 0 ? sb.substring(0, sb.length()-1) : "" ;
		System.out.println("open_week->"+open_week);
//		轉碼問題	debug	
		for (String key : request.getParameterMap().keySet()) {
			System.out.println("KEY:"+key+",VALUE:"+request.getParameter(key));
		} 
		int price = Integer.parseInt(priceStr);
//		byte[] image = request.getParameter("image").getBytes("UTF-8");
//		String image_Intro = request.getParameter("image_Intro");
		//測試有無走到這步
//		System.out.println("image_Intro :"+image_Intro);

		ViewBean p = new ViewBean();
		p.setProd_name(prod_name);
		p.setProd_Intro(prod_Intro);
		p.setOpen_week(open_week);
		p.setOpen_time(open_time);
		p.setEnd_time(end_time);
		p.setPhone(phone);
		p.setCountry(Country);
		p.setCity(City);
		p.setLocation(Location);
		p.setAddress(address);
		p.setPrice(price);
//		p.setImage(image);
//		p.setImage_Intro(image_Intro);
		

		ViewService vs = new HibernateServiceImpl();
		
//		ViewBean VB = new ViewBean(prod_name,prod_Intro,open_week,open_time,end_time
//				,phone,Country,City,Location,address,price);
		
		int status = vs.save(p);
		if (status > 0) {
			response.sendRedirect("View_pro");
		} else {
			out.println("對不起，保存失敗！");
			response.sendRedirect("View_pro");
		}

		out.close();
	}
}
