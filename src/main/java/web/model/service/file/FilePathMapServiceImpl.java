package web.model.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.FilePathMap;
import web.model.jpa.repos.FilePathMapRepo;

@Service("filePathMapService")
public class FilePathMapServiceImpl implements FilePathMapService{
	
	@Autowired
	FilePathMapRepo fileMapRepo;
	
	@Override
	public FilePathMap getFilePathMap(String id) {
		return fileMapRepo.findOne(id);
	}
	
}
