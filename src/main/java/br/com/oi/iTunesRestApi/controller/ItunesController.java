package br.com.oi.iTunesRestApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.oi.iTunesRestApi.model.ItunesResponse;
import br.com.oi.iTunesRestApi.repository.ItunesRepository;

@RestController
@RequestMapping("/iTunes")
public class ItunesController {
    
	private ItunesRepository iTunesRepository = new ItunesRepository();	
		
	@GetMapping("/artistByName/{name}")
    public ItunesResponse iTunesArtist(@PathVariable String name) {    	       	
    	return iTunesRepository.searchArtist(name);
    }
}