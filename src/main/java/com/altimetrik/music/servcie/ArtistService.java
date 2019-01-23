package com.altimetrik.music.servcie;

import com.altimetrik.music.domain.Artist;
import com.altimetrik.music.error.ArtistNotFoundException;

public interface ArtistService {

	/**
	 * Get artist info using artist first name, last name or full name
	 * 
	 * @param artistName
	 * @return {@link Artist}
	 */
	Artist getArtistInfo(String artistName) throws ArtistNotFoundException;

}
