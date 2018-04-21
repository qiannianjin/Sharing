package top.arexstorm.sharing.controller.file;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import top.arexstorm.sharing.bean.file.PicPath;
import top.arexstorm.sharing.config.Constants;
import top.arexstorm.sharing.service.file.PicPathService;
import top.arexstorm.sharing.utils.AppResponse;
import top.arexstorm.sharing.utils.FastDFSUtils;
import top.arexstorm.sharing.utils.UUIDUtils;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	
	@Autowired
	private PicPathService picPathService;

	/**
	 * 上传图片
	 * @param img
	 * @return
	 * @throws IOException 
	 */
	@PostMapping(value="/upload")
	@ResponseBody
	public AppResponse upload(MultipartFile file, HttpServletRequest req) throws IOException {
		
		String path = FastDFSUtils.savePic(file.getBytes(), file);
		if (path != null) {
			String url = Constants.IMG_URL + path;
			Map map = new HashMap<String, Object>();
			map.put("url",url);
			
			//添加一条记录
			PicPath picPath = new PicPath();
			picPath.setName(file.getOriginalFilename());
			picPath.setPath(path);
			picPath.setPicpathid(UUIDUtils.generateUUIDString());
			picPath.setStatus(Short.parseShort("1"));
			picPath.setType("");
			picPathService.addPicPath(picPath);
			
			return AppResponse.okData(map, 0, "图片上传成功", null);
		} else {
			return AppResponse.okData(-1, "图片上传失败。");
		}
		
	}
}
