package top.arexstorm.sharing.service.info.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.arexstorm.sharing.annotation.TargetDataSource;
import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.Information;
import top.arexstorm.sharing.mapper.CustomerInformationMapper;
import top.arexstorm.sharing.mapper.InformationMapper;
import top.arexstorm.sharing.service.info.InformationService;
import top.arexstorm.sharing.utils.PageResult;


@Service(value="informationService")
@Transactional
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationMapper informationMapper;
	@Autowired
	private CustomerInformationMapper customerInformationMapper;

	@TargetDataSource("slave")
	@Override
	public CustomerInformation findInformationById(String informationid) {
		CustomerInformation customerInformation = null;
		if (StringUtils.isNotBlank(informationid)) {
			customerInformation = customerInformationMapper.findInformationById(informationid);
		}
		return customerInformation;
	}

	@TargetDataSource("slave")
	@Override
	public List<CustomerInformation> findAllInformation(Short status, Short important, String userid, String informationtypeid) {
		
		List<CustomerInformation> list = new ArrayList<CustomerInformation>();
		CustomerInformation customerInformation = new CustomerInformation();
		if (status != null) {
			customerInformation.setStatus(status);
		}
		if (important != null) {
			customerInformation.setImportant(important);
		}
		if (StringUtils.isNotBlank(userid)) {
			customerInformation.setUserid(userid);
		}
		if (StringUtils.isNotBlank(informationtypeid)) {
			customerInformation.setTypeid(informationtypeid);
		}
		list = customerInformationMapper.findAllInformation(customerInformation);
		
		return list;
	}

	@TargetDataSource("slave")
	@Override
	public CustomerInformationType findInformationTypeByInformationId(String informationid) {
		CustomerInformationType find = null;
		if (StringUtils.isNotBlank(informationid)) {
			find = customerInformationMapper.findInformationTypeByInformationId(informationid);
		}
		
		return find;
	}

	@Override
	public int addInformation(Information information) {
		if (information != null) {
			return informationMapper.insertSelective(information);
		}

		return -1;
	}

	@Override
	public int updateInformationStatus(String informationid, Short status) {
		
		if (StringUtils.isNotBlank(informationid) && status != null) {
			CustomerInformation customerInformation = new CustomerInformation();
			customerInformation.setInformationid(informationid);
			customerInformation.setStatus(status);
			return customerInformationMapper.updateInformationStatus(customerInformation);
		}

		return -1;
	}

	@Override
	public int updateInformation(Information information, String informationid) {
		CustomerInformation find = customerInformationMapper.findInformationById(informationid);
		if (find != null) {
			information.setId(find.getId());
			return informationMapper.updateByPrimaryKeySelective(information);
		}

		return -1;
	}

	@Override
	public int deleteInformationByInfomationId(String informationid) {
		if (StringUtils.isNotBlank(informationid)) {
			CustomerInformation find = customerInformationMapper.findInformationById(informationid);
			return informationMapper.deleteByPrimaryKey(find.getId());
		}

		return -1;
	}

	@TargetDataSource("slave")
	@Override
	public List<CustomerInformation> findAllBuyInformation(String buyerid, Short status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(buyerid)) {
			paramMap.put("buyerid", buyerid);
		}
		if (status != null) {
			paramMap.put("status", status);
		}
		return customerInformationMapper.findAllBuyInformation(paramMap);
	}

	@Override
	public PageResult<CustomerInformation> findAllInformationWithPage(Integer pageNum, Integer pageSize, Short status, String searchKey, String searchValue) {

		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<CustomerInformation> infos = customerInformationMapper.findAllInformationWithPage(status, searchKey, searchValue);
		PageResult<CustomerInformation> result = new PageResult<CustomerInformation>();
		result.setData(infos);
		result.setCount(startPage.getTotal());
		return result;
	}
}
