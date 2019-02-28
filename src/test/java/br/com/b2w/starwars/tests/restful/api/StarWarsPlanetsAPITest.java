package br.com.b2w.starwars.tests.restful.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.b2w.starwars.model.PlanetListWrapper;


/**
 * Test access of RESTful Controller Reactive
 *  
 * @author emanuel.foliveira
 * @since 02/27/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "36000")
public class StarWarsPlanetsAPITest {
	
	@Autowired
    private WebTestClient webTestClient;

	@Test
	public void getStarWarsApi() {
		webTestClient.get().uri("https://swapi.co/api/planets/")
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .exchange()
                    .expectStatus().isOk()
                    .returnResult(PlanetListWrapper.class);
    }
}
