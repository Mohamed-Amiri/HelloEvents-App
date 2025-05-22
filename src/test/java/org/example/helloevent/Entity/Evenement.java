package org.example.helloevent.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Evenement {
    @Id
    @GeneratedValue
    private Long id;

    private String titre;
    private String description;
    private String location;
    private String category;

    @ManyToOne
    private Admin admin;

    @OneToMany(mappedBy = "evenement")
    private List<Reservation> reservations;

    public Evenement(Long id, String titre, String description, String location, String category, Admin admin, List<Reservation> reservations) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.location = location;
        this.category = category;
        this.admin = admin;
        this.reservations = reservations;
    }

    public Evenement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

}

