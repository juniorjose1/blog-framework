package br.com.framework.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.framework.blog.model.User;
import br.com.framework.blog.service.UserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByLogin(username);
		if(user != null) {
			return user;
		}
		throw new UsernameNotFoundException("Bad Credentials Invalid !");
	}

}
