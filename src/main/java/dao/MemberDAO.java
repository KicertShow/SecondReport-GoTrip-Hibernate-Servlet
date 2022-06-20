package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.MemberBean;

public class MemberDAO implements DAO<MemberBean> {
	
	SessionFactory factory;
	
	public MemberDAO() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public boolean isDup(String email) {
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean mb WHERE mb.email = :email";
		List<MemberBean> memberBeans = session.createQuery(hql, MemberBean.class)
											  .setParameter("email", email)
											  .getResultList();
		if (memberBeans.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int save(MemberBean mb) {
		Session session = factory.getCurrentSession();
		session.save(mb);
		return 1;
	}

	@Override
	public List<MemberBean> getAllMembers() {
		List<MemberBean> memberBeans = null; 
		
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean";
		memberBeans = session.createQuery(hql, MemberBean.class)
				             .getResultList();
		return memberBeans;
	}

	@Override
	public MemberBean getMember(int user_no) {
		Session session = factory.getCurrentSession();
		MemberBean memberBean = session.get(MemberBean.class, user_no);
		return memberBean;
	}

	@Override
	public int deleteMember(int user_no) {
		Session session = factory.getCurrentSession();
		MemberBean memberBean = new MemberBean();
		memberBean.setUser_no(user_no);
		session.delete(memberBean);
		return 1;
	}

	@Override
	public int updateMember(MemberBean mb) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(mb);
		return 1;
	}
	



	


}
