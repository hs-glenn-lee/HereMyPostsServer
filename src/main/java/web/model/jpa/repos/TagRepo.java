package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Tag;

public interface TagRepo extends JpaRepository<Tag, String>{
	
	@Query("select tag from Tag tag "
			+ " join fetch tag.owner own"
			+ " where own.username = :username")
	public List<Tag> findByOwnerUsername(@Param("username") String username);
	
	
	@Query("select tag from Tag tag "
			+ " join fetch tag.owner own "
			+ " where own.id = :ownerId")
	public List<Tag> findMyTagsByPage(@Param("ownerId") Long owenrId, Pageable pageable);
	
	@Query("select tag from Tag tag "
			+ " join fetch tag.owner own "
			+ " where own.id = :ownerId")
	public List<Tag> findAllMyTags(@Param("ownerId") Long owenrId);
}
