package br.com.oi.iTunesRestApi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.oi.iTunesRestApi.model.Artist;

public interface ArtistRepository extends MongoRepository<Artist, Long> {	
		
	Optional<Artist> findByName(String name);
	Optional<Artist> findByArtistId(String id);		
	Optional<Artist> deleteByName(String name);
	Optional<Artist> deleteByArtistId(String id);
}