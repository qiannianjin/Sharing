package top.arexstorm.sharing.service.info.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.bean.info.Comments;
import top.arexstorm.sharing.bean.info.CustomerComments;
import top.arexstorm.sharing.mapper.CommentsMapper;
import top.arexstorm.sharing.mapper.CustomerCommentsMapper;
import top.arexstorm.sharing.service.info.CommentsService;

@Service(value = "commentsService")
@Transactional
public class CustomerCommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;
	@Autowired
	private CustomerCommentsMapper customerCommentsMapper;
	
	@Override
	public CustomerComments findCommentById(String commentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerComments> findAllComments(Short status, String userid, String informationtypeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComment(Comments comments) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCommentStatus(Short status, String commentid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateComment(Comments comment, String commentid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCommentById(String commentid) {
		// TODO Auto-generated method stub

	}

}
