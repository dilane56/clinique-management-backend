package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionDTO;
import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PrescriptionService {
    PrescriptionResponseDTO save(PrescriptionDTO prescriptionDTO);
   PrescriptionResponseDTO findById(Long id);
    PrescriptionResponseDTO update(Long id , PrescriptionDTO prescriptionDTO);
    List<PrescriptionResponseDTO> findAll();
    ResponseEntity<String > deleteById(Long id);
}
