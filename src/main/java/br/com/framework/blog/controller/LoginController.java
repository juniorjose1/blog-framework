package br.com.framework.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.blog.config.security.TokenService;
import br.com.framework.blog.controller.dto.TokenDto;
import br.com.framework.blog.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;	
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@Valid @RequestBody LoginForm loginForm){
		UsernamePasswordAuthenticationToken userAuth = new 
				UsernamePasswordAuthenticationToken(loginForm.getLogin(), loginForm.getPassword());
		
		Authentication authenticate = authManager.authenticate(userAuth);
		String token = tokenService.gerarToken(authenticate);
		
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}

}
