package org.kfokam48.cliniquemanagementbackend.service.impl;

import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurDTO;
import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurResponseDTO;
import org.kfokam48.cliniquemanagementbackend.exception.RessourceNotFoundException;
import org.kfokam48.cliniquemanagementbackend.mapper.UtilisateurMapper;
import org.kfokam48.cliniquemanagementbackend.model.Utilisateur;
import org.kfokam48.cliniquemanagementbackend.repository.UtilisateurRepository;
import org.kfokam48.cliniquemanagementbackend.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
     private final UtilisateurMapper utilisateurMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }



    @Override
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found with id: " + id));
    }

    @Override
    public Utilisateur update(Long id,@Valid UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found with id: " + id));
        if (!utilisateurRepository.existsByEmail(utilisateurDTO.getEmail())) {
            if (utilisateurRepository.existsByUsername(utilisateurDTO.getUsername())) {
                throw new RessourceNotFoundException("Utilisateur already exists with this username");
            }
            utilisateur.setEmail(utilisateurDTO.getEmail());
            utilisateur.setUsername(utilisateurDTO.getUsername());
            utilisateur.setPassword(passwordEncoder.encode(utilisateurDTO.getPassword()));
            utilisateurRepository.save(utilisateur);
        }
        return utilisateur;
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found with email: " + email));
    }

    @Override
    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found with username: " + username));
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found with id: " + id));
        return ResponseEntity.ok("Utilisateur deleted successfully");
    }

    @Override
    public List<UtilisateurResponseDTO> findAll() {
        return utilisateurMapper.utilisateursToUtilisateurResponseDTOs(utilisateurRepository.findAll());
    }

    @Override
    public boolean existsByEmail(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    @Override
    public Utilisateur addRoleTouser(Utilisateur utilisateur, String role) {
        return null;
    }
}
