package br.com.b2w.starwars.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.utils.UUIDs;

import br.com.b2w.starwars.exception.DuplicityPlanetException;
import br.com.b2w.starwars.exception.PlanetNotFoundException;
import br.com.b2w.starwars.model.Planet;
import br.com.b2w.starwars.model.PlanetListWrapper;
import br.com.b2w.starwars.model.PlanetResponse;
import br.com.b2w.starwars.respositories.PlanetRepository;
import br.com.b2w.starwars.restful.api.StarWarsPlanetsAPI;
import br.com.b2w.starwars.service.StarWarsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * All business rules of the RESTful
 * 
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@Service("starWarsService")
public class StarWarsServiceImpl implements StarWarsService {

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private StarWarsPlanetsAPI starWarsPlanetsAPI;

	@Override
	public Mono<ResponseEntity<Planet>> addPlanet(Planet planet) throws PlanetNotFoundException, DuplicityPlanetException {
		Integer movieAppearance = validateBeforInsert(planet).get().getFilms().size();

		planet.setId(UUIDs.timeBased());
		planet.setMovieAppearance(movieAppearance);

		return planetRepository.save(planet).map(responseEntity -> {
			return ResponseEntity.ok(responseEntity);
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Override
	public Flux<Planet> getPlanetsFromDB() {
		return planetRepository.findAll();
	}

	@Override
	public Mono<PlanetListWrapper> getPlanetsApi() {
		return starWarsPlanetsAPI.getPlanetsApi();
	}

	@Override
	public Mono<ResponseEntity<Planet>> getPlanetByName(String name) {
		return planetRepository.findByName(name).map(planet -> {
			return ResponseEntity.ok(planet);
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Override
	public Mono<ResponseEntity<Planet>> getPlanetById(String id) {
		return planetRepository.findById(UUID.fromString(id)).map(planet -> {
			return ResponseEntity.ok(planet);
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteByName(String name) {
		return planetRepository.findByName(name).flatMap(
				planet -> planetRepository.delete(planet).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Validate duplicity and if planet exists on API
	 * @param planet
	 * @return Optional<PlanetResponse>
	 */
	private Optional<PlanetResponse> validateBeforInsert(Planet planet) {
		Optional<PlanetResponse> optionalPlanetResponse = starWarsPlanetsAPI.getPlanetByName(planet);
		if (!optionalPlanetResponse.isPresent()) {
			throw new PlanetNotFoundException(planet.getName());
		}

		return optionalPlanetResponse;
	}
}