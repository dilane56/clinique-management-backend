package org.kfokam48.cliniquemanagementbackend.service.impl;

import jakarta.validation.Valid;
import org.kfokam48.cliniquemanagementbackend.dto.MedecinDTO;
import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurDTO;
import org.kfokam48.cliniquemanagementbackend.exception.ResourceAlreadyExistException;
import org.kfokam48.cliniquemanagementbackend.exception.RessourceNotFoundException;
import org.kfokam48.cliniquemanagementbackend.mapper.MedecinMapper;
import org.kfokam48.cliniquemanagementbackend.mapper.UtilisateurMapper;
import org.kfokam48.cliniquemanagementbackend.model.Medecin;
import org.kfokam48.cliniquemanagementbackend.model.Utilisateur;
import org.kfokam48.cliniquemanagementbackend.repository.MedecinRepository;
import org.kfokam48.cliniquemanagementbackend.repository.UtilisateurRepository;
import org.kfokam48.cliniquemanagementbackend.service.MedecinService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedecinServiceImpl implements MedecinService {

    private  final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;
    private final UtilisateurRepository utilisateurRepository;

    public MedecinServiceImpl(MedecinRepository medecinRepository, MedecinMapper medecinMapper, UtilisateurRepository utilisateurRepository) {
        this.medecinRepository = medecinRepository;
        this.medecinMapper = medecinMapper;
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public Medecin save(@Valid MedecinDTO medecinDTO) {
        if (utilisateurRepository.existsByEmail(medecinDTO.getEmail())) {
            throw new ResourceAlreadyExistException("User already exists with this email");
        }
        if (utilisateurRepository.existsByUsername(medecinDTO.getUsername())) {
            throw new ResourceAlreadyExistException("User already exists with this username");
        }
        return medecinRepository.save(medecinMapper.medecinDtoToMedecin(medecinDTO));
    }

    @Override
    public Medecin findById(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Medecin not found with id: " + id));
    }

    @Override
    public Medecin update(Long id,@Valid MedecinDTO medecinDTO) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Medecin not found"));
        if (!utilisateurRepository.existsByEmail(medecinDTO.getEmail())) {
            if (utilisateurRepository.existsByUsername(medecinDTO.getUsername())) {
                throw new ResourceAlreadyExistException("User already exists with this username");
            }
            medecin.setUsername(medecinDTO.getUsername());
            medecin.setEmail(medecinDTO.getEmail());
            medecin.setPassword(medecinDTO.getPassword());
            medecin.setSpecialite(medecinDTO.getSpecialite());
            medecinRepository.save(medecin);
            return medecin;
        } else {
            throw new ResourceAlreadyExistException("User already exists with this email");
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Medecin not found"));
        medecinRepository.delete(medecin);
        return ResponseEntity.ok("Medecin deleted successfully");
    }

    @Override
    public List<Medecin> findAll() {
        return medecinRepository.findAll();
    }
}
