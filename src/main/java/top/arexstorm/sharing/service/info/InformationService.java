package top.arexstorm.sharing.service.info;

import java.util.List;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.utils.PageResult;

public interface InformationService {

	/**
	 * 根据信息id(informationid)来查询指定的信息
	 * @param informationid
	 * @return
	 */
	public CustomerInformation findInformationById(String informationid);
	
	/**
	 * 查找所有的信息 可扩展 
	 * @param status
	 * @param important
	 * @param userid
	 * @param informationtypeid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<CustomerInformation> findAllInformation(Short status, Short important, String userid, String informationtypeid, Integer pageNum, Integer pageSize);
	
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
	public int addInformation(Information information);
	
	/**
	 * 更新指定信息的状态
	 * @param informationid
	 * @param status
	 */
	public int updateInformationStatus(String informationid, Short status);
	
	/**
	 * 更新指定信息
	 * @param information
	 * @param informationid
	 */
	public int updateInformation(Information information, String informationid);
	
	/**
	 * 删除指定信息id的信息
	 * @param informationid
	 */
	public int deleteInformationByInfomationId(String informationid);

	/**
	 * 查询指定用户购买的所有共享信息
	 * @param userid
	 * @param status
	 * @return
	 */
	public List<CustomerInformation> findAllBuyInformation(String userid, Short status);

	/**
	 * 分页查找 共享信息
	 * @param page
	 * @param limit
	 * @param status
	 * @param searchKey
	 * @param searchValue
	 * @return
	 */
	PageResult<CustomerInformation> findAllInformationWithPage(Integer page, Integer limit, Short status, String searchKey, String searchValue);
}
