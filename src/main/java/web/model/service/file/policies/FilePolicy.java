package web.model.service.file.policies;

import java.nio.file.Path;

/*
 * 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * */
public interface FilePolicy {
	public Path getPath();
	public String getPathString();
	public boolean isDir();
}
