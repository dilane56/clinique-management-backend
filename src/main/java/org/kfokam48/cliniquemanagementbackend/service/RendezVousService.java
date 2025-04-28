package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.RendezVousDTO;
import org.kfokam48.cliniquemanagementbackend.model.RendezVous;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RendezVousService {

    RendezVous save(RendezVousDTO rendezVousDTO);
    RendezVous findById(Long Id);
    RendezVous update(Long id, RendezVousDTO rendezVousDTO);
    List<RendezVous> findAll();
    ResponseEntity<String > deleteById(Long id);
}
