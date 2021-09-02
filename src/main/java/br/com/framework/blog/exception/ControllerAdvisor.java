package br.com.framework.blog.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@ExceptionHandler({PostNotFoundException.class, ActionNotAllowedException.class})
	public ResponseEntity<?> handleTopicoNotFoundException(INotFoundException ex){
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now().format(formatter));
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> authenticationException(){
		Map<String, String> body = new HashMap<>();
		body.put("Message:", "Invalid Login/Password !");
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
