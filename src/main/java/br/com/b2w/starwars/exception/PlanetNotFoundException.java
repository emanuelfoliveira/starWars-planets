package br.com.b2w.starwars.exception;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
public class PlanetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1055959428045716923L;

	public PlanetNotFoundException(String planet) {
		super("Could not find planet on API StarWars " + planet);
	}
}
