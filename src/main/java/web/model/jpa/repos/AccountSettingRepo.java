package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.AccountSetting;

public interface AccountSettingRepo extends JpaRepository<AccountSetting, Long>{
	@Query("select accountSetting from AccountSetting accountSetting "
			+ " join fetch accountSetting.account account"
			+ " where account.id = :accountId")
	public AccountSetting findByAccountId(@Param("accountId") Long accountId);
	
	@Query("select accountSetting from AccountSetting accountSetting "
			+ " join fetch accountSetting.account account"
			+ " where account.username = :username")
	public AccountSetting findByAccountUsername(@Param("username") String username);
}
