package com.altimetrik.music.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {

	public ArtistNotFoundException(String exception) {
		super(exception);
	}
}
