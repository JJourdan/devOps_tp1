package fr.upem.devops.ws.wsapp.resources;

import fr.upem.devops.ws.wsapp.model.Personne;
import fr.upem.devops.ws.wsapp.services.ServicePersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PersonneResources {

    @Autowired
    private ServicePersonne servicePersonne;

    @RequestMapping(value = "/personnes", method = RequestMethod.GET)
    @ResponseBody
    public Collection<Personne> getAll() {
        return servicePersonne.getAll();
    }

    @RequestMapping(value = "/personnes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Personne get(@PathVariable("id") long id) { return servicePersonne.get(id); }

    @RequestMapping(value = "/personnes", method = RequestMethod.POST)
    @ResponseBody
    public Personne addPersonne(@RequestBody Personne personne) {
        return servicePersonne.addPersonne(personne);
    }

    @RequestMapping(value = "/personnes/{id}", method = RequestMethod.PUT)
    public void modifyPersonne(@PathVariable("id") long id, @RequestBody Personne newData){
        servicePersonne.replacePersonne(id, newData);
    }

    @RequestMapping(value = "/personnes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Personne deletePersonne(@PathVariable("id") long id){
        return servicePersonne.delete(id);
    }
}
