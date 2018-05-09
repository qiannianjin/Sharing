package top.arexstorm.sharing.service.info.impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.annotation.TargetDataSource;
import top.arexstorm.sharing.bean.info.Comments;
import top.arexstorm.sharing.bean.info.CustomerComments;
import top.arexstorm.sharing.mapper.CommentsMapper;
import top.arexstorm.sharing.mapper.CustomerCommentsMapper;
import top.arexstorm.sharing.service.info.CommentsService;
import top.arexstorm.sharing.utils.PageResult;

@Service(value = "commentsService")
@Transactional
public class CustomerCommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;
	@Autowired
	private CustomerCommentsMapper customerCommentsMapper;

	@TargetDataSource("slave")
	@Override
	public CustomerComments findCommentById(String commentid) {
		CustomerComments customerComments = null;
		if (StringUtils.isNotBlank(commentid)) {
			customerComments = customerCommentsMapper.findCommentById(commentid);
		}

		return customerComments;
	}

	@TargetDataSource("slave")
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

	@Override
	public PageResult<CustomerComments> findAllCommentsWithPage(Integer pageNum, Integer pageSize, String searchKey, String searchValue, Short status) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<CustomerComments> comments = customerCommentsMapper.findAllCommentsWithPage(status, searchKey, searchValue);
		PageResult<CustomerComments> result = new PageResult<CustomerComments>();
		result.setData(comments);
		result.setCount(startPage.getTotal());
		return result;
	}
}
