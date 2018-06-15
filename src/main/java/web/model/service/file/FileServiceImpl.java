package web.model.service.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.model.jpa.entities.FilePathMap;
import web.model.service.file.policies.NewFilePolicy;
import web.utils.UUIDUtil;

@Service("fileService")
public class FileServiceImpl implements FileService{
	
	@Autowired
	StorageService storageService; 
	
	@Autowired
	FilePathMapService filePathMapService;
	
	@Override
	public String saveFile(MultipartFile mFile, NewFilePolicy filePolicy) throws IOException {
		Path path = filePolicy.getPath();
		
		storageService.writeFile(mFile.getInputStream(), path);
		
		String fileId = UUIDUtil.getUUID();
		
		filePathMapService.putFilePathMap(fileId, path.toString());
		
		return fileId;
	}

	@Override
	public String saveFile(byte[] data, NewFilePolicy filePolicy) throws IOException {
		Path path = filePolicy.getPath();
		
		storageService.writeFile(data, path);
		
		String fileId = UUIDUtil.getUUID();
		filePathMapService.putFilePathMap(fileId, path.toString());
		
		return fileId;
	}
	
	@Override
	public File getFile(String fileId) {
		FilePathMap fpm = filePathMapService.getFilePathMap(fileId);
		return Paths.get(fpm.getPath()).toFile();
	}

	@Override
	public Path getPath(String fileId) {
		FilePathMap fpm = filePathMapService.getFilePathMap(fileId);
		return Paths.get(fpm.getPath());
	}

}
