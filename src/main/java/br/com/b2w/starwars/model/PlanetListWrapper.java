package br.com.b2w.starwars.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class PlanetListWrapper {

	private List<PlanetResponse> results;
}
