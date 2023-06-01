package com.sdcc.backend_sdcc.repositories;

import com.sdcc.backend_sdcc.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUtente extends JpaRepository<Utente,String> {
}
