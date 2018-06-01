package web.model.service.file;

import web.model.jpa.entities.FilePathMap;

public interface FilePathMapService {
	public FilePathMap getFilePathMap(String id);
	public FilePathMap putFilePathMap(String id, String path);
}
