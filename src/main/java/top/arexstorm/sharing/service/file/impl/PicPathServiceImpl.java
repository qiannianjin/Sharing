package top.arexstorm.sharing.service.file.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.bean.file.CustomerPicPath;
import top.arexstorm.sharing.bean.file.PicPath;
import top.arexstorm.sharing.mapper.CustomerPicPathMapper;
import top.arexstorm.sharing.mapper.PicPathMapper;
import top.arexstorm.sharing.service.file.PicPathService;

@Service(value="picPathService")
@Transactional
public class PicPathServiceImpl implements PicPathService {

	@Autowired
	private PicPathMapper picPathMapper;
	@Autowired
	private CustomerPicPathMapper customerPicPathMapper;
	
	@Override
	public CustomerPicPath findPicPathById(String picpathid) {
		CustomerPicPath customerPicPath = customerPicPathMapper.findPicPathById(picpathid);
		return customerPicPath;
	}

	@Override
	public List<CustomerPicPath> findAllPicPath(Integer status, String type) {
		CustomerPicPath customerPicPath = new CustomerPicPath();
		if (status != null) {
			customerPicPath.setStatus(Short.parseShort(status.toString()));
		}
		if (StringUtils.isNotBlank(type)) {
			customerPicPath.setType(type);
		}
		
		return customerPicPathMapper.findAllPicPath(customerPicPath);
	}

	@Override
	public void addPicPath(PicPath picPath) {

		picPathMapper.insertSelective(picPath);
	}

	@Override
	public void updatePicPathStatus(String picpathid, String status) {

		CustomerPicPath customerPicPath = new CustomerPicPath();
		if (status != null) {
			customerPicPath.setStatus(Short.parseShort(status.toString()));
		}
		if (StringUtils.isNotBlank(picpathid)) {
			customerPicPath.setPicpathid(picpathid);
		}
		
		customerPicPathMapper.updatePicPathStatus(customerPicPath);
	}

	@Override
	public void updatePicPath(PicPath picPath, String picpathid) {
		
		CustomerPicPath customerPicPath = this.findPicPathById(picpathid);
		if (customerPicPath!=null && StringUtils.isNotBlank(customerPicPath.getPicpathid())) {
			picPath.setId(customerPicPath.getId());
			picPathMapper.updateByPrimaryKeySelective(picPath);
		}
	}

	@Override
	public void deletePicPathByPicPathId(String picpathid) {

		if (StringUtils.isNotBlank(picpathid)) {
			CustomerPicPath customerPicPath = customerPicPathMapper.findPicPathById(picpathid);
			if (customerPicPath!=null && customerPicPath.getId() != null) {
				picPathMapper.deleteByPrimaryKey(customerPicPath.getId());
			}
		}
	}
}
