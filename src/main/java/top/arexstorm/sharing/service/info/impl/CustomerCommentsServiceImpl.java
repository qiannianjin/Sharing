package top.arexstorm.sharing.service.info.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
		CustomerComments customerComments = null;
		if (StringUtils.isNotBlank(commentid)) {
			customerComments = customerCommentsMapper.findCommentById(commentid);
		}

		return customerComments;
	}

	@Override
	public List<CustomerComments> findAllComments(Short status, String userid, String informationid) {
		CustomerComments customerComments = new CustomerComments();
		if (status != null) {
			customerComments.setStatus(status);
		}
		if (StringUtils.isNotBlank(userid)) {
			customerComments.setUserid(userid);
		}
		if (StringUtils.isNotBlank(informationid)) {
		    customerComments.setInformationid(informationid);
        }

        return customerCommentsMapper.findAllComments(customerComments);
	}

	@Override
	public void addComment(Comments comments) {
		commentsMapper.insertSelective(comments);
	}

	@Override
	public void updateCommentStatus(Short status, String commentid) {
        CustomerComments customerComments = this.findCommentById(commentid);
        if (customerComments != null && status != null) {
            customerComments.setStatus(status);
            commentsMapper.updateByPrimaryKeySelective(customerComments);
        }
	}

	@Override
	public void updateComment(Comments comment, String commentid) {
        CustomerComments customerComments = this.findCommentById(commentid);
        if (customerComments != null ) {
            comment.setId(customerComments.getId());
            commentsMapper.updateByPrimaryKeySelective(comment);
        }
	}

	@Override
	public void deleteCommentById(String commentid) {
		CustomerComments customerComments = this.findCommentById(commentid);
		if (customerComments != null) {
		    commentsMapper.deleteByPrimaryKey(customerComments.getId());
        }
	}
}
