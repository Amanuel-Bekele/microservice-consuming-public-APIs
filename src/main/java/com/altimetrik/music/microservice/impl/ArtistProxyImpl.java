package com.altimetrik.music.microservice.impl;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.altimetrik.music.domain.Artist;
import com.altimetrik.music.error.ArtistNotFoundException;
import com.altimetrik.music.microservice.AbstractProxy;
import com.altimetrik.music.microservice.ArtistProxy;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ArtistProxyImpl extends AbstractProxy implements ArtistProxy {

	@Value("${last.fm.api.key}")
	private String artistApiKey;

	@Value("${last.fm.api.artist.info.url}")
	private String artistApiUrl;

	@Autowired
	private RestOperations restOperation;

	@Override
	public Artist getArtistInfo(String artistName) throws ArtistNotFoundException {

		try {

			String requestUrl = MessageFormat.format(artistApiUrl, artistApiKey, artistName);

			ResponseEntity<String> apiRespose = this.restOperation.getForEntity(requestUrl, String.class);
			String aritstInfoJson = apiRespose.getBody();

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(aritstInfoJson);

			Artist artist = null;

			if (rootNode != null) {

				artist = new Artist();

				JsonNode artistNode = rootNode.path("artist");

				// Set name
				artist.setName(artistNode.path("name").asText());

				// Set url
				artist.setUrl(artistNode.path("url").asText());

				// Set summary
				JsonNode bio = artistNode.path("bio");
				artist.setSummary(bio.path("summary").asText());

				// Set image
				JsonNode images = artistNode.path("image");

				if (images.isArray()) {
					// try to set medium image
					for (JsonNode image : images) {
						if (image.path("size").asText().equals("medium")) {
							artist.setImageUrl(image.path("#text").asText());
							break;
						}
					}
				}

			}
			return artist;

		} catch (Exception ex) {
			logger.error(ex.toString());
			throw new ArtistNotFoundException("Artis not found");
		}
	}

}
