package org.kfokam48.cliniquemanagementbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Data
//@EqualsAndHashCode(callSuper = true)

public class MedecinDTO extends UtilisateurDTO {

    private String specialite;

    // Getters et Setters
}

