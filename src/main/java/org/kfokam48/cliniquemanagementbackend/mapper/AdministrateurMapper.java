package org.kfokam48.cliniquemanagementbackend.mapper;



import org.kfokam48.cliniquemanagementbackend.dto.AdministrateurDTO;
import org.kfokam48.cliniquemanagementbackend.model.Administrateur;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdministrateurMapper {
    private final ModelMapper modelMapper;

    public AdministrateurMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Administrateur administrateurDtoToAdministrateur (AdministrateurDTO administrateurDTO){
        return modelMapper.map(administrateurDTO, Administrateur.class);
    }

    public AdministrateurDTO administrateurToAdministrateurDto (Administrateur administrateur){
        return modelMapper.map(administrateur, AdministrateurDTO.class);
    }
}

