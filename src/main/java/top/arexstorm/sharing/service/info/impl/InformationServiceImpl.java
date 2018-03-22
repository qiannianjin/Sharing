package top.arexstorm.sharing.service.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.mapper.CustomerInformationMapper;
import top.arexstorm.sharing.mapper.InformationMapper;
import top.arexstorm.sharing.service.info.InformationService;

@Service(value="informationService")
@Transactional
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationMapper informationMapper;
	@Autowired
	private CustomerInformationMapper customerInformationMapper;
	
	@Override
	public CustomerInformation findInformationById(String informationid) {
		CustomerInformation customerInformation = null;
		if (StringUtils.isNotBlank(informationid)) {
			customerInformation = customerInformationMapper.findInformationById(informationid);
		}
		return customerInformation;
	}

	@Override
	public List<CustomerInformation> findAllInformation(Integer status, String userid) {
		
		List<CustomerInformation> list = new ArrayList<CustomerInformation>();
		CustomerInformation customerInformation = new CustomerInformation();
		if (status != null) {
			customerInformation.setStatus(Short.parseShort(status.toString()));
		}
		if (StringUtils.isNotBlank(userid)) {
			customerInformation.setUserid(userid);
		}
		list = customerInformationMapper.findAllInformation(customerInformation);
		
		return list;
	}

	@Override
	public CustomerInformationType findInformationTypeByInformationId(String informationid) {
		CustomerInformationType find = null;
		if (StringUtils.isNotBlank(informationid)) {
			find = customerInformationMapper.findInformationTypeByInformationId(informationid);
		}
		
		return find;	}

	@Override
	public void addInformation(Information information) {
		if (information != null) {
			informationMapper.insertSelective(information);
		}
	}

	@Override
	public void updateInformationStatus(String informationid, String status) {
		
		if (StringUtils.isNotBlank(informationid) && StringUtils.isNotBlank(status)) {
			CustomerInformation customerInformation = new CustomerInformation();
			customerInformation.setInformationid(informationid);
			customerInformation.setStatus(Short.parseShort(status));
			customerInformationMapper.updateInformationStatus(customerInformation);
		}

	}

	@Override
	public void updateInformation(Information information, String informationid) {
		CustomerInformation find = customerInformationMapper.findInformationById(informationid);
		if (find != null) {
			information.setId(find.getId());
			informationMapper.updateByPrimaryKeySelective(information);
		}
	}

	@Override
	public void deleteInformationByInfomationId(String informationid) {
		if (StringUtils.isNotBlank(informationid)) {
			CustomerInformation find = customerInformationMapper.findInformationById(informationid);
			informationMapper.deleteByPrimaryKey(find.getId());
		}
	}

}
