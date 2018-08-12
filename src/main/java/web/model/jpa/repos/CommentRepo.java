package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{
	@Query("SELECT c from Comment c "
			+ " join fetch c.article cArticle "
			+ " left outer join fetch c.author cAuthor "
			+ " left join fetch cAuthor.accountSetting cAuthorSetting "
			+ "	where cArticle.id = :articleId "
			+ " AND c.isDel = FALSE")
	List<Comment> findByArticleId(@Param("articleId") String articleId);
	
	@Query("SELECT count(c.id) from Comment c "
			+ "	where c.article.id = :articleId "
			+ " AND c.isDel = FALSE")
	Long findCountCommentsByArticleId(@Param("articleId") String articleId);
	
	
}
