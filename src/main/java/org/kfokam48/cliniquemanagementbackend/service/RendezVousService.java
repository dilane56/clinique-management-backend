package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.RendezVousDTO;
import org.kfokam48.cliniquemanagementbackend.dto.RendezVousResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RendezVousService {

    RendezVousResponseDTO save(RendezVousDTO rendezVousDTO);
    RendezVousResponseDTO findById(Long Id);
    RendezVousResponseDTO update(Long id, RendezVousDTO rendezVousDTO);
    List<RendezVousResponseDTO> findAll();
    ResponseEntity<String > deleteById(Long id);
}
