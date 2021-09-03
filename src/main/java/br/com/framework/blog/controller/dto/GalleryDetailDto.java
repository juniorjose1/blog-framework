package br.com.framework.blog.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.framework.blog.model.Gallery;

public class GalleryDetailDto {

	private Long id;
	private String title;
	private String urlImageFeatured;
	private LocalDateTime creationDate;
	private List<String> urlImages = new ArrayList<>();
	private String nameUser;

	public GalleryDetailDto(Gallery gallery) {
		this.id = gallery.getId();
		this.title = gallery.getTitle();
		this.urlImageFeatured = gallery.getUrlImageFeatured();
		this.creationDate = gallery.getCreationDate();
		this.urlImages.addAll(gallery.getImages().stream().map(i -> i.getUrl()).collect(Collectors.toList()));
		this.nameUser = gallery.getUser().getLogin();
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

	public List<String> getUrlImages() {
		return urlImages;
	}

	public String getNameUser() {
		return nameUser;
	}

}
