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
			+ " where aa.username = :username")
	Page<Article> findRecentArticlesPageByUsername(@Param("username")String username, Pageable pageable);
	
	@Query("SELECT COUNT(article) FROM Article article WHERE article.author.username= :username")
    Long countByUsername(@Param("username")String username);
	
	@Query("SELECT article FROM Article article "
			+ " WHERE article.category in :categoryList ")
	List<Article> getArticlesOf(@Param("categoryList") List<Category> categories);
	
}
