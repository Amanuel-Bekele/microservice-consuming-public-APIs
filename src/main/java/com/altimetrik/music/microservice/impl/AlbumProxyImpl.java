package com.altimetrik.music.microservice.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.music.domain.Album;
import com.altimetrik.music.domain.Track;
import com.altimetrik.music.microservice.AbstractProxy;
import com.altimetrik.music.microservice.AlbumProxy;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AlbumProxyImpl extends AbstractProxy implements AlbumProxy {

	@Value("${last.fm.api.key}")
	private String trackApiKey;

	@Value("${last.fm.api.track.top.url}")
	private String topTrackApiUrl;

	@Value("${last.fm.api.track.info.url}")
	private String trackInfoApiUrl;

	@Value("${music.match.api.key}")
	private String lyricsApiKey;

	@Value("${music.match.api.track.lyrics}")
	private String lyricsApiUrl;

	@Autowired
	private RestOperations restOperation;

	@Override
	public Album getTopAlbum(String country) {

		try {

			Track topTrack = getTopTack(country);

			Album album = getTrackAlbum(topTrack.getName(), topTrack.getArtistName());

			album.setTopTrack(topTrack);

			String requestUrl = MessageFormat.format(lyricsApiUrl, lyricsApiKey, topTrack.getName().trim(),
					topTrack.getArtistName().trim());

			ResponseEntity<String> apiRespose = this.restOperation.getForEntity(requestUrl, String.class);
			String trackLyricsJson = apiRespose.getBody();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(trackLyricsJson);

			if (rootNode != null) {

				// Set track lyrics
				topTrack.setLyrics(rootNode.path("message").path("body").path("lyrics").path("lyrics_body").asText());
			}

			return album;

		} catch (Exception ex) {
			logger.error(ex.toString());
			return null;
		}

	}

	@Override
	public Track getTopTack(String country) {

		try {

			String requestUrl = MessageFormat.format(topTrackApiUrl, trackApiKey, country);

			ResponseEntity<String> apiRespose = this.restOperation.getForEntity(requestUrl, String.class);
			String trackInfoJson = apiRespose.getBody();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(trackInfoJson);

			Track track = null;

			if (rootNode != null) {

				track = new Track();

				JsonNode trackNode = rootNode.path("tracks").path("track").elements().next();

				// Set name
				track.setName(trackNode.path("name").asText());

				// Set duration
				track.setDuration(trackNode.path("duration").asLong());

				// Set listeners
				track.setListeners(trackNode.path("listeners").asLong());

				// Set url
				track.setUrl(trackNode.path("url").asText());

				track.setArtistName(trackNode.path("artist").path("name").asText());

			}

			return track;

		} catch (Exception ex) {
			logger.error(ex.toString());
			return null;
		}

	}

	private Album getTrackAlbum(String trackName, String artistName) {

		try {

			String requestUrl = MessageFormat.format(trackInfoApiUrl, trackApiKey, trackName, artistName);

			ResponseEntity<String> apiRespose = this.restOperation.getForEntity(requestUrl, String.class);
			String trackInfoJson = apiRespose.getBody();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(trackInfoJson);

			Album album = null;

			if (rootNode != null) {

				album = new Album();

				JsonNode trackNode = rootNode.path("track");

				JsonNode albumNode = trackNode.path("album");

				// Set name
				album.setName(albumNode.path("title").asText());

				// Set url
				album.setAlbumUrl(albumNode.path("url").asText());
			}

			return album;
		} catch (Exception ex) {
			logger.error(ex.toString());
			return null;
		}

	}

}
