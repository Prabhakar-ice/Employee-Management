package ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String,Object> body= new LinkedHashMap<>();

		body.put("timeStramp", new Date());
		body.put("status", status.value());
		
		List<String> error=ex.getBindingResult()
							.getFieldErrors()
							.stream()
							.map(x -> x.getDefaultMessage())
							.collect(Collectors.toList());
		
		body.put("errors", error);
		
		return new ResponseEntity<>(body,headers,status);
	}
}
