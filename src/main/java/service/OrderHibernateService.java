package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.impl.OrderHibernateDao;
import model.OrderBean;
import util.HibernateUtils;

public class OrderHibernateService implements OrderService {
	SessionFactory factory;	
	OrderHibernateDao orderHibernateDao;
	
	public OrderHibernateService() {
		super();
		this.factory = HibernateUtils.getSessionFactory();
		this.orderHibernateDao = new OrderHibernateDao();
	}

	@Override
	public List<OrderBean> selectAll() {
		List<OrderBean> orderBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			orderBeans = orderHibernateDao.selectAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}		
		
		return orderBeans;
	}

	@Override
	public void insert(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			orderHibernateDao.insert(orderBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}

	}

	@Override
	public OrderBean selectOne(int orderId) {
		OrderBean orderBean;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		 	orderBean = orderHibernateDao.selectOne(orderId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}		
		
		return orderBean;
	}

	@Override
	public void update(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		 	orderHibernateDao.update(orderBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}		

	}

	@Override
	public void delete(int orderid) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		 	orderHibernateDao.delete(orderid);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}		
	}

}
