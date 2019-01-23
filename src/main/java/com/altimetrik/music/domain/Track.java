package com.altimetrik.music.domain;

public class Track {

	private String name;
	private String artistName;
	private String url;
	private long duration;
	private long listeners;
	private String lyrics;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getListeners() {
		return listeners;
	}

	public void setListeners(long listeners) {
		this.listeners = listeners;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

}
