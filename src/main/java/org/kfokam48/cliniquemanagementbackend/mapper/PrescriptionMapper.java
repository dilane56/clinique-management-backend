package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionDTO;
import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionResponseDTO;
import org.kfokam48.cliniquemanagementbackend.model.Prescription;
import org.kfokam48.cliniquemanagementbackend.repository.MedecinRepository;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrescriptionMapper {
    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    public PrescriptionMapper(ModelMapper modelMapper, PatientRepository patientRepository, MedecinRepository medecinRepository) {
        this.modelMapper = modelMapper;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }
    public Prescription prescriptionDtoToPrescription(PrescriptionDTO prescriptionDTO){
        Prescription prescription = new Prescription();
        prescription.setPatient(patientRepository.findByUsername(prescriptionDTO.getPatientUsername())
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        prescription.setMedecin(medecinRepository.findByUsername(prescriptionDTO.getMedecinUsername())
                .orElseThrow(() -> new RuntimeException("Medecin not found")));
        prescription.setMedicaments(prescriptionDTO.getMedicament());
        prescription.setInstructions(prescriptionDTO.getInstructions());
        return prescription;
    }

    public PrescriptionDTO prescriptionToPrescriptionDto(Prescription prescription){
        return modelMapper.map(prescription, PrescriptionDTO.class);
    }

    public PrescriptionResponseDTO prescriptionToPrescriptionResponseDto(Prescription prescription){
        PrescriptionResponseDTO prescriptionResponseDTO = new PrescriptionResponseDTO();
        prescriptionResponseDTO.setId(prescription.getId());
        prescriptionResponseDTO.setMedicament(prescription.getMedicaments());
        prescriptionResponseDTO.setPatientUsername(prescription.getPatient().getUsername());
        prescriptionResponseDTO.setMedecinUsername(prescription.getMedecin().getUsername());
        prescriptionResponseDTO.setInstructions(prescription.getInstructions());
        prescriptionResponseDTO.setDatePrescription(prescription.getDatePrescription());
        return prescriptionResponseDTO;
    }

    public List<PrescriptionResponseDTO> prescriptionListToPrescriptionResponseDtoList(List<Prescription> prescriptions){
        return prescriptions.stream()
                .map(this::prescriptionToPrescriptionResponseDto)
                .toList();
    }
}
