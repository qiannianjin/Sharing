package top.arexstorm.sharing.mapper;

import java.util.List;

import top.arexstorm.sharing.bean.info.CustomerComments;

public interface CustomerCommentsMapper {

	public CustomerComments findCommentById(String commentid);
	
	public List<CustomerComments> findAllComments(CustomerComments customerComments);
}
