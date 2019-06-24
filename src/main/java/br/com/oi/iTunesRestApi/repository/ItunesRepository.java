package br.com.oi.iTunesRestApi.repository;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import br.com.oi.iTunesRestApi.model.ItunesResponse;
import br.com.oi.iTunesRestApi.util.Constants;

public class ItunesRepository extends Constants {
		
	private Log logger = LogFactory.getLog(ItunesRepository.class);
	
	private StringBuilder sb;
		
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.setConnectTimeout(30000) // 30 seconds
				.setReadTimeout(30000) // 30 seconds
				.build();
	}	
		
	public ItunesResponse searchArtist(String name) {				
		
		sb = new StringBuilder();		
		sb.append(ITUNES_SEARCH_API_URL);
		sb.append(name);
						
		RestTemplate restTemplate = getRestTemplate(new RestTemplateBuilder());
						
		//RestTemplate restTemplate = new RestTemplate(getHttpClient());
		restTemplate.setMessageConverters(getMessageConverter());	
		
		logger.info("Calling iTunes Api: "+sb.toString());
		
		ItunesResponse res = restTemplate.getForObject(sb.toString(), ItunesResponse.class);
		
		logger.info("Return >>" + res.toString());
		
        return res;
	}
				
	/*private HttpComponentsClientHttpRequestFactory getHttpComponentsClient() {
	    return new HttpComponentsClientHttpRequestFactory(); //clientHttpRequestFactory;
	}*/
	
	private SimpleClientHttpRequestFactory getHttpClient() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
	    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_IP, PROXY_PORT));
	    clientHttpRequestFactory.setProxy(proxy);
	    return clientHttpRequestFactory; //clientHttpRequestFactory;
	}
	
	private List<HttpMessageConverter<?>> getMessageConverter() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(getJacksonConverter());
		return messageConverters;
	}
	
	private MappingJackson2HttpMessageConverter getJacksonConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		return converter;
	}	
}