package br.com.b2w.starwars.respositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import br.com.b2w.starwars.model.Planet;
import reactor.core.publisher.Mono;

/**
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
public interface PlanetRepository extends ReactiveCassandraRepository<Planet, UUID> {

	@Query(allowFiltering = true)
	Mono<Planet> findByName(String name);
	
}