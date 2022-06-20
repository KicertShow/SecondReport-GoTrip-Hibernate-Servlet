package service;

import java.util.List;

import model.CommentBean;

public interface CommentService {

	List<CommentBean> getAllComments();
	
	CommentBean getComment(int id);

	boolean saveComment(CommentBean comment);

	boolean deleteComment(int id);

	boolean updateComment(CommentBean comment);
	
}
