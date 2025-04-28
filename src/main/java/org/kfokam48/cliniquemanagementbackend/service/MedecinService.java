package org.kfokam48.cliniquemanagementbackend.service;

import org.kfokam48.cliniquemanagementbackend.dto.MedecinDTO;
import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurDTO;
import org.kfokam48.cliniquemanagementbackend.model.Medecin;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedecinService {
    Medecin save(MedecinDTO medecinDTO);
    Medecin findById(Long id);
    Medecin update(Long id, MedecinDTO medecinDTO);
    ResponseEntity<String > deleteById(Long id);
    List<Medecin> findAll();
}
