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

import br.com.framework.blog.controller.dto.GalleryDetailDto;
import br.com.framework.blog.controller.dto.GalleryDto;
import br.com.framework.blog.controller.form.GalleryForm;
import br.com.framework.blog.service.GalleryService;

@RestController
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@GetMapping
	@Cacheable("listGalleries")
	public ResponseEntity<Page<GalleryDto>> findAll(
			@PageableDefault(sort = "creationDate", direction = Direction.DESC, 
			page = 0, size = 10) Pageable pageable){
		return ResponseEntity.ok(galleryService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GalleryDetailDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(galleryService.findById(id));
	}
	
	@PostMapping
	@CacheEvict(value = "listGalleries", allEntries = true)
	public ResponseEntity<GalleryDetailDto> save(@Valid @RequestBody GalleryForm galleryForm, UriComponentsBuilder uriBuilder){
		GalleryDetailDto galleryDetailDto = galleryService.save(galleryForm);
		URI uri = uriBuilder.path("/gallery/{id}").buildAndExpand(galleryDetailDto.getId()).toUri();
		return ResponseEntity.created(uri).body(galleryDetailDto);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "listGalleries", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id){
		galleryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
