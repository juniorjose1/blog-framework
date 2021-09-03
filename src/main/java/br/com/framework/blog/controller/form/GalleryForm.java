package br.com.framework.blog.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class GalleryForm {
	
	@NotEmpty
	private String title;
	private String urlImageFeatured;
	
	@NotEmpty
	private List<String> urlImages = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlImageFeatured() {
		return urlImageFeatured;
	}

	public void setUrlImageFeatured(String urlImageFeatured) {
		this.urlImageFeatured = urlImageFeatured;
	}

	public List<String> getUrlImages() {
		return urlImages;
	}

	public void setUrlImages(List<String> urlImages) {
		this.urlImages = urlImages;
	}

	

}
