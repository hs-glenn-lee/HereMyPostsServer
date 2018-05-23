package web.model.service.file.policies;

/*
 * 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * 
 * */
public class FilePolicy {
	private static String ROOT_PATH = "/usr/heremyposts/user-files";
	public String getRootPath() {
		return this.ROOT_PATH;
	}
}
