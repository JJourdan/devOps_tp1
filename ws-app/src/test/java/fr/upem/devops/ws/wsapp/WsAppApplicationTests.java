package fr.upem.devops.ws.wsapp;

import fr.upem.devops.ws.wsapp.data.PersonneRepository;
import fr.upem.devops.ws.wsapp.model.Personne;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

	public void init(){
		Personne p1 = new Personne(1L, "Jojo");
		Personne p2 = new Personne(2L, "Jéjé");
		Personne p3 = new Personne(3L, "Juju");
		Personne p4 = new Personne("Johan");
		Personne p5 = new Personne("Jérémie");

		list = Arrays.asList(p1, p2, p3);

		Mockito.when(personneRepository.findById(1L)).thenReturn(Optional.of(p1));
		Mockito.when(personneRepository.findById(2L)).thenReturn(Optional.of(p2));
		Mockito.when(personneRepository.findById(3L)).thenReturn(Optional.of(p3));
		Mockito.when(personneRepository.findAll()).thenReturn(list);
		Mockito.when(personneRepository.save(p4)).thenReturn(p4);
		Mockito.when(personneRepository.save(p5)).thenReturn(p5);
	}

	@Test
	public void testGetAll() {
		Collection personnes = this.restTemplate.getForObject("http://localhost:" + port + "/personnes", Collection.class);
		assertEquals(personnes.size(), 3);
	}
	@Test
	public void testPostForObject() {
		Personne personne = new Personne("Johan");
		Personne response = this.restTemplate.postForObject("http://localhost:" + port + "/personnes", personne, Personne.class);
		assertEquals(personne, response);
	}

	@Test
	public void testPostForObject2() {
		Personne personne = new Personne("Johan2");
		Personne response = this.restTemplate.postForObject("http://localhost:" + port + "/personnes", personne, Personne.class);
		assertEquals(personne, response);
	}

	@Test
	public void testGetAll2() {
		Collection personnes = this.restTemplate.getForObject("http://localhost:" + port + "/personnes", Collection.class);
		assertEquals(personnes.size(), 5);
	}
}
