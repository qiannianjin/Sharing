package top.arexstorm.sharing.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FastDFSUtils {

	/**
	 * 保存失败 返回 null 
	 * 保存成功 返回文件路径 如：group1/M00/00/00/wKgBP1q-TnKAG88CAAAAFgBdXaA997.png
	 * @param file_buff
	 * @param file_ext_name
	 * @param pic
	 * @return
	 */
	public static String savePic(byte[] file_buff, String file_ext_name, MultipartFile pic) {

		ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
		String path = resource.getClassLoader().getResource("fdfs_client.conf").getPath();
		try {
			ClientGlobal.init(path);
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			
			StorageServer storageServer = null;
			StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
			NameValuePair[] meta_list = null;
			if (pic != null) {
				meta_list = new NameValuePair[3];
				meta_list[0] = new NameValuePair("文件大小", String.valueOf(pic.getSize()));
				meta_list[1] = new NameValuePair("contentType", pic.getContentType());
				meta_list[2] = new NameValuePair("原始文件名", pic.getOriginalFilename());
			}
			
			String name = null;
			synchronized (storageClient1) {
				name = storageClient1.upload_file1(file_buff, file_ext_name, meta_list);
			}
			
			return name;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static String savePic(byte[] file_buff, String file_ext_name) {
		return savePic(file_buff, file_ext_name, null);
	}
	
	public static String savePic(byte[] file_buff, MultipartFile pic) {
		return savePic(file_buff, FilenameUtils.getExtension(pic.getOriginalFilename()));
	}
}
