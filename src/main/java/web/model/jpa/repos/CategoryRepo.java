package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{
}
