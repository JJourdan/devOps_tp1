package fr.upem.devops.ws.wsapp.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personne{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    public Personne(){}

    public Personne(long id, String name) {
        super();
        this.id = id;
        this.name = Objects.requireNonNull(name);
    }

    public Personne(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Personne)){
            return false;
        }
        Personne personne = (Personne)obj;
        return (this.id == personne.id) && this.name.equals(personne.name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
