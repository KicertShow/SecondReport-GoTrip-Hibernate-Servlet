package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.OrderDaoFace;
import model.OrderBean;
import util.HibernateUtils;

public class OrderHibernateDao implements OrderDaoFace {
	SessionFactory factory;
	public OrderHibernateDao() {
		factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public List<OrderBean> selectAll() throws Exception {
		List<OrderBean> orderBeans;
		
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean";
		orderBeans = session.createQuery(hql, OrderBean.class)
								.getResultList();
		return orderBeans;
		
	}

	@Override
	public void insert(OrderBean orderBean) throws Exception {
		Session session = factory.getCurrentSession();
		session.save(orderBean);
	}

	@Override
	public OrderBean selectOne(int orderId) throws Exception {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderBean o WHERE o.orderId = :orderid";
		OrderBean orderBean = session.createQuery(hql, OrderBean.class)
						.setParameter("orderid", orderId)
						.getSingleResult();
		
		return orderBean;
	}

	@Override
	public void update(OrderBean orderBean) throws Exception {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(orderBean);
	}

	@Override
	public void delete(int orderid) throws Exception {
		Session session = factory.getCurrentSession();
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(orderid);
		session.delete(orderBean);
		
	}

}
