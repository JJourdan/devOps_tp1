package fr.upem.devops.ws.wsapp.data;


import fr.upem.devops.ws.wsapp.model.Personne;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long> {
    List<Personne> findAll();
}
