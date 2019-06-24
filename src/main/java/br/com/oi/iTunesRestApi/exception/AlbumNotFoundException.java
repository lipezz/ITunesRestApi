package br.com.oi.iTunesRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {

	public AlbumNotFoundException(String exception) {
		super(exception);
	}
}