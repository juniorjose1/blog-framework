package br.com.framework.blog.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.framework.blog.model.Post;

public class PostForm {
	
	@NotEmpty
	@Length(min = 5)
	private String title;
	
	@NotEmpty
	@Length(min = 10)
	private String content;
	
	private String urlImageFeatured;
	private List<String> urlImages = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Post toPost() {
		return new Post(this);
	}

}
