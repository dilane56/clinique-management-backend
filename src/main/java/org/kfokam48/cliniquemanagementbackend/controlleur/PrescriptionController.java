package org.kfokam48.cliniquemanagementbackend.controlleur;

import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionDTO;
import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionResponseDTO;
import org.kfokam48.cliniquemanagementbackend.model.Prescription;
import org.kfokam48.cliniquemanagementbackend.service.impl.PrescriptionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionServiceImpl prescriptionService;

    public PrescriptionController(PrescriptionServiceImpl prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    // Endpoint pour créer une nouvelle prescription
    @PostMapping
    public ResponseEntity<PrescriptionResponseDTO> createPrescription(@RequestBody @Valid PrescriptionDTO prescriptionDTO) {
        PrescriptionResponseDTO prescription = prescriptionService.save(prescriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
    }

    // Endpoint pour récupérer une prescription par son ID
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(@PathVariable Long id) {
        PrescriptionResponseDTO prescription = prescriptionService.findById(id);
        return ResponseEntity.ok(prescription);
    }

    // Endpoint pour mettre à jour une prescription existante
    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDTO> updatePrescription(@PathVariable Long id, @RequestBody @Valid PrescriptionDTO prescriptionDTO) {
        PrescriptionResponseDTO updatedPrescription = prescriptionService.update(id, prescriptionDTO);
        return ResponseEntity.ok(updatedPrescription);
    }

    // Endpoint pour récupérer toutes les prescriptions
    @GetMapping
    public ResponseEntity<List<PrescriptionResponseDTO>> getAllPrescriptions() {
        List<PrescriptionResponseDTO> prescriptions = prescriptionService.findAll();
        return ResponseEntity.ok(prescriptions);
    }

    // Endpoint pour supprimer une prescription par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id) {
        ResponseEntity<String> response = prescriptionService.deleteById(id);
        return response;
    }
}
