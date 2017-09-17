package it.marco.bean;

import java.awt.Image;
import java.sql.Date;


public class MP3Bean {

	private String title,album,artist,publisher;
	private float length;
	private Date released;
	private Image cover;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setCover(Image cover) {
		this.cover = cover;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
