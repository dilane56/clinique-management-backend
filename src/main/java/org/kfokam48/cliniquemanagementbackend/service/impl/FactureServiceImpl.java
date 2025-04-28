package org.kfokam48.cliniquemanagementbackend.service.impl;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.exception.RessourceNotFoundException;
import org.kfokam48.cliniquemanagementbackend.mapper.FactureMapper;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.kfokam48.cliniquemanagementbackend.repository.FactureRepository;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;
import org.kfokam48.cliniquemanagementbackend.service.FactureService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FactureServiceImpl implements FactureService {
     private final FactureRepository factureRepository;
     private final PatientRepository patientRepository;
    private final FactureMapper factureMapper;

    public FactureServiceImpl(FactureRepository factureRepository, PatientRepository patientRepository, FactureMapper factureMapper) {
        this.factureRepository = factureRepository;
        this.patientRepository = patientRepository;
        this.factureMapper = factureMapper;
    }

    @Override
    public Facture save(@Valid FactureDTO factureDTO) {
        return factureRepository.save(factureMapper.factureDtoToFacture(factureDTO));
    }

    @Override
    public Facture findById(Long id) {
        return factureRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Facture not found"));
    }

    @Override
    public List<Facture> findAll() {
        return factureRepository.findAll();
    }

    @Override
    public Facture update(Long id,@Valid FactureDTO factureDTO) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found"));
        facture.setMontant(factureDTO.getMontant());
        facture.setDate(factureDTO.getDate());
        Patient patient = patientRepository.findById(factureDTO.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        facture.setPatient(patient);
        return factureRepository.save(facture);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Facture not found"));
        factureRepository.delete(facture);
        return ResponseEntity.ok("Facture deleted successfully");
    }
}
