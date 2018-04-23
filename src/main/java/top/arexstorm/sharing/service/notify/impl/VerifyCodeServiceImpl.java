package top.arexstorm.sharing.service.notify.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.arexstorm.sharing.bean.notify.VerifyCode;
import top.arexstorm.sharing.mapper.CustomerVerifyCodeMapper;
import top.arexstorm.sharing.mapper.VerifyCodeMapper;
import top.arexstorm.sharing.service.notify.VerifyCodeService;

@Service(value = "verifyCodeService")
public class VerifyCodeServiceImpl implements VerifyCodeService {

	@Autowired
	private CustomerVerifyCodeMapper customerVerifyCodeMapper;
	@Autowired
	private VerifyCodeMapper verifyCodeMapper;

	@Override
	public VerifyCode findVerifyCodeByUserid(String userid, Short type) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userid)) {
			paramMap.put("userid", userid);
		}
		if (type != null) {
			paramMap.put("type", type);
		}

		return customerVerifyCodeMapper.findVerifyCodeByUserid(paramMap);
	}

	@Override
	public void addVerifyCode(VerifyCode verifyCode) {
		verifyCodeMapper.insertSelective(verifyCode);
	}

	@Override
	public void updateVerifyCodeStatus(String userid, Short type, Short status) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userid)) {
			paramMap.put("userid", userid);
		}
		if (type != null) {
			paramMap.put("type", type);
		}
		if (status != null) {
			paramMap.put("status", status);
		}

		customerVerifyCodeMapper.updateVerifyCodeStatus(paramMap);
	}

	
}
