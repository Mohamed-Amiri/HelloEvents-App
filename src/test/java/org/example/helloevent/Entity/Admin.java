package org.example.helloevent.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    private String name;

    @OneToMany(mappedBy = "admin")
    private List<Evenement> evenements;

    public Admin() {}

    public Admin(Long id, String name, String email, String password, List<Evenement> evenements) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.evenements = evenements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }
}
