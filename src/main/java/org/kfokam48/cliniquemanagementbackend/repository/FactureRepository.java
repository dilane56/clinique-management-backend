package org.kfokam48.cliniquemanagementbackend.repository;


import org.kfokam48.cliniquemanagementbackend.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}
