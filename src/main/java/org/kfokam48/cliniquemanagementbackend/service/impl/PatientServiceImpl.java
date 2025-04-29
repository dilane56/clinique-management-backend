package org.kfokam48.cliniquemanagementbackend.service.impl;

import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.PatientDTO;
import org.kfokam48.cliniquemanagementbackend.enums.Roles;
import org.kfokam48.cliniquemanagementbackend.exception.ResourceAlreadyExistException;
import org.kfokam48.cliniquemanagementbackend.exception.RessourceNotFoundException;
import org.kfokam48.cliniquemanagementbackend.mapper.PatientMapper;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;
import org.kfokam48.cliniquemanagementbackend.repository.UtilisateurRepository;
import org.kfokam48.cliniquemanagementbackend.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, UtilisateurRepository utilisateurRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Patient save(@Valid PatientDTO patientDto) {
        if (utilisateurRepository.existsByEmail(patientDto.getEmail())) {
            throw new ResourceAlreadyExistException("User already exists with this email");
        }
        if (utilisateurRepository.existsByUsername(patientDto.getUsername())) {
            throw new ResourceAlreadyExistException("User already exists with this username");
        }
        Patient patient = patientMapper.patientDtoToPatient(patientDto);
        patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patient.setRole(Roles.valueOf("PATIENT"));
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Patient not found"));
    }

    @Override
    public Patient update(Long id,@Valid PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Patient not found"));
       if(patient.getEmail() != patientDTO.getEmail() && utilisateurRepository.existsByEmail(patientDTO.getEmail())){
            throw new ResourceAlreadyExistException("User already exists with this email");
        }
        if(patient.getUsername() != patientDTO.getUsername() && utilisateurRepository.existsByUsername(patientDTO.getUsername())){
            throw new ResourceAlreadyExistException("User already exists with this username");
        }
        patient.setUsername(patientDTO.getUsername());
        patient.setEmail(patientDTO.getEmail());
        patient.setPassword(patientDTO.getPassword());
        return patientRepository.save(patient);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Patient not found"));
        patientRepository.deleteById(id);
        return ResponseEntity.ok("Patient deleted successfully");

    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
