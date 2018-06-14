package web.model.service.file;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import web.model.service.file.policies.NewFilePolicy;

public interface FileService {
	public String saveFile(MultipartFile file, NewFilePolicy filePolicy);
	public File getFile(String fileId);
}
