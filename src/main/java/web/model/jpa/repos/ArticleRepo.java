package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Article;
import web.model.jpa.entities.Category;

public interface ArticleRepo extends JpaRepository<Article, String>{
	@Query("select article from Article article "
			+ " join article.author aa"
			+ " where aa.username = :username AND"
			+ " article.isDel = FALSE")
	Page<Article> findRecentArticlesPageByUsername(@Param("username")String username, Pageable pageable);
	
	@Query("SELECT COUNT(article) FROM Article article WHERE article.author.username= :username AND article.isDel = FALSE")
    Long countByUsername(@Param("username")String username);
	
	@Query("SELECT article FROM Article article "
			+ " WHERE article.category in :categoryList AND "
			+ " article.isDel = FALSE ")
	List<Article> getArticlesOf(@Param("categoryList") List<Category> categories);
	
	@Query("SELECT article FROM Article article "
			+ " WHERE article.category.id = :categoryId AND "
			+ " article.isDel = FALSE ")
	List<Article> findArticlesOfCategory(@Param("categoryId") String categoryId);
	
}
