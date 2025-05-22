package org.example.helloevent.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String name;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

    public Client() {}

    public Client(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // 🔹 Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
