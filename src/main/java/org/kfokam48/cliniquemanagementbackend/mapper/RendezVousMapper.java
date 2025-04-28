package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.RendezVousDTO;
import org.kfokam48.cliniquemanagementbackend.model.RendezVous;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RendezVousMapper {
    private final ModelMapper modelMapper;

    public RendezVousMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RendezVous rendezVousDtoToRendezvous (RendezVousDTO rendezVousDTO){
        return modelMapper.map(rendezVousDTO, RendezVous.class);
    }

    public RendezVousDTO rendezVousToRendezvousDto(RendezVous rendezVous){
        return modelMapper.map(rendezVous, RendezVousDTO.class);
    }
}
