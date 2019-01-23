package com.altimetrik.music.servcie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.music.domain.Artist;
import com.altimetrik.music.error.ArtistNotFoundException;
import com.altimetrik.music.microservice.ArtistProxy;
import com.altimetrik.music.servcie.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	private ArtistProxy artistProxy;

	@Override
	public Artist getArtistInfo(String artistName) throws ArtistNotFoundException {
		return artistProxy.getArtistInfo(artistName);
	}

}
