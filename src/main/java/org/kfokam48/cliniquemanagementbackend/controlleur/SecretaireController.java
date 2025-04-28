package org.kfokam48.cliniquemanagementbackend.controlleur;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.SecretaireDTO;
import org.kfokam48.cliniquemanagementbackend.model.Secretaire;
import org.kfokam48.cliniquemanagementbackend.service.impl.SecretaireServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/secretaire")
public class SecretaireController {
    private final SecretaireServiceImpl secretaireService;

    public SecretaireController(SecretaireServiceImpl secretaireService) {
        this.secretaireService = secretaireService;
    }
    @PostMapping("/create")
    public ResponseEntity<Secretaire> createSecretaire(@Valid @RequestBody SecretaireDTO secretaireDTO) {
        Secretaire secretaire = secretaireService.save(secretaireDTO);
        return ResponseEntity.ok(secretaire);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Secretaire>> getAllSecretaires() {
        List<Secretaire> secretaires = secretaireService.findAll();
        return ResponseEntity.ok(secretaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secretaire> getSecretaireById(@PathVariable Long id) {
        Secretaire secretaire = secretaireService.findById(id);
        return ResponseEntity.ok(secretaire);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Secretaire> updateSecretaire(@PathVariable Long id,@Valid @RequestBody SecretaireDTO secretaireDTO) {
        Secretaire updatedSecretaire = secretaireService.update(id, secretaireDTO);
        return ResponseEntity.ok(updatedSecretaire);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSecretaire(@PathVariable Long id) {
        ResponseEntity<String> response = secretaireService.deleteById(id);
        return response;
    }


}
