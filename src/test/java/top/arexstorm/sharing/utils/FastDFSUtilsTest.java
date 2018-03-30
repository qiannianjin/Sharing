package top.arexstorm.sharing.utils;

import java.io.IOException;

import org.csource.common.MyException;
import org.junit.Test;

public class FastDFSUtilsTest {

	/**
	 * 访问路径 http://101.132.149.194:8633/group1/M00/00/00/wKgBP1q-TnKAG88CAAAAFgBdXaA997.txt
	 * @throws IOException
	 * @throws MyException
	 */
	@Test
	public void testSavePic() throws IOException, MyException {
		
		String file_ext_name = "txt";
		byte[] file_buff = "asdfasdfwerdsfasdfasdf".getBytes();
		String name = FastDFSUtils.savePic(file_buff , file_ext_name);
		System.out.println(name); 
	}
}
