package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.FactureDTO;
import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FactureMapper {
    private final ModelMapper modelMapper;

    public FactureMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Facture factureDtoToFacture (FactureDTO factureDTO){
        return modelMapper.map(factureDTO, Facture.class);
    }

    public FactureDTO fatureToFactureDto (Facture facture){
        return modelMapper.map(facture,FactureDTO.class);
    }
}
