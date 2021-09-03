package br.com.framework.blog.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.framework.blog.controller.dto.PostDetailDto;
import br.com.framework.blog.controller.dto.PostDto;
import br.com.framework.blog.controller.form.PostForm;
import br.com.framework.blog.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Page to be loaded", defaultValue = "0"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Number of records", defaultValue = "5"),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Ordering of records(attribute,desc ou asc)")
	})
	@GetMapping
	@Cacheable("listPosts")
	public ResponseEntity<Page<PostDto>> findAll(
			@ApiIgnore @PageableDefault(sort = "creationDate", direction = Direction.DESC, 
			page = 0, size = 10) Pageable pageable){
		return ResponseEntity.ok(postService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDetailDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(postService.findById(id));
	}
	
	@PostMapping
	@CacheEvict(value = "listPosts", allEntries = true)
	public ResponseEntity<PostDetailDto> save(@Valid @RequestBody PostForm postForm, UriComponentsBuilder uriBuilder){
		PostDetailDto postDetailDto = postService.save(postForm);
		URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(postDetailDto.getId()).toUri();
		return ResponseEntity.created(uri).body(postDetailDto);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "listPosts", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id){
		postService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
