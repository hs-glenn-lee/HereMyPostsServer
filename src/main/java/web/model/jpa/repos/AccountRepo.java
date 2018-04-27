package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
