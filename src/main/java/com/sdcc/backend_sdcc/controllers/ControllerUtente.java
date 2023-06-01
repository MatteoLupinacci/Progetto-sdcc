package com.sdcc.backend_sdcc.controllers;

import com.sdcc.backend_sdcc.entities.Documento;
import com.sdcc.backend_sdcc.entities.Utente;
import com.sdcc.backend_sdcc.exceptions.DocumentoAlreadyExistsException;
import com.sdcc.backend_sdcc.repositories.RepositoryUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/utenti")
public class ControllerUtente {

    @Autowired
    private RepositoryUtente repositoryUtente;

    @PostMapping(value = "/registra")
    public void registraUtente(@RequestBody Utente utente){
       if(!repositoryUtente.existsById(utente.getId())) {
           repositoryUtente.save(utente);
       }
    }
}
