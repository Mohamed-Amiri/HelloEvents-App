package org.example.helloevent.service;

import org.example.helloevent.entity.Evenement;
import org.example.helloevent.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    private EvenementRepository evenementRepository;

    @Autowired
    public EvenementService(EvenementRepository evenementRepository){
        this.evenementRepository = evenementRepository;
    }


    public  List<Evenement> getAllEvenement() {
        return evenementRepository.findAll();
    }

    public Optional<Evenement> getEvenementById(Long id) {
        return evenementRepository.findById(id);
    }

    public  Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public void deleteEvenement(Long id) {
        if(evenementRepository.existsById(id)) {
            evenementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Evenement not found with id: " + id);
        }
    }
}
