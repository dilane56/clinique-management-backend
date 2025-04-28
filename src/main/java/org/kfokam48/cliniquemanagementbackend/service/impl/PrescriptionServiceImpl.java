package org.kfokam48.cliniquemanagementbackend.service.impl;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionDTO;
import org.kfokam48.cliniquemanagementbackend.exception.RessourceNotFoundException;
import org.kfokam48.cliniquemanagementbackend.mapper.PrescriptionMapper;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.kfokam48.cliniquemanagementbackend.model.Prescription;
import org.kfokam48.cliniquemanagementbackend.repository.MedecinRepository;
import org.kfokam48.cliniquemanagementbackend.repository.PatientRepository;
import org.kfokam48.cliniquemanagementbackend.repository.PrescriptionRepository;
import org.kfokam48.cliniquemanagementbackend.service.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, PrescriptionMapper prescriptionMapper, PatientRepository patientRepository, MedecinRepository medecinRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
    }

    @Override
    public Prescription save(@Valid PrescriptionDTO prescriptionDTO) {
        return prescriptionRepository.save(prescriptionMapper.prescriptionDtoToPrescription(prescriptionDTO));
    }

    @Override
    public Prescription findById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Prescription not found"));
    }

    @Override
    public Prescription update(Long id,@Valid PrescriptionDTO prescriptionDTO) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Prescription not found"));
        prescription.setDate(prescriptionDTO.getDatePrescription());
        prescription.setMedicaments(prescriptionDTO.getMedicament());
        Patient patient = patientRepository.findById(prescriptionDTO.getPatientId())
                .orElseThrow(() -> new RessourceNotFoundException("Patient not found"));
        prescription.setPatient(patient);
        prescription.setMedecin(medecinRepository.findById(prescriptionDTO.getMedecinId())
                .orElseThrow(() -> new RessourceNotFoundException("Medecin not found")));
        prescriptionRepository.save(prescription);

        return prescription;
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Prescription not found"));
        prescriptionRepository.deleteById(id);
        return ResponseEntity.ok("Prescription deleted successfully");

    }
}
