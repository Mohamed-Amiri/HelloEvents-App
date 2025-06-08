package org.example.helloevent.controller;


import org.example.helloevent.entity.Evenement;
import org.example.helloevent.service.EvenementService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EvenementController {

    private EvenementService evenementService;

    public EvenementController(EvenementService evenementService){
        this.evenementService = evenementService;
    }

    @GetMapping("/All")
    public List<Evenement> getAllEvenement(){
        return evenementService.getAllEvenement();
    }

    @GetMapping("/{id}")
    public Evenement getEvenementById(@PathVariable long id){
        return evenementService.getEvenementById(id)
                .orElseThrow(() -> new RuntimeException("Evenement not found with id: " + id));
    }

    @PostMapping
    public ResponseEntity<Evenement> createEvenement(@RequestBody Evenement evenement){
        Evenement newEvenement = evenementService.saveEvenement(evenement);
        return  new ResponseEntity<>(newEvenement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Evenement updateEvenement(
            @PathVariable long id,
            @RequestBody Evenement evenement)
    {
           Evenement evenementToUpdate =  evenementService.getEvenementById(id)
                   .orElseThrow(() -> new RuntimeException("Evenement not found with id: " + id));
           evenementToUpdate.setTitre(evenement.getTitre());
           evenementToUpdate.setCategory(evenement.getCategory());
           evenementToUpdate.setDescription(evenement.getDescription());
           evenementToUpdate.setLocation(evenement.getLocation());
           return evenementService.saveEvenement(evenementToUpdate);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable long id){
        evenementService.deleteEvenement(id);
        return ResponseEntity.noContent().build();
    }


}
