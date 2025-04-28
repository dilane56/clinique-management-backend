package org.kfokam48.cliniquemanagementbackend.controlleur;


import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.RendezVousDTO;
import org.kfokam48.cliniquemanagementbackend.model.RendezVous;
import org.kfokam48.cliniquemanagementbackend.service.impl.RendezVousServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {
    private final RendezVousServiceImpl rendezVousService;

    public RendezVousController(RendezVousServiceImpl rendezVousService) {
        this.rendezVousService = rendezVousService;
    }
    @PostMapping("/create")
    public ResponseEntity<RendezVous> createRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = rendezVousService.save(rendezVousDTO);
        return ResponseEntity.ok(rendezVous);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousService.findAll();
        return ResponseEntity.ok(rendezVousList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable Long id) {
        RendezVous rendezVous = rendezVousService.findById(id);
        return ResponseEntity.ok(rendezVous);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id,@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVous updatedRendezVous = rendezVousService.update(id, rendezVousDTO);
        return ResponseEntity.ok(updatedRendezVous);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRendezVous(@PathVariable Long id) {
        ResponseEntity<String> response = rendezVousService.deleteById(id);
        return response;
    }

}
