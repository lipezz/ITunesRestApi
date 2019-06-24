package br.com.oi.iTunesRestApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.oi.iTunesRestApi.model.Album;

public interface AlbumRepository extends MongoRepository<Album, Long> {	
		
	Optional<Album> findByAlbumId(String albumId);
	Optional<Album> findByName(String name);	
	List<Album> findByArtistName(String artistName);
	Optional<Album> deleteByAlbumId(String albumId);
	Optional<Album> deleteByName(String name);	
	
	@Query("{ 'tracks.name' : ?0 }")
	Optional<Album> findByTrackName(String name);	
}