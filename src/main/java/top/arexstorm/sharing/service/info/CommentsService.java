package top.arexstorm.sharing.service.info;

import java.util.List;

import top.arexstorm.sharing.bean.info.Comments;
import top.arexstorm.sharing.bean.info.CustomerComments;

public interface CommentsService {

	/**
	 * 根据comentid查找评论
	 * @param commentid
	 * @return
	 */
	public CustomerComments findCommentById(String commentid);
	
	/**
	 * 根据条件查找一系列的评论
	 * @param status
	 * @param userid
	 * @param informationtypeid
	 * @return
	 */
	public List<CustomerComments> findAllComments(Short status, String userid, String informationtypeid);
	
	/**
	 * 添加一条评论
	 * @param comments
	 */
	public void addComment(Comments comments);
	
	/**
	 * 更新指定评论的状态
	 * @param status
	 * @param commentid
	 */
	public void updateCommentStatus(Short status, String commentid);
	
	/**
	 * 更新指定的评论
	 * @param comment
	 * @param commentid
	 */
	public void updateComment(Comments comment, String commentid);
	
	/**
	 * 正真删除一条评论
	 * @param commentid
	 */
	public void deleteCommentById(String commentid);
}
