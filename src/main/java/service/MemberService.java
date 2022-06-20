package service;

import java.util.List;

import model.MemberBean;

public interface MemberService {

	boolean isDup(String id);

	int save(MemberBean mb);

	List<MemberBean> getAllMembers();

	MemberBean getMember(int user_no);

	int deleteMember(int user_no);

	int updateMember(MemberBean mb);

}