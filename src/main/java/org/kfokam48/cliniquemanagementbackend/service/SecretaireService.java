package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.SecretaireDTO;
import org.kfokam48.cliniquemanagementbackend.model.Secretaire;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SecretaireService {
    Secretaire save(SecretaireDTO secretaireDTO);
   Secretaire findById(Long id);
    Secretaire update(Long id , SecretaireDTO secretaireDTO);
    List<Secretaire> findAll();
    ResponseEntity<String > deleteById(Long id);
}
