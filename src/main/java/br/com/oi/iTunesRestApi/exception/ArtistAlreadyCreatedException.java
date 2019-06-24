package br.com.oi.iTunesRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArtistAlreadyCreatedException extends RuntimeException {

	public ArtistAlreadyCreatedException(String exception) {
		super(exception);
	}
}