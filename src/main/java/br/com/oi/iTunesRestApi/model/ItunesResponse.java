package br.com.oi.iTunesRestApi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItunesResponse {

    private long resultCount;
    private List<ItunesArtist> results;
    
	public long getResultCount() {
		return resultCount;
	}
	public void setResultCount(long resultCount) {
		this.resultCount = resultCount;
	}
	public List<ItunesArtist> getResults() {
		return results;
	}
	public void setResults(List<ItunesArtist> results) {
		this.results = results;
	}    
	
	@Override
    public String toString() {
        return "Artist{"+ printList() +"}"; 
    }
	
	private String printList(){
		StringBuilder sb = new StringBuilder();		
		for (ItunesArtist ia : results) {
			sb.append("ArtistId: "   + ia.getArtistId()       +", ");
			sb.append("ArtistName: " + ia.getArtistName()     +", ");
			sb.append("TrackName: "  + ia.getTrackName()      +", ");
			sb.append("AlbumId:   "  + ia.getCollectionId()   +",");
			sb.append("AlbumName: "  + ia.getCollectionName() +";");
			sb.append(System.getProperty("line.separator"));
		}		
		return sb.toString();
	}
}