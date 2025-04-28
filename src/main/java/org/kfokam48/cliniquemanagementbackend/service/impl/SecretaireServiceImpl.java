package org.kfokam48.cliniquemanagementbackend.service.impl;

import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.SecretaireDTO;
import org.kfokam48.cliniquemanagementbackend.exception.ResourceAlreadyExistException;
import org.kfokam48.cliniquemanagementbackend.exception.RessourceNotFoundException;
import org.kfokam48.cliniquemanagementbackend.mapper.SecretaireMapper;
import org.kfokam48.cliniquemanagementbackend.model.Secretaire;
import org.kfokam48.cliniquemanagementbackend.repository.SecretaireRepository;
import org.kfokam48.cliniquemanagementbackend.repository.UtilisateurRepository;
import org.kfokam48.cliniquemanagementbackend.service.SecretaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class SecretaireServiceImpl implements SecretaireService {

    private final SecretaireRepository secretaireRepository;
    private final SecretaireMapper secretaireMapper;
    private final UtilisateurRepository utilisateurRepository;

    public SecretaireServiceImpl(SecretaireRepository secretaireRepository, SecretaireMapper secretaireMapper, UtilisateurRepository utilisateurRepository) {
        this.secretaireRepository = secretaireRepository;
        this.secretaireMapper = secretaireMapper;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Secretaire save(@Valid SecretaireDTO secretaireDTO) {
        if (utilisateurRepository.existsByEmail(secretaireDTO.getEmail())) {
            throw new ResourceAlreadyExistException("User already exists with this email");
        }
        if (utilisateurRepository.existsByUsername(secretaireDTO.getUsername())) {
            throw new ResourceAlreadyExistException("User already exists with this username");
        }
        return secretaireRepository.save(secretaireMapper.secretaireDtoToSecretaire(secretaireDTO));
    }

    @Override
    public Secretaire findById(Long id) {
        return secretaireRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Secretaire not found"));
    }

    @Override
    public Secretaire update(Long id,@Valid SecretaireDTO secretaireDTO) {
        Secretaire secretaire = secretaireRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Secretaire not found"));
        if (!utilisateurRepository.existsByEmail(secretaireDTO.getEmail())) {
            if (utilisateurRepository.existsByUsername(secretaireDTO.getUsername())) {
                throw new ResourceAlreadyExistException("User already exists with this username");
            }
            secretaire.setUsername(secretaireDTO.getUsername());
            secretaire.setEmail(secretaireDTO.getEmail());
            secretaire.setPassword(secretaireDTO.getPassword());
            secretaireRepository.save(secretaire);
            return secretaire;
        } else {
            throw new ResourceAlreadyExistException("User already exists with this email");
        }
    }

    @Override
    public List<Secretaire> findAll() {
        return secretaireRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Secretaire secretaire = secretaireRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Secretaire not found"));
        secretaireRepository.deleteById(id);
        return ResponseEntity.ok("Secretaire deleted successfully");
    }
}
