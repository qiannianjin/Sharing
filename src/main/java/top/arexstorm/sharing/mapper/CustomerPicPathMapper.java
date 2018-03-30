package top.arexstorm.sharing.mapper;

import java.util.List;

import top.arexstorm.sharing.bean.file.CustomerPicPath;

public interface CustomerPicPathMapper {
    
	public CustomerPicPath findPicPathById(String picpathid);
	
	public List<CustomerPicPath> findAllPicPath(CustomerPicPath customerPicPath);
	
	public void updatePicPathStatus(CustomerPicPath customerPicPath);
}