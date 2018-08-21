package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{
	@Query("SELECT category "
			+ " FROM Category category "
			+ " WHERE category.owner.id = :ownerId "
			+ " AND category.isDel = FALSE")
	List<Category> findCategoriesOwnedBy(@Param("ownerId") Long ownerId);
	
	@Query("SELECT category "
			+ " FROM Category category "
			+ " WHERE category.owner.username = :username "
			+ " AND category.isDel = FALSE")
	List<Category> findCategoriesByOwnerUsername(@Param("username") String username);
	
	@Query("SELECT category "
			+ " FROM Category category "
			+ " WHERE category.id = :categoryId "
			+ " AND category.owner.id = :ownerId "
			+ " AND category.isDel = FALSE ")
	Category findByIdAndOwner(@Param("categoryId")String categoryId, @Param("ownerId") Long ownerId);
	
	@Query("SELECT category "
			+ " FROM Category category "
			+ " WHERE category.parentId = :categoryId "
			+ " AND category.owner.id = :ownerId "
			+ " AND category.isDel = FALSE ")
	List<Category> findChildrenByIdAndOwner(@Param("categoryId")String categoryId, @Param("ownerId") Long ownerId);
}
