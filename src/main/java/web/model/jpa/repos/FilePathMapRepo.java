package web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.model.jpa.entities.FilePathMap;

public interface FilePathMapRepo extends JpaRepository<FilePathMap, String>{

}
