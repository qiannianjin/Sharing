package top.arexstorm.sharing.service.info;

import java.util.List;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.InformationType;

public interface InformationTypeService {

	/**
	 * 根据信息类型id(informationid)来查找一个信息类型
	 * @param informationTypeId
	 * @return
	 */
	public CustomerInformationType findInformationTypeById(String informationTypeId);
	
	/**
	 * 根据状态查找所有的信息类型
	 * @param status
	 * @return
	 */
	public List<CustomerInformationType> findAllInformaionType(Integer status);
	
	/**
	 * 添加一个信息类型
	 * @param informationType
	 */
	public void addInformationType(InformationType  informationType);
	
	/**
	 * 根据信息类型查找所有的信息
	 * @param informationTypeId
	 * @return
	 */
	public List<CustomerInformation> findAllInfomationByInformationTypeId(String informationTypeId);
	
	/**
	 * 删除指定的信息类型
	 * @param informationTypeId
	 */
	public void deleteInformationType(String informationTypeId);
	
	/**
	 * 更新指定信息类型的状态
	 * @param informationTypeId
	 * @param status
	 */
	public void updateInformationTypeStatus(String informationTypeId, String status);
	
}
