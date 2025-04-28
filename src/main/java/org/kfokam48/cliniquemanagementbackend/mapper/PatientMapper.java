package org.kfokam48.cliniquemanagementbackend.mapper;


import org.kfokam48.cliniquemanagementbackend.dto.PatientDTO;
import org.kfokam48.cliniquemanagementbackend.model.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Patient patientDtoToPatient(PatientDTO patientDTO){
        return modelMapper.map(patientDTO, Patient.class);
    }

    public PatientDTO patientToPatientDto(Patient patient){
        return modelMapper.map(patient, PatientDTO.class);
    }
}
