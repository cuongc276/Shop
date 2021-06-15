package com.shop.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileUtils {

	public File handleUploadFile(
			MultipartFile uploadFile
	) {
		String folderPath = "F:\\21.Java 5\\Workspace\\Shop\\src\\main\\webapp\\views\\storage";
		File folder = new File(folderPath);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		File saveFile = null;
		try {
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + "." +uploadFile.getContentType().substring(uploadFile.getContentType().indexOf("/")+1, uploadFile.getContentType().length());
			saveFile = new File(folder, fileName);
			uploadFile.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveFile;
		
	}
}
