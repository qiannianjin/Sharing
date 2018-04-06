package top.arexstorm.sharing.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.csource.common.MyException;
import org.junit.Test;

public class FastDFSUtilsTest {

	/**
	 * 访问路径 http://101.132.149.194:8633/group1/M00/00/00/wKgBP1q-TnKAG88CAAAAFgBdXaA997.txt
	 * @throws IOException
	 * @throws MyException
	 */
	@Test
	public void testSaveText() {
		
		String file_ext_name = "txt";
		byte[] file_buff = "asdfasdfwerdsfasdfasdf".getBytes();
		String name = FastDFSUtils.savePic(file_buff , file_ext_name);
		System.out.println(name); 
	}
	
	/**
	 * 访问路径  http://101.132.149.194:8633/group1/M00/00/00/wKgBP1q-UoaABcwZAAPzytMSFD8744.png
	 * @throws IOException
	 */
	@Test
	public void testSavePic() throws IOException {
		
		File file = new File("F:\\Workspace\\Sharing\\src\\main\\java\\111.png");
		if (file.exists()) {
			//读取111.png 文件
			InputStream in = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int n = 0;
			byte[] buffer = new byte[1024];
			while ((n = in.read(buffer)) != -1) {
				baos.write(buffer, 0, n);
			}
			
			byte[] file_buff = baos.toByteArray();
			String file_ext_name = "png";
			
			//保存文件到fastdfs
			String name = FastDFSUtils.savePic(file_buff , file_ext_name);
			System.out.println(name); 
		} else {
			System.err.println("文件不存在");
		}
	}
}
