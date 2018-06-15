package web.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;

public interface AccountSettingService {
	
	/**
	 * update AccountSetting penName
	 * */
	public AccountSetting savePenName(String penName, Account account);
	
	/**
	 * update AccountSetting introduction
	 * */
	public AccountSetting saveIntroduction(String introduction, Account account);
	
	
	/**
	 * find AccountSetting by accountId
	 * */
	public AccountSetting findAccountSettingByAccountId(Long accountId);
	
	/**
	 * 
	 * */
	public AccountSetting saveAccountSetting(AccountSetting setting);
	
	/**
	 * save ProfilePictureFile
	 * return profilePicture fileId
	 * @throws IOException 
	 * */
	public AccountSetting saveProfilePictureFile(MultipartFile uploadedPicture, Account account) throws IOException;

}
