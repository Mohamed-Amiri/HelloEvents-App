package org.example.helloevent.Controller;


import org.example.helloevent.Entity.Evenement;
import org.example.helloevent.service.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvenementController {

    @GetMapping("/Evenement")
    public List<Evenement> getAllEvenement(){
        return EvenementService.getAllEvenement();
    }

    @GetMapping("{id}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable long id){
        Evenement evenement = EvenementService.getAllEvenementById(id)
                .orElseThrows("Product not found");
        return null;
    }
         private Object id;
    @PostMapping
    public ResponseEntity<Void>creatEvenement(@RequestBody Evenement evenement){
        Void newEvenement = EvenementService.saveEvenement(evenement);
        return  new ResponseEntity<>(newEvenement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Void updateEvenement(
            @PathVariable long id,
            @RequestBody Evenement evenement)
    {
          Evenement evenmentToUpdate =  EvenementService.getAllEvenementById(id);
           evenmentToUpdate.setTitre(evenement.getTitre());
           evenmentToUpdate.setCategory(evenement.getCategory());
           evenmentToUpdate.setDescription(evenement.getDescription());
           evenmentToUpdate.setLocation(evenement.getLocation());
           return EvenementService.saveEvenement(evenmentToUpdate);
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<?> deletEvenement(@PathVariable long id){
         Evenement evenementToDelet = EvenementService.getAllEvenementById(id);
        return EvenementService.deletEvenement(evenementToDelet);

    }
}
