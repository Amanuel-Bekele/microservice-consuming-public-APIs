package com.altimetrik.music.microservice;

import com.altimetrik.music.domain.Artist;
import com.altimetrik.music.error.ArtistNotFoundException;

/**
 * ArtistProxy is a <b>microservcie</b> responsible for interaction with music
 * API's and return artist info
 * 
 * @author Amanuel Bekele
 *
 */
public interface ArtistProxy {

	/**
	 * Get artist info from music API by artist first name, last name or full name
	 * 
	 * @param artistName
	 * @return {@link Artist}
	 */
	Artist getArtistInfo(String artistName) throws ArtistNotFoundException;

}
