package br.com.oi.iTunesRestApi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

import br.com.oi.iTunesRestApi.exception.AlbumAlreadyCreatedException;
import br.com.oi.iTunesRestApi.exception.AlbumNotFoundException;
import br.com.oi.iTunesRestApi.exception.BusinessException;
import br.com.oi.iTunesRestApi.model.Album;
import br.com.oi.iTunesRestApi.repository.AlbumRepository;
import br.com.oi.iTunesRestApi.util.Constants;

@RestController
@RequestMapping("/album")
public class AlbumController extends Constants {
    
	@Autowired
	private AlbumRepository albumRepository;
		
	@GetMapping("/")
	public List<Album> allAlbums() {		
		return albumRepository.findAll();		 
	}
	
	@GetMapping("/byArtist/{artist}")
	public Resources<Album> albumArtist(@PathVariable String artist) {
						
		if (isStringEmpty(artist))
			throw new BusinessException(ERR_NOME);
		
		List<Album> albums = albumRepository.findByArtistName(artist);
		
		if (albums.isEmpty())
			throw new AlbumNotFoundException(NO_ALBUM_FOUND + artist);
				
		return new Resources<Album>(albums);
	}
	
	@GetMapping("/byName/{name}")
	public Resource<Album> albumName(@PathVariable String name) {
		
		if (isStringEmpty(name))
			throw new BusinessException(ERR_NOME);
		
		Optional<Album> album = albumRepository.findByName(name);
		
		if (!album.isPresent())
			throw new AlbumNotFoundException(ALBUM_NOT_FOUND + " Name: " + name);
						
		return new Resource<Album>(album.get());
	}
			
	@GetMapping("/byId/{id}")
	public Resource<Album> albumId(@PathVariable String id) {
		
		if (isStringEmpty(id))
			throw new BusinessException(ERR_ID);
		
		Optional<Album> album = albumRepository.findByAlbumId(id);
		
		if (!album.isPresent())
			throw new AlbumNotFoundException(ALBUM_NOT_FOUND + " Id: " + id);

		return new Resource<Album>(album.get());
	}
	
	@PostMapping("/")
	public ResponseEntity<Album> newAlbum(@RequestBody Album newAlbum) {
		
		if (!isAlbumObjValid(newAlbum))
			throw new BusinessException(ERR_DATA);		
		
		URI location = null;
		
		Optional<Album> album = albumRepository.findByAlbumId(newAlbum.getAlbumId());
		
		if (album.isPresent())
			throw new AlbumAlreadyCreatedException(ALBUM_ALREADY_CREATED +" Id: "+ newAlbum.getAlbumId() +" Name: "+ newAlbum.getName());
				
		albumRepository.save(newAlbum);
		
		location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newAlbum.getAlbumId()).toUri();
	
		return ResponseEntity.created(location).build();
	}	
    	
	@PutMapping("/")
	public ResponseEntity<Object> editAlbum(@RequestBody Album newAlbum) {
		
		if (!isAlbumObjValid(newAlbum))
			throw new BusinessException(ERR_DATA);
		
		Optional<Album> optAlbum = albumRepository.findByAlbumId(newAlbum.getAlbumId());
		
		if (!optAlbum.isPresent())
			throw new AlbumNotFoundException(ALBUM_NOT_FOUND + " Id: " + newAlbum.getAlbumId());
		else 
			albumRepository.deleteByAlbumId(newAlbum.getAlbumId());
		
		albumRepository.save(newAlbum);
		
		return ResponseEntity.ok().build();
	}
				
	@DeleteMapping("/byId/{id}")
	public ResponseEntity<Object> deleteAlbumId(@PathVariable String id) {				
		
		if (isStringEmpty(id))
			throw new BusinessException(ERR_ID);
		
		Optional<Album> album = albumRepository.findByAlbumId(id);
		
		if (!album.isPresent())
			throw new AlbumNotFoundException(ALBUM_NOT_FOUND + " Id: " + id);
		
		albumRepository.deleteByAlbumId(id);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/byName/{name}")
	public ResponseEntity<Object> deleteAlbumName(@PathVariable String name) {				
		
		if (isStringEmpty(name))
			throw new BusinessException(ERR_NOME);
		
		Optional<Album> album = albumRepository.findByName(name);
				
		if (!album.isPresent())
			throw new AlbumNotFoundException(ALBUM_NOT_FOUND + " Name: " + name);
		
		albumRepository.deleteByName(name);
		
		return ResponseEntity.ok().build();
	}
	
	private boolean isStringEmpty(String string) {
		if( (string==null) || (string.isEmpty() )) return true;
		return false;
	}
	
	private boolean isAlbumObjValid(Album alb) {		
		return !isStringEmpty(alb.getAlbumId())  && 
			   !isStringEmpty(alb.getArtistId()) && 
			   !isStringEmpty(alb.getArtistName()) &&
			   !isStringEmpty(alb.getName());		
	}		
}