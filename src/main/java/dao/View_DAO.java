package dao;

import java.util.List;

import model.ViewBean;

public interface View_DAO {

	ViewBean selectOne(int prod_no);

	int save(ViewBean p);

	List<ViewBean> selectAll();

	ViewBean getProd(int prod_no);

	int delete(int prod_no);

	int update(ViewBean p);
}