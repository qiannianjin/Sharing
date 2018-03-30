package top.arexstorm.sharing.service.file;

import java.util.List;

import top.arexstorm.sharing.bean.file.CustomerPicPath;
import top.arexstorm.sharing.bean.file.PicPath;

public interface PicPathService {

	/**
	 * 根据picpathid查找指定的PicPath
	 * @param picpathid
	 * @return
	 */
	public CustomerPicPath findPicPathById(String picpathid);
	
	/**
	 * 根据条件查找所有的 picpath
	 * @param status
	 * @param type
	 * @return
	 */
	public List<CustomerPicPath> findAllPicPath(Integer status, String type);
	
	/**
	 * 添加一条记录
	 * @param picPath
	 */
	public void addPicPath(PicPath picPath);
	
	/**
	 * 更新指定picpath的状态
	 * @param picpathid
	 * @param status
	 */
	public void updatePicPathStatus(String picpathid, String status);
	
	/**
	 * 
	 * @param picPath
	 * @param picpathid
	 */
	public void updatePicPath(PicPath picPath, String picpathid);
	
	/**
	 * 根据picpathid 来删除指定的picpath
	 * @param picpathid
	 */
	public void deletePicPathByPicPathId(String picpathid);
}
