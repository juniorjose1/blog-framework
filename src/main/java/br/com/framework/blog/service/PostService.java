package br.com.framework.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.framework.blog.controller.dto.PostDetailDto;
import br.com.framework.blog.controller.dto.PostDto;
import br.com.framework.blog.controller.form.PostForm;
import br.com.framework.blog.exception.ActionNotAllowedException;
import br.com.framework.blog.exception.PostNotFoundException;
import br.com.framework.blog.exception.ResourceNotFoundException;
import br.com.framework.blog.model.Post;
import br.com.framework.blog.model.User;
import br.com.framework.blog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public PostDetailDto save(PostForm postForm) {
		Post post = postForm.toPost();
		postRepository.save(post);
		return new PostDetailDto(post);
	}
	
	public Page<PostDto> findAll(Pageable pageable){
		Page<Post> posts = postRepository.findAll(pageable);
		if(posts != null) {
			return PostDto.toListPostDto(posts);
		}
		throw new ResourceNotFoundException();
	}
	
	public PostDetailDto findById(Long id) {
		Optional<Post> post = postRepository.findById(id);
		Post postFound = post.orElseThrow(() -> new PostNotFoundException(id));
		return new PostDetailDto(postFound);
	}
	
	public void deleteById(Long id) {
		User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Post> post = postRepository.findById(id);
		Post postFound = post.orElseThrow(() -> new PostNotFoundException(id));
		User user = postFound.getUser();
		if(!user.equals(userAuth)) {
			throw new ActionNotAllowedException();
		}
		postRepository.deleteById(id);
	}

}
