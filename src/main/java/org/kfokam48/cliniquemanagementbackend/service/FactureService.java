package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.dto.FactureResponseDto;
import org.kfokam48.cliniquemanagementbackend.dto.FactureUpdateDTO;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FactureService {
    FactureResponseDto save(FactureDTO factureDTO);
    FactureResponseDto findById(Long id);
    List<FactureResponseDto> findAll();
    FactureResponseDto update(Long id, FactureUpdateDTO factureDTO);
    ResponseEntity<String> deleteById(Long id);
}
