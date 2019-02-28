package br.com.b2w.starwars.restful.api;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.b2w.starwars.model.Planet;
import br.com.b2w.starwars.model.PlanetListWrapper;
import br.com.b2w.starwars.model.PlanetResponse;
import reactor.core.publisher.Mono;

/**
 * StarWars Api Consumer
 * 
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@Component
public class StarWarsPlanetsAPI {

	private static final String USER_AGENT = "StarWars WebClient";
	private static final String BASE_URL = "https://swapi.co/api";

	private WebClient webClient;
	
	/**
	 * WebClient Builder
	 * @return WebClient
	 */
	@Bean
	public WebClient getWebClient() {
		return this.webClient = WebClient.builder()
				.baseUrl(BASE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
				.build();
	}
	
	/**
	 * Consumes the Star Wars Api
	 * @return Mono<PlanetListWrapper>
	 */
	public Mono<PlanetListWrapper> getPlanetsApi() {
        Mono<PlanetListWrapper> mono = webClient.get()
            .uri("/planets/")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .retrieve()
            .bodyToMono(PlanetListWrapper.class);

        return mono;
	}
	
	/**
	 * Search by name inside list from StarWars API if the planet exists.
	 * @param newPlanet
	 * @return Optional<PlanetResponse>
	 */
	public Optional<PlanetResponse> getPlanetByName(Planet newPlanet) {
		List<PlanetResponse> listPlanetResponse = getPlanetsApi()
				.flux()
				.toStream()
				.map(planet -> planet.getResults())
				.findFirst()
				.get();
		
		 return listPlanetResponse.stream()
				.filter(planet -> newPlanet.getName().equals(planet.getName()))
				.map(planet -> Optional.of(new PlanetResponse(planet)))
				.findFirst()
				.orElse(Optional.empty());
	}
}