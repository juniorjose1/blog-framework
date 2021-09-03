package br.com.framework.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.framework.blog.controller.dto.GalleryDetailDto;
import br.com.framework.blog.controller.dto.GalleryDto;
import br.com.framework.blog.controller.form.GalleryForm;
import br.com.framework.blog.exception.ActionNotAllowedException;
import br.com.framework.blog.exception.GalleryNotFoundException;
import br.com.framework.blog.exception.ResourceNotFoundException;
import br.com.framework.blog.model.Gallery;
import br.com.framework.blog.model.User;
import br.com.framework.blog.repository.GalleryRepository;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	public Page<GalleryDto> findAll(Pageable pageable){
		Page<Gallery> galleries = galleryRepository.findAll(pageable);
		if(galleries != null) {
			return GalleryDto.toListGallery(galleries);
		}
		throw new ResourceNotFoundException();
	}
	
	public GalleryDetailDto findById(Long id) {
		Optional<Gallery> gallery = galleryRepository.findById(id);
		Gallery galleryFound = gallery.orElseThrow(() -> new GalleryNotFoundException(id));
		return new GalleryDetailDto(galleryFound);
	}
	
	public GalleryDetailDto save(GalleryForm galleryForm) {
		Gallery gallery = new Gallery(galleryForm);
		galleryRepository.save(gallery);
		return new GalleryDetailDto(gallery);
	}
	
	public void deleteById(Long id) {
		User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Gallery> gallery = galleryRepository.findById(id);
		Gallery galleryFound = gallery.orElseThrow(() -> new GalleryNotFoundException(id));
		User user = galleryFound.getUser();
		if(!user.equals(userAuth)) {
			throw new ActionNotAllowedException();
		}
		galleryRepository.deleteById(id);
	}

}
