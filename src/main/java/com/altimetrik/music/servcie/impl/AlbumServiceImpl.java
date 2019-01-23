package com.altimetrik.music.servcie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.music.domain.Album;
import com.altimetrik.music.microservice.AlbumProxy;
import com.altimetrik.music.servcie.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumProxy albumProxy;

	@Override
	public Album getTopAlbum(String country) {
		return albumProxy.getTopAlbum(country);
	}

}
