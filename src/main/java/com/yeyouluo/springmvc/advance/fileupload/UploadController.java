package com.yeyouluo.springmvc.advance.fileupload;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String upload(MultipartFile file) {
		try {
			FileUtils.writeByteArrayToFile(new File("e:/upload/"+file.getOriginalFilename()), file.getBytes());
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "wrong";
		}
	}
}
