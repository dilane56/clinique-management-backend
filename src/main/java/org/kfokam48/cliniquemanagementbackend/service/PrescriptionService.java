package org.kfokam48.cliniquemanagementbackend.service;


import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionDTO;
import org.kfokam48.cliniquemanagementbackend.model.Prescription;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PrescriptionService {
    Prescription save(PrescriptionDTO prescriptionDTO);
   Prescription findById(Long id);
    Prescription update(Long id , PrescriptionDTO prescriptionDTO);
    List<Prescription> findAll();
    ResponseEntity<String > deleteById(Long id);
}
