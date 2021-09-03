package br.com.framework.blog.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private MessageSource message;
	
	@ExceptionHandler({PostNotFoundException.class, CommentNotFoundException.class, GalleryNotFoundException.class})
	public ResponseEntity<?> handleNotFoundException(IException ex){
		
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
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<NotValidException> body = new ArrayList<>();
		ex.getFieldErrors().forEach(f -> {
				body.add(new NotValidException(f.getField(), message.getMessage(f, LocaleContextHolder.getLocale())));
			});
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ActionNotAllowedException.class)
	public ResponseEntity<?> handleActionNotAllowedNotFoundException(IException ex){
		
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now().format(formatter));
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	

}
