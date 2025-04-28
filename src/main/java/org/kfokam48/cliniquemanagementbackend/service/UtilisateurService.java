package org.kfokam48.cliniquemanagementbackend.service;

import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurDTO;
import org.kfokam48.cliniquemanagementbackend.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UtilisateurService {
    Utilisateur save(UtilisateurDTO utilisateurDTO);
    Utilisateur findById(Long id);
    Utilisateur update(Long id, UtilisateurDTO utilisateurDTO);
    Utilisateur findByEmail(String email);
    Utilisateur findByUsername(String username);
    ResponseEntity<String> deleteById(Long id);
    List<Utilisateur> findAll();
    boolean existsByEmail(String email);
}
