package org.kfokam48.cliniquemanagementbackend.mapper;

import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurDTO;
import org.kfokam48.cliniquemanagementbackend.model.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    private final ModelMapper modelMapper;

    public UtilisateurMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Utilisateur utilisateurDtoToUtilisateur (UtilisateurDTO utilisateurDTO){
        return modelMapper.map(utilisateurDTO, Utilisateur.class);
    }
    public UtilisateurDTO utilisateurToUtilisateurDto (Utilisateur utilisateur){
        return modelMapper.map(utilisateur, UtilisateurDTO.class);
    }

}
