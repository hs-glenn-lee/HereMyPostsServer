package web.model.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.model.jpa.entities.Account;
import web.model.jpa.repos.AccountRepo;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	EntityManager em;
	
	
	@Override
	public Account getAccount(String username) {
		
		Query q = em.createQuery("SELECT account FROM Account account WHERE account.username = :username", Account.class);
		q.setParameter("username", username);
		Account account = (Account) q.getSingleResult();
		return account;
	}

	@Override
	public Account getAccount(Long id) {
		return accountRepo.getOne(id);
	}

	@Override
	public Account createAccount(Account newAccount) {
		return accountRepo.saveAndFlush(newAccount);
	}
	
	@Override
	public Account authenticate(String username, String password) {
		
		Query q = em.createQuery("SELECT a FROM Account a WHERE a.username = :username and a.password = :password", Account.class);
		q.setParameter("username", username);
		q.setParameter("password", password);

		try {
			Account account = (Account) q.getSingleResult();
			System.out.println(account);
			System.out.println(account.getCategories().size());
			
			return account;
		}catch(NoResultException nre) {
			return null;
		}
		
	}

	@Override
	public boolean isUniqueNewUsername(String username) {
		Query q = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class);
		q.setParameter("username", username);

		try {
			Account account = (Account) q.getSingleResult();
			if(account == null)
				return true;
			else
				return false;
		}catch(NoResultException nre) {
			return true;
		}

	}

}
