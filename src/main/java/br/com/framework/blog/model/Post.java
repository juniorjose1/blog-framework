package br.com.framework.blog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.framework.blog.controller.form.PostForm;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private String urlImageFeatured;
	private LocalDateTime creationDate = LocalDateTime.now();

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private List<Image> images = new ArrayList<>();
	
	public Post() {
	}

	public Post(PostForm postForm) {
		this.title = postForm.getTitle();
		this.content = postForm.getContent();
		this.urlImageFeatured = postForm.getUrlImageFeatured();
		this.images.addAll(postForm.getUrlImages().stream().map(i -> new Image(i, this)).collect(Collectors.toList()));
		this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	

}
