package com.altimetrik.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.music.domain.Album;
import com.altimetrik.music.domain.Artist;
import com.altimetrik.music.servcie.AlbumService;
import com.altimetrik.music.servcie.ArtistService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/altimetrik/music")
@Api(value = "Music artist and info API")
public class MusicController {

	@Autowired
	private ArtistService artistService;

	@Autowired
	private AlbumService albumService;

	@ApiOperation(value = "Get Artist Information", response = Artist.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved Artist Information!"),
			@ApiResponse(code = 403, message = "Sorry forbidden!!"),
			@ApiResponse(code = 404, message = "URL not found") })
	@GetMapping("/artist-info/{name}")
	public ResponseEntity<?> getArtist(@PathVariable String name) {
		return new ResponseEntity<>(artistService.getArtistInfo(name), HttpStatus.OK);
	}

	@ApiOperation(value = "Get Top album with it's top track Lyrics", response = Album.class)
	@GetMapping("/top-album/{country}")
	public ResponseEntity<?> getTopAlbum(@PathVariable String country) {
		return new ResponseEntity<>(albumService.getTopAlbum(country), HttpStatus.OK);
	}

}
