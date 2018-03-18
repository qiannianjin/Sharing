package top.arexstorm.sharing.mapper;

import java.util.List;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;

public interface CustomerInformationTypeMapper {

	public CustomerInformationType findInformationTypeById(String informationTypeId);

	public List<CustomerInformationType> findAllInformationType(CustomerInformationType customerInformationType);

	public List<CustomerInformation> findAllInfomationByInformationTypeId(CustomerInformationType customerInformationType);
	
}
