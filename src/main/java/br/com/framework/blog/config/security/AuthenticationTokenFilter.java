package br.com.framework.blog.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.framework.blog.model.User;
import br.com.framework.blog.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;
	
	public AuthenticationTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recoverToken(request);
		Boolean tokenValidate = tokenService.isToken(token);
		if(tokenValidate) {
			authenticateUser(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void authenticateUser(String token) {
		Long idUser = tokenService.getIdUser(token);
		User userAuthententicated = userRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken userAuth =
				new UsernamePasswordAuthenticationToken(
						userAuthententicated, null, userAuthententicated.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(userAuth);
	}

	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
