package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.dto.FactureResponseDto;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;

import org.springframework.stereotype.Component;

@Component
public class FactureMapper {

    private final PatientRepository patientRepository;

    public FactureMapper( PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Facture factureDtoToFacture (FactureDTO factureDTO){
        Facture facture = new Facture();
        facture.setMontantTotal(factureDTO.getMontantTotal());
        facture.setPatient(patientRepository.findByUsername(factureDTO.getPatientUsername())
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        return facture;
    }

    public FactureResponseDto factureToFactureResponseDto (Facture facture) {
        FactureResponseDto factureResponseDto = new FactureResponseDto();
        factureResponseDto.setId(facture.getId());
        factureResponseDto.setMontantTotal(facture.getMontantTotal());
        factureResponseDto.setDateEmission(facture.getDateEmission());
        factureResponseDto.setPatientUsername(facture.getPatient().getUsername());
        factureResponseDto.setDescription(facture.getDescription());
        return factureResponseDto;
   }
    public Facture factureResponseDtoToFacture (FactureResponseDto factureResponseDto){
        Facture facture = new Facture();
        facture.setId(factureResponseDto.getId());
        facture.setMontantTotal(factureResponseDto.getMontantTotal());
        facture.setDateEmission(factureResponseDto.getDateEmission());
        facture.setDescription(factureResponseDto.getDescription());
        return facture;
    }
}
