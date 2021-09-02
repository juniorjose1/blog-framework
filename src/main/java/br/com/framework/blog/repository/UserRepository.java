package br.com.framework.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.blog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String login);

}
