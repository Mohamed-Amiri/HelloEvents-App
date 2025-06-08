package org.example.helloevent.repository;


import org.example.helloevent.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementRepository  extends JpaRepository<Evenement, Long> {
    List<Evenement> findAllById(Long id);
}
