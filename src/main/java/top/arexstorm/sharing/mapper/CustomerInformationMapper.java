package top.arexstorm.sharing.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;

public interface CustomerInformationMapper {
    
	public CustomerInformation findInformationById(String informationid);
	
	public List<CustomerInformation> findAllInformation(CustomerInformation customerInformation);
	
	public CustomerInformationType findInformationTypeByInformationId(String informationid);
	
	public int updateInformationStatus(CustomerInformation customerInformation);

	public List<CustomerInformation> findAllBuyInformation(Map<String, Object> paramMap);

	List<CustomerInformation> findAllInformationWithPage(@Param("status") Short status, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
}