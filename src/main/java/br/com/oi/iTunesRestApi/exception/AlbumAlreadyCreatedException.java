package br.com.oi.iTunesRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlbumAlreadyCreatedException extends RuntimeException {

	public AlbumAlreadyCreatedException(String exception) {
		super(exception);
	}
}