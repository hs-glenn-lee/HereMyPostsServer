package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{
	
	@Query("select act from Account act "
			+ " join fetch act.accountSetting "
			+ " where act.id = :id")
	public List<Account> findWithAccountSetting(@Param("id")Long id);
	
	public List<Account> findByUsername(String username);
	
	public List<Account> findByEmail(String email);
	
	@Query("select account from Account account "
			+ " where account.username = :username "
			+ " and account.password = :password")
	public List<Account> findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);

}
