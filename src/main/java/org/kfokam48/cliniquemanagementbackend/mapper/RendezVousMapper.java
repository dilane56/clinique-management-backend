package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.*;
import org.kfokam48.cliniquemanagementbackend.model.RendezVous;
import org.kfokam48.cliniquemanagementbackend.repository.MedecinRepository;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RendezVousMapper {
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final ModelMapper modelMapper;

    public RendezVousMapper(PatientRepository patientRepository, MedecinRepository medecinRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.modelMapper = modelMapper;
    }

    public RendezVous rendezVousDtoToRendezvous (RendezVousDTO rendezVousDTO){
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVous.setMotif(rendezVousDTO.getMotif());
        rendezVous.setPatient(patientRepository.findByUsername(rendezVousDTO.getPatientUsername())
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        rendezVous.setMedecin(medecinRepository.findByUsername(rendezVousDTO.getMedecinUsername())
                .orElseThrow(() -> new RuntimeException("Medecin not found")));

        return rendezVous;



    }

    public RendezVousDTO rendezVousToRendezvousDto(RendezVous rendezVous){
        RendezVousDTO rendezVousDTO = new RendezVousDTO();
        return rendezVousDTO;
    }
    public RendezVousResponseDTO rendezVousDtoToRendezVousResponseDto (RendezVousDTO rendezVousDTO){
       RendezVousResponseDTO rendezVousResponseDTO = new RendezVousResponseDTO();
        rendezVousResponseDTO.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVousResponseDTO.setMotif(rendezVousDTO.getMotif());
        PatientInRendezVousDTO patientDTO = patientRepository.findByUsername(rendezVousDTO.getPatientUsername())
                .map(patient -> modelMapper.map(patient, PatientInRendezVousDTO.class))
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        rendezVousResponseDTO.setPatient(patientDTO);
        MedecinInRendezVousDto medecinDTO = medecinRepository.findByUsername(rendezVousDTO.getMedecinUsername())
                .map(medecin -> modelMapper.map(medecin, MedecinInRendezVousDto.class))
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        rendezVousResponseDTO.setMedecin(medecinDTO);
        return rendezVousResponseDTO;
    }

    public List<RendezVousResponseDTO> rendezVousListToRendezVousResponseDtoList(List<RendezVous> rendezVousList) {
        return rendezVousList.stream()
                .map(rendezVous -> {
                    RendezVousResponseDTO rendezVousResponseDTO = new RendezVousResponseDTO();
                    rendezVousResponseDTO.setId(rendezVous.getId());
                    rendezVousResponseDTO.setDateRendezVous(rendezVous.getDateRendezVous());
                    rendezVousResponseDTO.setMotif(rendezVous.getMotif());
                    rendezVousResponseDTO.setPatient(modelMapper.map(rendezVous.getPatient(), PatientInRendezVousDTO.class));
                    rendezVousResponseDTO.setMedecin(modelMapper.map(rendezVous.getMedecin(), MedecinInRendezVousDto.class));
                    return rendezVousResponseDTO;
                })
                .toList();
    }
    public RendezVousResponseDTO rendezVousToRendezVousResponseDTo (RendezVous rendezVous){
        return modelMapper.map(rendezVous, RendezVousResponseDTO.class);
    }
    public RendezVousInUserDto rendezVousToRendezVousInUserDto(RendezVous rendezVous) {
        RendezVousInUserDto rendezVousInUserDto = new RendezVousInUserDto();
        rendezVousInUserDto.setId(rendezVous.getId());
        rendezVousInUserDto.setDateRendezVous(rendezVous.getDateRendezVous());
        rendezVousInUserDto.setMotif(rendezVous.getMotif());
        rendezVousInUserDto.setPatientUsername(rendezVous.getPatient().getUsername());
        rendezVousInUserDto.setMedecinUsername(rendezVous.getMedecin().getUsername());
        return rendezVousInUserDto;
    }

    public List<RendezVousInUserDto> rendezVousListToRendezVousInUserDtoList(List<RendezVous> rendezVousList) {
        return rendezVousList.stream()
                .map(this::rendezVousToRendezVousInUserDto)
                .toList();
    }
}
