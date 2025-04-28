package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.SecretaireDTO;
import org.kfokam48.cliniquemanagementbackend.model.Secretaire;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SecretaireMapper {
    private final ModelMapper modelMapper;

    public SecretaireMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Secretaire secretaireDtoToSecretaire(SecretaireDTO secretaireDTO){
        return modelMapper.map(secretaireDTO, Secretaire.class);
    }

    public SecretaireDTO secretaireToSecretaireDto(Secretaire secretaire){
        return modelMapper.map(secretaire, SecretaireDTO.class);
    }
}
