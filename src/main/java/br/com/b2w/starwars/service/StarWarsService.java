package br.com.b2w.starwars.service;

import org.springframework.http.ResponseEntity;

import br.com.b2w.starwars.model.Planet;
import br.com.b2w.starwars.model.PlanetListWrapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
public interface StarWarsService {

	public Mono<ResponseEntity<Planet>> addPlanet(Planet planet);
	
	public Flux<Planet> getPlanetsFromDB();

	public Mono<PlanetListWrapper> getPlanetsApi();
	
	public Mono<ResponseEntity<Planet>> getPlanetByName(String name);
	
	public Mono<ResponseEntity<Planet>> getPlanetById(String id);
	
	public Mono<ResponseEntity<Void>> deleteByName(String name);
}
