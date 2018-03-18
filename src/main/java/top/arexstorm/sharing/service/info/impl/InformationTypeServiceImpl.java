package top.arexstorm.sharing.service.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.InformationType;
import top.arexstorm.sharing.mapper.CustomerInformationTypeMapper;
import top.arexstorm.sharing.mapper.InformationTypeMapper;
import top.arexstorm.sharing.service.info.InformationTypeService;

@Service(value="informationTypeService")
@Transactional
public class InformationTypeServiceImpl implements InformationTypeService {

	@Autowired
	private InformationTypeMapper informationTypeMapper;
	@Autowired
	private CustomerInformationTypeMapper customerInformationTypeMapper;
	
	@Override
	public CustomerInformationType findInformationTypeById(String informationTypeId) {
		CustomerInformationType customerInformationType = null;
		if (StringUtils.isNotEmpty(informationTypeId)) {
			customerInformationType = customerInformationTypeMapper.findInformationTypeById(informationTypeId);
		}
		return customerInformationType;
	}

	/**
	 * 该方法还可以扩展 参数
	 */
	@Override
	public List<CustomerInformationType> findAllInformaionType(Integer status) {
		
		List<CustomerInformationType> list = new ArrayList<CustomerInformationType>();
		
		CustomerInformationType customerInformationType = new CustomerInformationType();
		if (status != null) {
			customerInformationType.setStatus(Short.parseShort(status.toString()));
			list = customerInformationTypeMapper.findAllInformationType(customerInformationType);
		}
		return list;
	}

	@Override
	public void addInformationType(InformationType informationType) {
		informationTypeMapper.insert(informationType);
	}

	@Override
//	TODO
	public List<CustomerInformation> findAllInfomationByInformationTypeId(String informationTypeId) {
		List<CustomerInformation> list = new ArrayList<CustomerInformation>();
		
		CustomerInformationType customerInformationType = new CustomerInformationType();
		if (StringUtils.isNotEmpty(informationTypeId)) {
			customerInformationType.setInformationid(informationTypeId);
			list = customerInformationTypeMapper.findAllInfomationByInformationTypeId(customerInformationType);
		}
		
		return list;
	}

	@Override
	public void deleteInformationType(String informationTypeId) {
		if (StringUtils.isNotEmpty(informationTypeId)) {
			InformationType informationType = this.findInformationTypeById(informationTypeId);
			if (informationType != null) {
				informationTypeMapper.deleteByPrimaryKey(informationType.getId());
			}
		}
	}

	/**
	 * 先根据informationTypeId 查找到信息类型，然后更新状态，保存
	 */
	@Override
	public void updateInformationTypeStatus(String informationTypeId, String status) {
		if (StringUtils.isNotEmpty(informationTypeId)) {
			InformationType informationType = this.findInformationTypeById(informationTypeId);
			if (informationType!=null) {
				informationType.setStatus(Short.parseShort(status.toString()));
				informationTypeMapper.updateByPrimaryKey(informationType);
			}
		}
	}

}
