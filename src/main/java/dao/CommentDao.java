package dao;

import java.util.List;
import model.CommentBean;

public interface CommentDao {

	List<CommentBean> getAllComments();
	
	CommentBean getComment(int id);

	void saveComment(CommentBean comment);

	void deleteComment(int id);

	void updateComment(CommentBean comment);

}
