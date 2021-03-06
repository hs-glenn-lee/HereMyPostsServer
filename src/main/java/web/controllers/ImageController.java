package web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.exceptions.NotSignedInException;
import web.model.service.file.FileService;

@Controller
public class ImageController {
	
	@Autowired
	FileService fileService;
	
	@RequestMapping(value="/uploaded-image/{imageFileId}", method = RequestMethod.GET)
	public void getImage(HttpServletResponse res,
				@PathVariable String imageFileId) throws IOException {
		//get file
		File imageFile = fileService.getFile(imageFileId);
		String fileExt = FilenameUtils.getExtension(imageFile.getPath());
		
		//validate before serve image
		validateFileServedAsImage(fileExt);
		
		//set response and serve image
		res.setContentType("image/"+fileExt);
		InputStream imgInputStream = FileUtils.openInputStream(imageFile);
		IOUtils.copy(imgInputStream, res.getOutputStream());
		
		if (imgInputStream != null) {
			imgInputStream.close();
		}
		
		//TODO optimize performance, image server, try catch
	}
	
	@RequestMapping(value="/article/{articleId}/image/{imageFileId}", method=RequestMethod.GET)
	public void getArticleImage(
			@PathVariable("articleId") String articleId,//지금 로직으로는 필요 없다.
			@PathVariable("imageFileId") String imageFileId,
			HttpServletResponse res)
			throws NotSignedInException, IOException {
		//get file
		File imageFile = fileService.getFile(imageFileId);
		String fileExt = FilenameUtils.getExtension(imageFile.getPath());
		
		//validate before serve image
		validateFileServedAsImage(fileExt);
		
		//set response and serve image
		res.setContentType("image/"+fileExt);
		InputStream imgInputStream = FileUtils.openInputStream(imageFile);
		IOUtils.copy(imgInputStream, res.getOutputStream());
		
		if (imgInputStream != null) {
			imgInputStream.close();
		}
		
		//TODO optimize performance, image server, try catch
	}
	
	
	
	
	public void validateFileServedAsImage(String fileExt) {
		if("".equals(fileExt)) {
			throw new IllegalStateException("this file is not image.");
		}
	}
	
}
