package org.kfokam48.cliniquemanagementbackend.service.impl;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.dto.FactureResponseDto;
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

import java.time.LocalDate;
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
    public FactureResponseDto save(@Valid FactureDTO factureDTO) {
        System.out.println("Ajout d'un facture");
        Facture facture = factureMapper.factureDtoToFacture(factureDTO);
        facture.setDateEmission(LocalDate.now());
        factureRepository.save(facture);
        return factureMapper.factureToFactureResponseDto(facture);
    }

    @Override
    public FactureResponseDto findById(Long id) {
        System.out.println("recherche d'une facture");
        return factureMapper.factureToFactureResponseDto(factureRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Facture not found")));
    }

    @Override
    public List<FactureResponseDto> findAll() {
        return factureMapper.factureListToFactureResponseDtoList(factureRepository.findAll());
    }

    @Override
    public FactureResponseDto update(Long id,@Valid FactureDTO factureDTO) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RuntimeException("Facture not found"));
        facture.setMontantTotal(factureDTO.getMontantTotal());

        Patient patient = patientRepository.findByUsername(factureDTO.getPatientUsername()).orElseThrow(() -> new RuntimeException("Patient not found"));
        facture.setPatient(patient);

        return factureMapper.factureToFactureResponseDto(facture);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Facture facture = factureRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Facture not found"));
        factureRepository.delete(facture);
        return ResponseEntity.ok("Facture deleted successfully");
    }
}
