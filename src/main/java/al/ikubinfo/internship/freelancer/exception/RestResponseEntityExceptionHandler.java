package al.ikubinfo.internship.freelancer.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());
		log.error(message.getMessage(),ex);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage());
		log.error(message.getMessage(),ex);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorMessage> nullPointerException(NullPointerException ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), "Enter start date and end date");
		log.error(message.getMessage(),ex);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), "You must write all attributes");
		log.error(message.getMessage(),ex);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
		
	}

}
