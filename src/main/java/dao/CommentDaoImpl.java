package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CommentBean;
import util.HibernateUtils;

public class CommentDaoImpl implements CommentDao{
	
	SessionFactory factory;

	public CommentDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<CommentBean> getAllComments() {
		List<CommentBean> comments = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM CommentBean";
		comments = session.createQuery(hql, CommentBean.class)
							  .getResultList();
		return comments;
	}

	@Override
	public CommentBean getComment(int id) {
		Session session = factory.getCurrentSession();
		CommentBean comment = session.get(CommentBean.class, id);
		return comment;
	}
	
	@Override
	public void saveComment(CommentBean comment) {
		Session session = factory.getCurrentSession();
		session.save(comment);
	}

	@Override
	public void deleteComment(int id) {
		Session session = factory.getCurrentSession();
		CommentBean comment = new CommentBean();
		comment.setId(id);
		session.delete(comment);
	}

	@Override
	public void updateComment(CommentBean comment) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(comment);
	}

}
