package com.altimetrik.music.domain;

public class Album {

	private String name;

	private Track topTrack;

	private String albumUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Track getTopTrack() {
		return topTrack;
	}

	public void setTopTrack(Track topTrack) {
		this.topTrack = topTrack;
	}

	public String getAlbumUrl() {
		return albumUrl;
	}

	public void setAlbumUrl(String url) {
		this.albumUrl = url;
	}

}
