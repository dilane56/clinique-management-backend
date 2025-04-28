package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.MedecinDTO;
import org.kfokam48.cliniquemanagementbackend.dto.UtilisateurDTO;
import org.kfokam48.cliniquemanagementbackend.model.Medecin;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MedecinMapper {
    private final ModelMapper modelMapper;

    public MedecinMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Medecin medecinDtoToMedecin (MedecinDTO medecinDTO){
        return modelMapper.map(medecinDTO, Medecin.class );
    }

    public MedecinDTO medecinToMedecinDto (Medecin medecin){
        return modelMapper.map(medecin, MedecinDTO.class);
    }


}
