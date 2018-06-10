package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.AccountSetting;

public interface AccountSettingRepo extends JpaRepository<AccountSetting, Long>{

}
