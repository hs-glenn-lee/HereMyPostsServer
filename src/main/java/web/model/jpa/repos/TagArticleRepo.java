package web.model.jpa.repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Tag;
import web.model.jpa.entities.TagArticle;

public interface TagArticleRepo extends JpaRepository<TagArticle, String>{

	@Query("select ta from TagArticle ta"
			+ " join fetch ta.article "
			+ " join fetch ta.tag"
			+ " where ta.article.id = :articleId ")
	public List<TagArticle> findByArticleId(@Param("articleId")String articleId);
	
	@Query("select ta from TagArticle ta "
			+ " join fetch ta.article "
			+ " join fetch ta.tag"
			+ " where ta.tag.name = :tagName ")
	public List<TagArticle> findByTagNaame(@Param("tagName") String tagName);
	
}
