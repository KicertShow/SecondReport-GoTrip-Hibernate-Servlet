package dao;

import java.util.List;

import model.MemberBean;

public interface DAO<T>{
		
		public boolean isDup(String email);
	
//		public List<T> selectAll(); //選取全部
//		
//		public T selectOne(int id); //依id選取
//
//		public boolean insert(T t); //新增Data，回傳boolean以讓controller可以接收執行成功與否的資訊
//		
//		public boolean update(T t); //更新Data，回傳boolean以讓controller可以接收執行成功與否的資訊
//		
//		public boolean delete(int id); //刪除Data，回傳boolean以讓controller可以接收執行成功與否的資訊

		int save(MemberBean mb);

		List<MemberBean> getAllMembers();

		MemberBean getMember(int user_no);

		int deleteMember(int user_no);

		int updateMember(MemberBean mb);
		
	
}
