package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;

@Data
public class PatientInRendezVousDTO {
    private Long id;
    private String username;
    private String email;
    private Integer numeroDossierMedical;
}
