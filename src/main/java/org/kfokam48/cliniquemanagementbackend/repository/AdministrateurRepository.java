package org.kfokam48.cliniquemanagementbackend.repository;



import org.kfokam48.cliniquemanagementbackend.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

 Administrateur findByEmail(String email);


}
