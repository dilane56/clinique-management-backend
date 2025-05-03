package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.dto.FactureResponseDto;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.kfokam48.cliniquemanagementbackend.model.RendezVous;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;

import org.springframework.stereotype.Component;

import java.util.List;

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
        factureResponseDto.setRendezvousMotif(facture.getDescription());
        factureResponseDto.setDatePayement(facture.getDatePayement());
        factureResponseDto.setMontantVerser(facture.getMontantPayement());
        factureResponseDto.setMontantRestant(facture.getMontantRestant());
        factureResponseDto.setStatutPayement(facture.getStatutPayement());
        factureResponseDto.setModePayement(facture.getModePayement());
        return factureResponseDto;
   }
    public Facture factureResponseDtoToFacture (FactureResponseDto factureResponseDto){
        Facture facture = new Facture();
        facture.setId(factureResponseDto.getId());
        facture.setMontantTotal(factureResponseDto.getMontantTotal());
        facture.setDateEmission(factureResponseDto.getDateEmission());
        facture.setDescription(factureResponseDto.getRendezvousMotif());
        return facture;
    }

    public List<FactureResponseDto> factureListToFactureResponseDtoList(List<Facture> factures) {
        return factures.stream()
                .map(this::factureToFactureResponseDto)
                .toList();
    }


}
