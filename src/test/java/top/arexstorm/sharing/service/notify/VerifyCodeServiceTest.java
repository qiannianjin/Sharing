package top.arexstorm.sharing.service.notify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.notify.VerifyCode;
import top.arexstorm.sharing.utils.UUIDUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyCodeServiceTest {
	
	@Autowired
	private VerifyCodeService verifyCodeService;

	@Test
	public void testFindVerifyCodeByUserid() {
		String userid = "islambao@126.com";
		Short type = 0;
		
		VerifyCode verifyCode = verifyCodeService.findVerifyCodeByUserid(userid, type);
		System.err.println(verifyCode);
	}

	@Test
	public void testAddVerifyCode() {
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setCode(UUIDUtils.generateUUIDString());
		verifyCode.setEmail("islambao@126.com");
		verifyCode.setType(Short.parseShort("0"));
		verifyCode.setUserid("islambao@126.com");
		
		verifyCodeService.addVerifyCode(verifyCode);
	}

	@Test
	public void testUpdateVerifyCodeStatus() {
		
		String userid = "islambao@126.com";
		Short type = 0;
		Short status = Short.parseShort("1");
		
		verifyCodeService.updateVerifyCodeStatus(userid, type, status);
	}

}
