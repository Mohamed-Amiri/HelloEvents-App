package org.example.helloevent.service;

import org.example.helloevent.Entity.Evenement;
import org.example.helloevent.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementService {

    @Autowired
    EvenementRepository evenementRepository;

    public static List<Evenement> getAllEvenement() {
        return null;
    }

    public static Evenement getAllEvenementById(Object id) {
        return null;
    }

    public static ResponseEntity deletEvenement(Evenement evenementToDelet) {
        return null;
    }

    List<Evenement> GetALLEvenement() {
        return null;
    }

    public static Void saveEvenement(Evenement evenement) {
        return null;
    }

    Evenement getEvenement(long id) {
        return null;
    }

    void deleteEvenement(long id) {

    }
}
