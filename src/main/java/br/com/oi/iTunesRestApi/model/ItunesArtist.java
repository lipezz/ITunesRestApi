package br.com.oi.iTunesRestApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesArtist {

	private String artistId; 
	private String artistName; 
	private String collectionId;
	private String collectionName;
	private String trackName;
		
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}	
	
	@Override
    public String toString() {
        return String.format(
                "Artist[artistId=%s, artistName=%s, collectionId=%s, collectionName=%s, trackName=%s]",
                artistId, artistName, collectionId, collectionName, trackName);
    }

}