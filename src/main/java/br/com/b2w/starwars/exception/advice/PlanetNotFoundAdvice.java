package br.com.b2w.starwars.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.b2w.starwars.exception.PlanetNotFoundException;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@ControllerAdvice
public class PlanetNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(PlanetNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String planetNotFoundHandler(PlanetNotFoundException ex) {
		return ex.getMessage();
	}
}
