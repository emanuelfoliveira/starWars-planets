package br.com.b2w.starwars.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.starwars.model.Planet;
import br.com.b2w.starwars.model.PlanetListWrapper;
import br.com.b2w.starwars.service.StarWarsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * RESTful Controller Reactive
 *  
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@RestController
@RequestMapping("/starWars")
public class StarWarsReactiveController {

	@Autowired
	private StarWarsService starWarsService;

	@PostMapping("/addPlanet")
	private Mono<ResponseEntity<Planet>> addPlanet(@Valid @RequestBody Planet planet) {
		return starWarsService.addPlanet(planet);
	}

	@GetMapping("/planetsDB")
	private Flux<Planet> getPlanetsFromDB() {
		return starWarsService.getPlanetsFromDB();
	}

	@GetMapping(value = "/planetsAPI")
	private Mono<PlanetListWrapper> getPlanetsApi() {
		return starWarsService.getPlanetsApi();
	}

	@GetMapping("/planetByName/{name}")
	private Mono<ResponseEntity<Planet>> getPlanetByName(@PathVariable(value = "name") String name) {
		return starWarsService.getPlanetByName(name);
	}

	@GetMapping("/planetById/{id}")
	private Mono<ResponseEntity<Planet>> getPlanetById(@PathVariable(value = "id") String id) {
		return starWarsService.getPlanetById(id);
	}

	@DeleteMapping(value = "/planets/{name}")
	public Mono<ResponseEntity<Void>> deletePlanet(@PathVariable(value = "name") String name) {
		return starWarsService.deleteByName(name);
	}

}