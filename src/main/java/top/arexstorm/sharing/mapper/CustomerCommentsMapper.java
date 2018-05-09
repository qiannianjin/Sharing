package top.arexstorm.sharing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.info.CustomerComments;

public interface CustomerCommentsMapper {

	public CustomerComments findCommentById(String commentid);
	
	public List<CustomerComments> findAllComments(CustomerComments customerComments);

	List<CustomerComments> findAllCommentsWithPage(@Param("status") Short status, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
}
