package com.altimetrik.music.microservice;

import com.altimetrik.music.domain.Album;
import com.altimetrik.music.domain.Track;

public interface AlbumProxy {

	/**
	 * Get top album
	 * 
	 * @param country
	 * @return {@link Album}
	 */
	Album getTopAlbum(String country);

	/**
	 * Get the most popular track last week by country from music API
	 * 
	 * @param country
	 * @return {@link Track}
	 */
	Track getTopTack(String country);

}
