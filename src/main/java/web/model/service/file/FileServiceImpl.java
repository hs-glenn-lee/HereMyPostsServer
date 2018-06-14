package web.model.service.file;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import web.model.service.file.policies.NewFilePolicy;

public class FileServiceImpl implements FileService{
	
	@Autowired
	StorageService storageService; 
	
	@Override
	public String saveFile(MultipartFile file, NewFilePolicy filePolicy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}

}
