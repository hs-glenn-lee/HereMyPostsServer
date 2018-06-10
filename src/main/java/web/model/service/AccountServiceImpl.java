package web.model.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.model.jpa.entities.Account;
import web.model.jpa.entities.AccountSetting;
import web.model.jpa.repos.AccountRepo;
import web.model.jpa.repos.AccountSettingRepo;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	AccountSettingRepo accountSettingRepo;
	
	@Autowired
	EntityManager em;

	
	@Override
	public Account createNewAccount(Account newAccount) throws IllegalStateException{
		validateNewAccount(newAccount);
		
		//save Account should precede before save AccountSetting, because account.id must be set first.
		Account created = accountRepo.save(newAccount);
		
		//initial empty AccountSetting
		AccountSetting actSet = new AccountSetting("","");
		actSet.setAccount(created);
		accountSettingRepo.save(actSet);

		return created;
	}
	
	/**
	 * validate account instance. if instance is invalidate throw IllegalStateException.
	 * */
	private void validateNewAccount(Account newAccount) throws IllegalStateException{
		if(!isUniqueNewUsername(newAccount.getUsername())) {
			throw new IllegalStateException("이미 사용중인 사용자명입니다.");
		}
		if(!isUniqueNewEmail(newAccount.getEmail())) {
			throw new IllegalStateException("이미 사용중인 이메일입니다.");
		}
	}

	@Override
	public Account authenticate(String username, String password) {
		List<Account> finded = accountRepo.findByUsernameAndPassword(username, password);
		if(finded.isEmpty()) {
			return null;
		}else {
			return finded.get(0);
		}
	}
	
	@Override
	public boolean isUniqueNewUsername(String username) {
		List<Account> findByName = accountRepo.findByUsername(username);
		if(!findByName.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean isUniqueNewEmail(String email) {
		List<Account> findByName = accountRepo.findByEmail(email);
		if(!findByName.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

}
