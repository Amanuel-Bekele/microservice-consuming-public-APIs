package com.altimetrik.music.servcie;

import com.altimetrik.music.domain.Album;

public interface AlbumService {

	/**
	 * Get top album last week in country
	 * 
	 * @param country
	 * @return {@link Album}
	 */
	Album getTopAlbum(String country);
}
