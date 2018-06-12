package web.model.service;

import java.io.File;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;
import web.model.jpa.repos.AccountRepo;
import web.model.jpa.repos.AccountSettingRepo;
import web.model.service.file.policies.UserFilePolicy;

@Service("accountSettingService")
public class AccountSettingServiceImpl implements AccountSettingService{
	
	@Autowired
	AccountSettingRepo accountSettingRepo;
	
	
	@Autowired
	AccountRepo accountRepo;

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
	public void saveProfilePictureFile(File uploadedPicture, Account account) {
		//get AccountSetting
		account = accountRepo.findOne(account.getId());
		AccountSetting setting = account.getAccountSetting();
		
		//write picture file by using fileStroageService;
		
		
		
		
		//save AccountSetting
	}



}
