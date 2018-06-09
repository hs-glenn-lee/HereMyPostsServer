package web.model.jpa.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import web.model.jpa.entities.Series;

public interface SeriesRepo extends JpaRepository<Series, String>{
	
	@Query("select series from Series series "
			+ " join fetch series.owner own"
			+ " where own.username = :username")
	public List<Series> findByOwnerUsername(@Param("username") String username);
	
	
	@Query("select series from Series series "
			+ " join fetch series.owner own "
			+ " where own.id = :ownerId")
	public List<Series> findMySeriesByPage(@Param("ownerId") Long owenrId, Pageable pageable);
}
