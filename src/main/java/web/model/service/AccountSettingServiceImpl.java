package web.model.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;
import web.model.jpa.repos.AccountRepo;
import web.model.jpa.repos.AccountSettingRepo;
import web.model.service.file.FileService;
import web.model.service.file.policies.NewProfilePictureFilePolicy;
import web.utils.JpegExtensionUtils;

@Service("accountSettingService")
@Transactional
public class AccountSettingServiceImpl implements AccountSettingService{
	
	@Autowired
	AccountSettingRepo accountSettingRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	FileService fileService;

	@Override
	public AccountSetting savePenName(String penName, Account account) {
		account = accountRepo.findOne(account.getId());
		AccountSetting accountSetting = account.getAccountSetting();
		accountSetting.setPenName(penName);
		accountSetting.setAccount(account);
		accountSettingRepo.save(accountSetting);
		return accountSetting;
	}

	@Override
	public AccountSetting saveIntroduction(String introduction, Account account) {
		account = accountRepo.findOne(account.getId());
		AccountSetting accountSetting = account.getAccountSetting();
		accountSetting.setIntroduction(introduction);
		accountSetting.setAccount(account);
		accountSettingRepo.save(accountSetting);
		return accountSetting;
	}

	@Override
	public AccountSetting findAccountSettingByAccountId(Long accountId) {
		Account account = accountRepo.findOne(accountId);
		return account.getAccountSetting();
	}

	@Override
	public AccountSetting saveAccountSetting(AccountSetting setting) {
		return accountSettingRepo.save(setting);
	}

	
	@Override
	public AccountSetting saveProfilePictureFile(MultipartFile uploadedPicture, Account account) throws IOException {
		String ext = FilenameUtils.getExtension(uploadedPicture.getOriginalFilename()).toLowerCase();
		validateProfilePicture(ext);
		ext = JpegExtensionUtils.normalize(ext);
		
		//get AccountSetting
		account = accountRepo.findOne(account.getId());
		AccountSetting setting = account.getAccountSetting();
		
		String oldProfilePictureFileId = setting.getProfilePictureFileId();

		NewProfilePictureFilePolicy filePolicy = new NewProfilePictureFilePolicy(account, ext);
		String newFileId = fileService.saveFile(uploadedPicture, filePolicy);
		setting.setProfilePictureFileId(newFileId);
		accountSettingRepo.save(setting);

		if(oldProfilePictureFileId != null) {//if null == no setted profile picture
			fileService.deleteFile(oldProfilePictureFileId);
		}
		
		return setting;
	}
	
	private static final Set<String> ALLOWED_IMAGE_FILE_TYPE;
	static {
		/*gif, png, jpeg, jpg, bmp*/
		ALLOWED_IMAGE_FILE_TYPE = new HashSet<String>();
		ALLOWED_IMAGE_FILE_TYPE.add("png");
		ALLOWED_IMAGE_FILE_TYPE.add("bmp");
		ALLOWED_IMAGE_FILE_TYPE.add("gif");
		ALLOWED_IMAGE_FILE_TYPE.add("jpg");
		ALLOWED_IMAGE_FILE_TYPE.add("jpeg");
		ALLOWED_IMAGE_FILE_TYPE.add("jpe");
		ALLOWED_IMAGE_FILE_TYPE.add("jfif");
		ALLOWED_IMAGE_FILE_TYPE.add("jif");
	}
	private void validateProfilePicture (String ext) {
		if( !ALLOWED_IMAGE_FILE_TYPE.contains( ext ) ) {
			throw new IllegalStateException(" \"" + ext + "\"는 허용되지 않는 이미지파일 형식입니다.");
		}
	}
	


}
