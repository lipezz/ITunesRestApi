package br.com.oi.iTunesRestApi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.oi.iTunesRestApi.exception.AlbumNotFoundException;
import br.com.oi.iTunesRestApi.exception.BusinessException;
import br.com.oi.iTunesRestApi.model.Album;
import br.com.oi.iTunesRestApi.repository.AlbumRepository;
import br.com.oi.iTunesRestApi.util.Constants;

@RestController
@RequestMapping("/track")
public class TrackController extends Constants {
			
	@Autowired
	private AlbumRepository albumRepository;
		
	@GetMapping("/byName/{name}")
	public Resource<Album> trackByName(@PathVariable String name) {
						
		if (isStringEmpty(name))
			throw new BusinessException(ERR_NOME);
		
		Optional<Album> album = albumRepository.findByTrackName(name);
		
		if (!album.isPresent())
			throw new AlbumNotFoundException(TRACK_NOT_FOUND + name);
				
		return new Resource<Album>(album.get());
	}
	
	private boolean isStringEmpty(String string) {
		if( (string==null) || (string.isEmpty() )) return true;
		return false;
	}
}