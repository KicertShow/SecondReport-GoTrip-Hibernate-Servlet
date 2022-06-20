package service;

import java.util.List;

import model.OrderBean;

public interface OrderService {
	
	public List<OrderBean> selectAll();
	
	public void insert(OrderBean orderBean);
	
	public OrderBean selectOne(int orderId);
	
	public void update(OrderBean orderBean);
	
	public void delete(int orderid);
}
