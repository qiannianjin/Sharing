package top.arexstorm.sharing.controller.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.service.info.InformationTypeService;
import top.arexstorm.sharing.utils.AppResponse;

@Controller
@RequestMapping(value="/type")
public class InformationTypeController {

	@Autowired
	private InformationTypeService informationTypeService;
	
	/**
	 * 查询信息类型
	 * @param status
	 * @return
	 */
	@ResponseBody
	@GetMapping(value="/common")
	public AppResponse common(Integer status) {
		
		List<CustomerInformationType> list = informationTypeService.findAllInformaionType(status);
		
		return AppResponse.okList(list);
	}
}
