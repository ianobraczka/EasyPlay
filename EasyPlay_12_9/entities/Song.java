package entities;

import java.util.*;
import java.io.*;
import javazoom.jl.player.*;

public class Song 
{
	private String name;
	private String artist;
	private String album;
	private String genre;
	private int lenght;
	private FileInputStream location;
	
	public Song(FileInputStream dir)
	{
		location = dir;
	}
	
	
	public FileInputStream getFileInputStream (){
		return location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getLenght() {
		return lenght;
	}
	
}
