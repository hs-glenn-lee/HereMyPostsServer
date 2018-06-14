package web.model.service.file.policies;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import web.model.jpa.entities.Account;

public class NewProfilePictureFilePolicy implements NewFilePolicy{

	private String profilePictureFilePathString;
	private Path profilePictureFilePath;
	
	public NewProfilePictureFilePolicy (Account account, String extension) {
		NewAccountFilePolicy afp = new NewAccountFilePolicy(account);
		profilePictureFilePathString = afp.getPathString() + File.separator + "profile_picture." + extension;
		profilePictureFilePath = Paths.get(profilePictureFilePathString);
	}
	
	@Override
	public Path getPath() {
		return profilePictureFilePath;
	}

	@Override
	public String getPathString() {
		return profilePictureFilePathString;
	}
	
}
