package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.PrescriptionDTO;
import org.kfokam48.cliniquemanagementbackend.model.Prescription;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper {
    private final ModelMapper modelMapper;

    public PrescriptionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Prescription prescriptionDtoToPrescription(PrescriptionDTO prescriptionDTO){
        return modelMapper.map(prescriptionDTO, Prescription.class);
    }

    public PrescriptionDTO prescriptionToPrescriptionDto(Prescription prescription){
        return modelMapper.map(prescription, PrescriptionDTO.class);
    }
}
