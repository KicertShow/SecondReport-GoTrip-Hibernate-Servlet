package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.DAO;
import dao.MemberDAO;
import model.MemberBean;
import service.MemberService;
import util.HibernateUtils;

public class MemberHibernateServiceImpl implements MemberService {

	SessionFactory factory;
	DAO DAO;
	
    public MemberHibernateServiceImpl() {
		this.factory = HibernateUtils.getSessionFactory();
		this.DAO = new MemberDAO();
	}

	@Override
	public boolean isDup(String id) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = DAO.isDup(id);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return result;
	}

	@Override
	public int save(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = DAO.save(mb);
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
	public List<MemberBean> getAllMembers() {
		List<MemberBean> memberBeans = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			memberBeans = DAO.getAllMembers();
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		
		return memberBeans;
	}

	@Override
	public MemberBean getMember(int user_no) {
		MemberBean memberBean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			memberBean = DAO.getMember(user_no);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(ex);
		}
		return memberBean;
	}

	@Override
	public int deleteMember(int user_no) {
		
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = DAO.deleteMember(user_no);
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
	public int updateMember(MemberBean mb) {
		
		int n = 0;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			n = DAO.updateMember(mb);
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
