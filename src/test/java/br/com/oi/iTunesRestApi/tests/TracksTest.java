package br.com.oi.iTunesRestApi.tests;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.oi.iTunesRestApi.model.Album;
import br.com.oi.iTunesRestApi.model.Track;

public class TracksTest {

	public static void main(String[] args) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		Album a = new Album();
		a.setAlbumId("490573904");
		a.setArtistId("3563456");
		a.setName("Great Album");
		a.setYear("2019");
		
		List<Track> tracks = new ArrayList<Track>();
		
		for (int i=0; i <= 5; i++) {
			Track t = new Track();			
			t.setName("Dreaming");
			t.setNumber(String.valueOf(i));
			
			tracks.add(t);
		}

		a.setTracks(tracks);
		
		//Object to JSON in String
		System.out.println(mapper.writeValueAsString(a));		

	}

}
