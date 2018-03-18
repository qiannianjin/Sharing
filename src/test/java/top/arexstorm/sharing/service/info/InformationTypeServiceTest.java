package top.arexstorm.sharing.service.info;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.info.CustomerInformation;
import top.arexstorm.sharing.bean.info.CustomerInformationType;
import top.arexstorm.sharing.bean.info.InformationType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationTypeServiceTest {

	@Autowired
	private InformationTypeService informationTypeService;
	
	@Test
	public void testFindInformationTypeById() {
		String informationTypeId = "1";
		CustomerInformationType customerInformationType = informationTypeService.findInformationTypeById(informationTypeId);
		System.out.println(customerInformationType);
	}

	@Test
	public void testFindAllInformaionType() {
		
		Integer status = 1;
		List<CustomerInformationType> findAllInformaionType = informationTypeService.findAllInformaionType(status);
		for (CustomerInformationType customerInformationType : findAllInformaionType) {
			System.out.println(customerInformationType);
		}
	}

	@Test
	public void testAddInformationType() {
		InformationType informationType = new InformationType();
		informationType.setIcon("/abc/icon.png");
		informationType.setName("服务类");
		informationType.setShortname("服务类");
		informationType.setStatus(Short.parseShort("1"));
		informationType.setSummary("这是服务类的");
		informationType.setInformationtypeid("2");
		informationTypeService.addInformationType(informationType);
	}

	@Test
	public void testFindAllInfomationByInformationTypeId() {
		String informationTypeId = "1";
		List<CustomerInformation> list = informationTypeService.findAllInfomationByInformationTypeId(informationTypeId);
		for (CustomerInformation customerInformation : list) {
			System.out.println(customerInformation);
		}
	}

	@Test
	public void testDeleteInformationType() {
		String informationTypeId = "2";
		informationTypeService.deleteInformationType(informationTypeId);
	}

	@Test
	public void testUpdateInformationTypeStatus() {
		String informationTypeId = "1";
		String status = "0";
		informationTypeService.updateInformationTypeStatus(informationTypeId, status);
	}
	
	@Test
	public void testUpdateInformationType( ) {
		
		String informationTypeId = "1";
		InformationType informationType = new InformationType();
		informationType.setIcon("bb.png");
		informationType.setName("新生类");
		informationType.setShortname("new kind");
		informationTypeService.updateInformationType(informationType, informationTypeId);
	}

}
