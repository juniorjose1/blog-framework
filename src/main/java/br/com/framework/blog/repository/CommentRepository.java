package br.com.framework.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.blog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	Page<Comment> findByPostId(Long id, Pageable pageable);

}
