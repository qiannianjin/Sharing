package top.arexstorm.sharing.service.file;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import top.arexstorm.sharing.bean.file.CustomerPicPath;
import top.arexstorm.sharing.bean.file.PicPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PicPathServiceTest {

	@Autowired
	private PicPathService picPathService;
	
	@Test
	public void testFindPicPathById() {
		
		String picpathid = "1";
		CustomerPicPath picPath = picPathService.findPicPathById(picpathid);
		System.err.println(picPath);
	}

	@Test
	public void testFindAllPicPath() {

		Integer status = null;
		String type = "生活";
		
		List<CustomerPicPath> list = picPathService.findAllPicPath(status, type);
		for (CustomerPicPath customerPicPath : list) {
			System.err.println(customerPicPath);
		}
		
	}

	@Test
	public void testAddPicPath() {
		PicPath picPath = new PicPath();
		picPath.setName("圖片1");
		picPath.setPath("/img/test/goodsMan.jpg");
		picPath.setPicpathid("2");
		picPath.setStatus(Short.parseShort("1"));
		picPath.setType("生活類");
		
		picPathService.addPicPath(picPath);
	}

	@Test
	public void testUpdatePicPathStatus() {
		
		String picpathid = "2";
		String status = "1";
		picPathService.updatePicPathStatus(picpathid, status);
	}

	@Test
	public void testUpdatePicPath() {
		PicPath picPath = new PicPath();
		picPath.setName("這是圖片2");
		picPath.setType("car type");
		String picpathid = "2";
		picPathService.updatePicPath(picPath, picpathid);
	}

	@Test
	public void testDeletePicPathByPicPathId() {
		String picpathid = "2";
		picPathService.deletePicPathByPicPathId(picpathid);
	}

}
