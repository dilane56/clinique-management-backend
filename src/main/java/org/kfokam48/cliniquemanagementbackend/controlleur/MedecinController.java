package org.kfokam48.cliniquemanagementbackend.controlleur;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.MedecinDTO;
import org.kfokam48.cliniquemanagementbackend.dto.MedecinResponseDTO;
import org.kfokam48.cliniquemanagementbackend.model.Medecin;
import org.kfokam48.cliniquemanagementbackend.service.impl.MedecinServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
public class MedecinController {
    private final MedecinServiceImpl medecinService;

    public MedecinController(MedecinServiceImpl medecinService) {
        this.medecinService = medecinService;
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Medecin> createMedecin(@Valid @RequestBody MedecinDTO medecinDTO) {
        Medecin medecin = medecinService.save(medecinDTO);
        return ResponseEntity.ok(medecin);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')") // Accès pour les rôles MEDECIN et ADMIN
    public ResponseEntity<List<MedecinResponseDTO>> getAllMedecins() {
        List<MedecinResponseDTO> medecins = medecinService.findAll();
        return ResponseEntity.ok(medecins);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MEDECIN') or hasRole('ADMIN')")
    public ResponseEntity<MedecinResponseDTO> getMedecinById(@PathVariable Long id) {
        MedecinResponseDTO medecin = medecinService.findById(id);
        return ResponseEntity.ok(medecin);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MEDECIN') or hasRole('ADMIN')")
    public ResponseEntity<MedecinResponseDTO> updateMedecin(@PathVariable Long id,@Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinResponseDTO updatedMedecin = medecinService.update(id, medecinDTO);
        return ResponseEntity.ok(updatedMedecin);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMedecin(@PathVariable Long id) {
        ResponseEntity<String> response = medecinService.deleteById(id);
        return response;
    }

}
