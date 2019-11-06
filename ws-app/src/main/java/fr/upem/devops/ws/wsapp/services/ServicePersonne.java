package fr.upem.devops.ws.wsapp.services;

import fr.upem.devops.ws.wsapp.data.PersonneRepository;
import fr.upem.devops.ws.wsapp.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ServicePersonne {
    /*
    private final HashMap<Long, Personne> personnes = new HashMap<>();
    private long size;

    public Personne get(long id) { return personnes.get(id); }

    public Collection<Personne> getAll(){
        return personnes.values();
    }

    public Personne addPersonne(Personne personne){
        personne.setId(size);
        personnes.put(size++, personne);
        return personne;
    }

    public void replacePersonne(long id, Personne newData) {
        newData.setId(id);
        personnes.replace(id, newData);
    }

    public Personne delete(long id) {
        return personnes.remove(id);
    }*/

    @Autowired
    private PersonneRepository personneRepository;

    public Personne get(Long id) {
        return personneRepository.findById(id).get();
    }

    public Collection<Personne> getAll(){
        return personneRepository.findAll();
    }

    public Personne addPersonne(Personne personne){
        personneRepository.save(personne);
        return personne;
    }

    public void replacePersonne(long id, Personne newData) {
        newData.setId(id);
        personneRepository.save(newData);
    }

    public Personne delete(long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if(personne.isPresent()){
            personneRepository.delete(personne.get());
            return personne.get();
        }
        return null;
    }
}
