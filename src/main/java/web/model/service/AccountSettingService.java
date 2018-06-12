package web.model.service;

import java.io.File;

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
	 * */
	public void saveProfilePictureFile(File uploadedPicture, Account account);

}
