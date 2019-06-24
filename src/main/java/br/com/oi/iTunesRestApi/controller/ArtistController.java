package br.com.oi.iTunesRestApi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.oi.iTunesRestApi.exception.ArtistAlreadyCreatedException;
import br.com.oi.iTunesRestApi.exception.ArtistNotFoundException;
import br.com.oi.iTunesRestApi.exception.BusinessException;
import br.com.oi.iTunesRestApi.model.Artist;
import br.com.oi.iTunesRestApi.repository.ArtistRepository;
import br.com.oi.iTunesRestApi.util.Constants;

@RestController
@RequestMapping("/artist")
public class ArtistController extends Constants {
    
	@Autowired
	private ArtistRepository artistRepository;
	
	@GetMapping("/index")
	public List<Artist> allArtists() {		
		return artistRepository.findAll();		 
	}
		
	@GetMapping("/byName/{name}")
	public Resource<Artist> artistName(@PathVariable String name) {
		
		if (isStringEmpty(name))
			throw new BusinessException(ERR_NOME);
		
		Optional<Artist> artist = artistRepository.findByName(name);
		
		if (!artist.isPresent())
			throw new ArtistNotFoundException(ARTIST_NOT_FOUND + " Name: " + name);
				
		return new Resource<Artist>(artist.get());
	}
	
	@GetMapping("/byId/{id}")
	public Resource<Artist> artistId(@PathVariable String id) {
		
		if (isStringEmpty(id))
			throw new BusinessException(ERR_ID);
		
		Optional<Artist> artist = artistRepository.findByArtistId(id);
		
		if (!artist.isPresent())
			throw new ArtistNotFoundException(ARTIST_NOT_FOUND + " Id: " + id);
		
		return new Resource<Artist>(artist.get());
	}
	
	@PostMapping("/new")
	public ResponseEntity<Artist> newArtist(@RequestBody Artist newArtist) {
				
		if (!isArtistObjValid(newArtist))
			throw new BusinessException(ERR_DATA);
		
		URI location = null;
		
		Optional<Artist> artist = artistRepository.findByArtistId(newArtist.getArtistId());
		
		if (artist.isPresent())
			throw new ArtistAlreadyCreatedException(ARTIST_ALREADY_CREATED +" Id: "+ newArtist.getArtistId() +" Name: "+ newArtist.getName());
				
		artistRepository.save(newArtist);
		
		location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newArtist.getArtistId()).toUri();
	
		return ResponseEntity.created(location).build();
	}	
    	
	@PutMapping("/byId")
	public ResponseEntity<Object> editArtistId(@RequestBody Artist newArtist) {
		
		if (!isArtistObjValid(newArtist))
			throw new BusinessException(ERR_DATA);
		
		Optional<Artist> optArtist = artistRepository.findByArtistId(newArtist.getArtistId());
		
		if (!optArtist.isPresent())
			throw new ArtistNotFoundException(ARTIST_NOT_FOUND + " Id: " + newArtist.getArtistId());
		else 
			artistRepository.deleteByArtistId(newArtist.getArtistId());
		
		artistRepository.save(newArtist);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/byName")
	public ResponseEntity<Object> editArtistName(@RequestBody Artist newArtist) {
		
		if (!isArtistObjValid(newArtist))
			throw new BusinessException(ERR_DATA);
		
		Optional<Artist> optArtist = artistRepository.findByName(newArtist.getName());
		
		if (!optArtist.isPresent())
			throw new ArtistNotFoundException(ARTIST_NOT_FOUND + " Id: " + newArtist.getArtistId());
		else 
			artistRepository.deleteByArtistId(newArtist.getArtistId());
		
		artistRepository.save(newArtist);
		
		return ResponseEntity.ok().build();
	}
				
	@DeleteMapping("/byId/{id}")
	public ResponseEntity<Object> deleteArtistId(@PathVariable String id) {				
		
		if (isStringEmpty(id))
			throw new BusinessException(ERR_ID);
		
		Optional<Artist> artist = artistRepository.findByArtistId(id);
		
		if (!artist.isPresent())
			throw new ArtistNotFoundException(ARTIST_NOT_FOUND + " Id: " + id);
		
		artistRepository.deleteByArtistId(id);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/byName/{name}")
	public ResponseEntity<Object> deleteArtistName(@PathVariable String name) {				
		
		if (isStringEmpty(name))
			throw new BusinessException(ERR_NOME);
		
		Optional<Artist> artist = artistRepository.findByName(name);
				
		if (!artist.isPresent())
			throw new ArtistNotFoundException(ARTIST_NOT_FOUND + " Name: " + name);
		
		artistRepository.deleteByName(name);
		
		return ResponseEntity.ok().build();
	}	
	
	private boolean isStringEmpty(String string) {
		if( (string==null) || (string.isEmpty() )) return true;
		return false;
	}
	
	private boolean isArtistObjValid(Artist art) {		
		return !isStringEmpty(art.getArtistId()) && 
			   !isStringEmpty(art.getName()) ;		
			
	}		
}