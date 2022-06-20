package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.ViewBean;
import util.HibernateUtils;

public class HibernateDaoImpl implements View_DAO {
	SessionFactory factory;
	//如果poem沒加hibernate標示就會找不到sessionfactory,一定要記得加標籤財會引入jar檔
	public HibernateDaoImpl() {
		//取得合法工廠
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public ViewBean selectOne(int prod_no) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ViewBean p WHERE p.prod_no = :pid";
		List<ViewBean> view_prods = session.createQuery(hql, ViewBean.class).setParameter("pid", prod_no).getResultList();
		return view_prods.get(0);
	}

	@Override
	public int save(ViewBean p) {
		Session session = factory.getCurrentSession();
		session.save(p);
		return 1;
	}

	@Override
	public List<ViewBean> selectAll() {
		List<ViewBean> view_prods = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM ViewBean";
		view_prods = session.createQuery(hql,ViewBean.class).getResultList();
		return view_prods;
	}

	@Override
	//int最好寫void
	//依主鍵查不要用hql會比較快
	public ViewBean getProd(int pk) {
		Session session = factory.getCurrentSession();
		ViewBean view_prods = session.get(ViewBean.class, pk);
		return view_prods;
	}

	@Override
	//int最好寫void
	public int delete(int pk) {
		Session session = factory.getCurrentSession();
		ViewBean view_prod = new ViewBean();
		view_prod.setProd_no(pk);
		session.delete(view_prod);
		return 1;
	}

	@Override
	//int最好寫void
	public int update(ViewBean p) {
		Session session = factory.getCurrentSession();
		session.update(p);
		return 1;
	}

}
