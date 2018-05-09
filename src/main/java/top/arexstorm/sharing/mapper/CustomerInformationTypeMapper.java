package top.arexstorm.sharing.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;

public interface CustomerInformationTypeMapper {

	public CustomerInformationType findInformationTypeById(String informationTypeId);

	public List<CustomerInformationType> findAllInformationType(CustomerInformationType customerInformationType);

	public List<CustomerInformation> findAllInfomationByInformationTypeId(CustomerInformationType customerInformationType);

	List<CustomerInformationType> findAllInformaionTypeWithPage(@Param("status") Short status, @Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
}
