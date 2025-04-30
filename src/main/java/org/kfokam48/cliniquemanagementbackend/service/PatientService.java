package org.kfokam48.cliniquemanagementbackend.service;

import org.kfokam48.cliniquemanagementbackend.dto.PatientDTO;
import org.kfokam48.cliniquemanagementbackend.dto.PatientResponseDTO;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PatientService {
    Patient save(PatientDTO patientDto);
    PatientResponseDTO findById(Long id);
    Patient update(Long id, PatientDTO patientDTO);
    ResponseEntity<String > deleteById(Long id);
    List<PatientResponseDTO> findAll();
}
