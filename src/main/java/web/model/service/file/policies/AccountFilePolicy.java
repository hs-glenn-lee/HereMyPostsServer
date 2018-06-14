package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Account;

/*
 * User 과 관련된 파일을 쓸 떄 이 클래스에 명시된 path로 파일을 쓴다.
 * Decorator Pattern
 * 
 * */
public class AccountFilePolicy extends FilePolicy{
	
	private Long userId = null;
	
	public AccountFilePolicy(Account account) {
		this.userId = account.getId();
	}

	public String getUserPathString() {
		return super.getRootPath() + File.separator + this.userId;
	}
	
	public Path getUserPath() {
		return Paths.get(getUserPathString());
	}
	
	public String getProfilePicturePathString () {
		return this.getUserPathString() + File.separator + "profile_picture"; // file will be saved as "profile_picture"
	}
	
	public Path getProfilePicturePath() {
		return Paths.get(getProfilePicturePathString());
	}

}
