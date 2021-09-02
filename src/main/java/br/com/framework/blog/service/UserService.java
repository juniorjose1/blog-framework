package br.com.framework.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.blog.model.User;
import br.com.framework.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
