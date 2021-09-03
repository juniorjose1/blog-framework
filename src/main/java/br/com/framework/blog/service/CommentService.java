package br.com.framework.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.framework.blog.controller.dto.CommentDto;
import br.com.framework.blog.controller.form.CommentForm;
import br.com.framework.blog.exception.ActionNotAllowedException;
import br.com.framework.blog.exception.CommentNotFoundException;
import br.com.framework.blog.exception.PostNotFoundException;
import br.com.framework.blog.exception.ResourceNotFoundException;
import br.com.framework.blog.model.Comment;
import br.com.framework.blog.model.Post;
import br.com.framework.blog.model.User;
import br.com.framework.blog.repository.CommentRepository;
import br.com.framework.blog.repository.PostRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public Page<CommentDto> findByPostId(Long id, Pageable pageable){
		Page<Comment> comments = commentRepository.findByPostId(id, pageable);
		if(comments != null) {
			return CommentDto.toListCommentsDto(comments);
		}
		throw new ResourceNotFoundException();
	}
	
	public CommentDto findById(Long id) {
		Optional<Comment> comment = commentRepository.findById(id);
		Comment commentFound = comment.orElseThrow(() -> new CommentNotFoundException(id));
		return new CommentDto(commentFound);
	}
	
	public CommentDto save(CommentForm commentForm) {
		Optional<Post> post = postRepository.findById(commentForm.getIdPost());
		Post postFound = post.orElseThrow(() -> new PostNotFoundException(commentForm.getIdPost()));
		Comment comment = new Comment(commentForm, postFound);
		commentRepository.save(comment);
		return new CommentDto(comment);
	}
	
	public void deleteById(Long id) {
		User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Comment> comment = commentRepository.findById(id);
		Comment commentFound = comment.orElseThrow(() -> new CommentNotFoundException(id));
		User user = commentFound.getUser();
		if(!user.equals(userAuth)) {
			throw new ActionNotAllowedException();
		}
		commentRepository.deleteById(id);
	}

}
