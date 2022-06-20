package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.CommentDao;
import dao.CommentDaoImpl;
import model.CommentBean;
import util.HibernateUtils;

public class CommentServiceImpl implements CommentService{
	
	SessionFactory factory;
	CommentDao commentDao;

	public CommentServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		commentDao = new CommentDaoImpl();
	}

	@Override
	public List<CommentBean> getAllComments() {
		List<CommentBean> comments = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			comments = commentDao.getAllComments();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return comments;
	}

	@Override
	public CommentBean getComment(int id) {
		CommentBean comment = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			comment = commentDao.getComment(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		} 
		return comment;
	}

	@Override
	public boolean saveComment(CommentBean comment) {
		boolean isSaved = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			commentDao.saveComment(comment);
			tx.commit();
			isSaved = true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return isSaved;
	}

	@Override
	public boolean deleteComment(int id) {
		boolean isDeleted = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			commentDao.deleteComment(id);
			tx.commit();
			isDeleted = true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return isDeleted;
	}

	@Override
	public boolean updateComment(CommentBean comment) {
		boolean isUpdated = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			commentDao.updateComment(comment);
			tx.commit();
			isUpdated = true;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw new RuntimeException(e);
		}
		return isUpdated;
		
	}

}
