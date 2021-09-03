package br.com.framework.blog.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.framework.blog.controller.dto.CommentDto;
import br.com.framework.blog.controller.form.CommentForm;
import br.com.framework.blog.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
	            value = "Page to be loaded", defaultValue = "0"),
	    @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
	            value = "Number of records", defaultValue = "5"),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Ordering of records(attribute,desc ou asc)")
	})
	@GetMapping
	public ResponseEntity<Page<CommentDto>> findByPostId(@RequestParam(required = true) Long postId,
			@ApiIgnore @PageableDefault(sort = "creationDate", direction = Direction.DESC, 
			page = 0, size = 10) Pageable pageable){
		return ResponseEntity.ok(commentService.findByPostId(postId, pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CommentDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(commentService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CommentDto> save(@Valid @RequestBody CommentForm commentForm, UriComponentsBuilder uriBuilder){
		CommentDto commentDto = commentService.save(commentForm);
		URI uri = uriBuilder.path("/comments/{id}").buildAndExpand(commentDto.getId()).toUri();
		return ResponseEntity.created(uri).body(commentDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		commentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
