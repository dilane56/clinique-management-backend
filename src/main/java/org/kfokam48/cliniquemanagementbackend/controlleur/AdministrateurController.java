package org.kfokam48.cliniquemanagementbackend.controlleur;

;
import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.AdministrateurDTO;
import org.kfokam48.cliniquemanagementbackend.model.Administrateur;
import org.kfokam48.cliniquemanagementbackend.service.impl.AdministrateurServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrateur")
public class AdministrateurController {
    private final AdministrateurServiceImpl administrateurService;

    public AdministrateurController(AdministrateurServiceImpl administrateurService) {
        this.administrateurService = administrateurService;
    }


    @PostMapping("/create")
    public ResponseEntity<Administrateur> createAdministrateur(@Valid @RequestBody AdministrateurDTO administrateurDTO) {
        Administrateur administrateur = administrateurService.save(administrateurDTO);
        return ResponseEntity.ok(administrateur);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Administrateur>> getAllAdministrateurs() {
        List<Administrateur> administrateurs = administrateurService.findAll();
        return ResponseEntity.ok(administrateurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        Administrateur administrateur = administrateurService.findById(id);
        return ResponseEntity.ok(administrateur);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Administrateur> updateAdministrateur(@PathVariable Long id,@Valid @RequestBody AdministrateurDTO administrateurDTO) {
        Administrateur updatedAdministrateur = administrateurService.update(id, administrateurDTO);
        return ResponseEntity.ok(updatedAdministrateur);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Administrateur> getAdministrateurByEmail(@PathVariable String email) {
        Administrateur administrateur = administrateurService.findByEmail(email);
        return ResponseEntity.ok(administrateur);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdministrateur(@PathVariable Long id) {
        ResponseEntity<String> response = administrateurService.deleteById(id);
        return response;
    }


}

