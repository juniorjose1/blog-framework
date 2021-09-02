package br.com.framework.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.blog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
