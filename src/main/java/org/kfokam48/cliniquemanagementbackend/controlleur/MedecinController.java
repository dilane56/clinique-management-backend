package org.kfokam48.cliniquemanagementbackend.controlleur;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.MedecinDTO;
import org.kfokam48.cliniquemanagementbackend.model.Medecin;
import org.kfokam48.cliniquemanagementbackend.service.impl.MedecinServiceImpl;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Medecin> createMedecin(@Valid @RequestBody MedecinDTO medecinDTO) {
        Medecin medecin = medecinService.save(medecinDTO);
        return ResponseEntity.ok(medecin);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medecin>> getAllMedecins() {
        List<Medecin> medecins = medecinService.findAll();
        return ResponseEntity.ok(medecins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Medecin medecin = medecinService.findById(id);
        return ResponseEntity.ok(medecin);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id,@Valid @RequestBody MedecinDTO medecinDTO) {
        Medecin updatedMedecin = medecinService.update(id, medecinDTO);
        return ResponseEntity.ok(updatedMedecin);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMedecin(@PathVariable Long id) {
        ResponseEntity<String> response = medecinService.deleteById(id);
        return response;
    }

}
