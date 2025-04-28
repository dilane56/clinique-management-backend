package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FactureService {
    Facture save(FactureDTO factureDTO);
    Facture findById(Long id);
    List<Facture> findAll();
    Facture update(Long id, FactureDTO factureDTO);
    ResponseEntity<String> deleteById(Long id);
}
