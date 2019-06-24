package br.com.oi.iTunesRestApi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artist")
public class Artist {

	private String artistId; 
	private String name;
	
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
		return String.format(
				"Artist[artistId=%s, name=%s]", 
				artistId, name);
    }		
}