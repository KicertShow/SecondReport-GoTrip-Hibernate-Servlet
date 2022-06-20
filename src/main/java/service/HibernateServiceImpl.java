package service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.HibernateDaoImpl;
import dao.View_DAO;
import model.ViewBean;
import util.HibernateUtils;

public class HibernateServiceImpl implements ViewService {

	SessionFactory factory;
	View_DAO viewDAO;

	// 建構子
	public HibernateServiceImpl() {
//		super();
		this.factory = HibernateUtils.getSessionFactory();
		this.viewDAO = new HibernateDaoImpl();
	}

	@Override
	public ViewBean selectOne(int prod_no) {
		ViewBean viewBean = null;
		//啟動交易一定要由SESSION啟動
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			viewBean = viewDAO.selectOne(prod_no);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return viewBean;
//		MemberDao dao = new MemberJdbcDaoImpl();
//		return dao.isDup(id);
	}

	@Override
	public int save(ViewBean p) {
		// 黃色:區域變數 ; 淺藍:實體變數
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = viewDAO.save(p);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				//dead code永遠run不到的敘述
				tx.rollback();
			} 
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public List<ViewBean> selectAll() {
		List<ViewBean> viewBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			viewBeans = viewDAO.selectAll();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return viewBeans;
	}

	@Override
	public ViewBean getProd(int pk) {
		ViewBean viewBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			viewBean = viewDAO.getProd(pk);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return viewBean;
	}

	@Override
	public int delete(int prod_no) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = viewDAO.delete(prod_no);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return n;
	}

	@Override
	public int update(ViewBean p) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = viewDAO.update(p);
			System.out.println("update:" + p);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return n;
	}

}
