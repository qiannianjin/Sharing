package top.arexstorm.sharing.service.info;

import java.util.List;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;

public interface InformationService {

	/**
	 * 根据信息id(informationid)来查询指定的信息
	 * @param informationid
	 * @return
	 */
	public CustomerInformation findInformationById(String informationid);
	
	/**
	 * 
	 * 查找所有的信息 可扩展 
	 * @param status
	 * @return
	 */
	public List<CustomerInformation> findAllInformation(Integer status);
	
	/**
	 * 根据信息id(informationid) 来查询相关的信息类型信息
	 * @param informationid
	 * @return
	 */
	public CustomerInformationType findInformationTypeByInformationId(String informationid);
	
	/**
	 * 添加一个信息 选择性添加
	 * @param information
	 */
	public void addInformation(Information information);
	
	/**
	 * 更新指定信息的状态
	 * @param informationid
	 * @param status
	 */
	public void updateInformationStatus(String informationid, String status);
	
	/**
	 * 更新指定信息
	 * @param information
	 * @param informationid
	 */
	public void updateInformation(Information information, String informationid);
	
	/**
	 * 删除指定信息id的信息
	 * @param informationid
	 */
	public void deleteInformationByInfomationId(String informationid);
}
