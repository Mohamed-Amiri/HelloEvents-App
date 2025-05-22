package org.example.helloevent.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Evenement evenement;
    private LocalDateTime reservationDate;

    public Reservation(Long id, Client client, Evenement evenement, LocalDateTime reservationDate) {
        this.id = id;
        this.client = client;
        this.evenement = evenement;
        this.reservationDate = reservationDate;
    }

    public Reservation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Evenement getEvenement() {
        return this.evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public LocalDateTime getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}
