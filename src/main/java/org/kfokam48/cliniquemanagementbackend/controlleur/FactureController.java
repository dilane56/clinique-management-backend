package org.kfokam48.cliniquemanagementbackend.controlleur;

import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.kfokam48.cliniquemanagementbackend.service.impl.FactureServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facture")
public class FactureController {
    private final FactureServiceImpl factureService;

    public FactureController(FactureServiceImpl factureService) {
        this.factureService = factureService;
    }
    @PostMapping("/create")
    public ResponseEntity<Facture> createFacture(@Valid @RequestBody FactureDTO factureDTO) {
        Facture facture = factureService.save(factureDTO);
        return ResponseEntity.ok(facture);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.findAll();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) {
        Facture facture = factureService.findById(id);
        return ResponseEntity.ok(facture);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable Long id,@Valid @RequestBody FactureDTO factureDTO) {
        Facture updatedFacture = factureService.update(id, factureDTO);
        return ResponseEntity.ok(updatedFacture);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFacture(@PathVariable Long id) {
        ResponseEntity<String> response = factureService.deleteById(id);
        return response;
    }

}
