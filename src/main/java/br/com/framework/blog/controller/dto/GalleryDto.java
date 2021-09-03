package br.com.framework.blog.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.framework.blog.model.Gallery;

public class GalleryDto {

	private Long id;
	private String title;
	private String urlImageFeatured;
	private LocalDateTime creationDate;

	public GalleryDto(Gallery gallery) {
		this.id = gallery.getId();
		this.title = gallery.getTitle();
		this.urlImageFeatured = gallery.getUrlImageFeatured();
		this.creationDate = gallery.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrlImageFeatured() {
		return urlImageFeatured;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public static Page<GalleryDto> toListGallery(Page<Gallery> galleries) {
		return galleries.map(GalleryDto::new);
	}

}
