package fr.upem.devops.ws.wsapp;

import fr.upem.devops.ws.wsapp.data.PersonneRepository;
import fr.upem.devops.ws.wsapp.model.Personne;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WsAppApplicationTests {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private PersonneRepository personneRepository;

	private List<Personne> list;

	@Before

	public void init() {
		Personne p1 = new Personne(1L, "Johan");
		Personne p2 = new Personne(2L, "Jéjé");
		Personne p3 = new Personne(3L, "Juju");

		list = Arrays.asList(p1, p2, p3);

		Mockito.when(personneRepository.findById(1L)).thenReturn(Optional.of(p1));
		Mockito.when(personneRepository.findById(2L)).thenReturn(Optional.of(p2));
		Mockito.when(personneRepository.findById(3L)).thenReturn(Optional.of(p3));
		Mockito.when(personneRepository.findAll()).thenReturn(list);
	}

	@Test
	public void testGetAll() {
		Collection personnes = this.restTemplate.getForObject("http://localhost:" + port + "/personnes", Collection.class);
		assertEquals(personnes.size(), 3);
	}

	@Test
	public void testPostForObject() {
		Personne p4 = new Personne(10, "Johan");
		Mockito.when(personneRepository.save(new Personne("Johan"))).thenReturn(p4);
		Personne response = this.restTemplate.postForObject("http://localhost:" + port + "/personnes", p4, Personne.class);
		assertEquals(p4, response);
	}

	@Test
	public void testGetOne() {
		Personne p1 = new Personne(1L, "Johan");
		Personne personnes = this.restTemplate.getForObject("http://localhost:" + port + "/personnes/1", Personne.class);
		assertEquals(p1, personnes);
	}
}
