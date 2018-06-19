package web.model.jpa.repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Tag;

public interface TagRepo extends JpaRepository<Tag, String>{

	@Query("select tag from Tag tag "
			+ " join fetch tag.tagArticles ta "
			+ " join fetch ta.article article "
			+ " where article.id = :articleId")
	public List<Tag> findByArticleId(@Param("articleId") String articleId);
	
}
