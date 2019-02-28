package br.com.b2w.starwars.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response Object
 * 
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetResponse {

	public PlanetResponse(PlanetResponse response) {
		this.name = response.getName();
		this.climate = response.getClimate();
		this.terrain = response.getTerrain();
		this.films = response.getFilms();
	}
	
	private String name;
	private String climate;
	private String terrain;
	private List<String> films;
}