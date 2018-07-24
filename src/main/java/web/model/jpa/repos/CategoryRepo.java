package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{
	@Query("SELECT category "
			+ " FROM Category category "
			+ " WHERE category.owner.id = :ownerId AND "
			+ " category.isDel = FALSE")
	List<Category> findCategoriesOwnedBy(@Param("ownerId") Long ownerId);
	
	
}
