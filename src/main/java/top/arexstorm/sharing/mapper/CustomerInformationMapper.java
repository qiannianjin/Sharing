package top.arexstorm.sharing.mapper;

import java.util.List;
import java.util.Map;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;

public interface CustomerInformationMapper {
    
	public CustomerInformation findInformationById(String informationid);
	
	public List<CustomerInformation> findAllInformation(CustomerInformation customerInformation);
	
	public CustomerInformationType findInformationTypeByInformationId(String informationid);
	
	public void updateInformationStatus(CustomerInformation customerInformation);

	public List<CustomerInformation> findAllBuyInformation(Map<String, Object> paramMap);
}