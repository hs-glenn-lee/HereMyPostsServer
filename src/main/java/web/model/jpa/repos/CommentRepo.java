package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{
}
