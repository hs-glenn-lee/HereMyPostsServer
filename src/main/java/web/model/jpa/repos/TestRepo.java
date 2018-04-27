package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.Test;

public interface TestRepo extends JpaRepository<Test, Integer>{
}
