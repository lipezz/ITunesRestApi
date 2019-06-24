package br.com.oi.iTunesRestApi.util;

public class Constants {

	public static final String ITUNES_SEARCH_API_URL = "https://itunes.apple.com/search?term=";
	public static final String PROXY_IP = "10.21.7.10";
	public static final int PROXY_PORT = 82;	
	
	public static final String ERR_NOME = "Name not provided.";
	public static final String ERR_ID = "Id not provided.";
	public static final String ERR_DATA = "Some data is missing. Check your request.";
	
	public static final String ARTIST_NOT_FOUND = "Artist not found.";
	public static final String ARTIST_ALREADY_CREATED = "Artist already created.";
	
	public static final String ALBUM_NOT_FOUND = "Album not found.";
	public static final String ALBUM_ALREADY_CREATED = "Album already created.";
	public static final String NO_ALBUM_FOUND = "No Album found for artist:";
	
	public static final String TRACK_NOT_FOUND = "Track not found. ";
	public static final String TRACK_ALREADY_CREATED = "Track already created.";
}