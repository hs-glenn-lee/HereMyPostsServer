package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.Article;

public interface ArticleRepo extends JpaRepository<Article, String>{
	
}
