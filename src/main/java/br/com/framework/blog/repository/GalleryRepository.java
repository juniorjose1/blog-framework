package br.com.framework.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.blog.model.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}
