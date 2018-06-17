package web.model.service.file;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.FilePathMap;
import web.model.jpa.repos.FilePathMapRepo;

@Service("filePathMapService")
public class FilePathMapServiceImpl implements FilePathMapService{
	
	@Autowired
	FilePathMapRepo fileMapRepo;
	
	@Autowired
	EntityManager em;
	
	@Override
	public FilePathMap getFilePathMap(String id) {
		return fileMapRepo.findOne(id);
	}

	@Override
	public FilePathMap putFilePathMap(String id, String path) {
		FilePathMap fpm = new FilePathMap();
		fpm.setId(id);
		fpm.setPath(path);
		em.persist(fpm);
		em.close();
		return fpm;
	}

	@Override
	public void removeFilePathMap(String id) {
		fileMapRepo.delete(id);
	}
	
}
