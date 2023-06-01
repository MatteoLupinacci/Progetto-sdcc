package com.sdcc.backend_sdcc.controllers;


import com.sdcc.backend_sdcc.entities.Documento;
import com.sdcc.backend_sdcc.entities.Utente;
import com.sdcc.backend_sdcc.exceptions.DocumentoAlreadyExistsException;
import com.sdcc.backend_sdcc.services.ServiceDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/documenti")
public class ControllerDocumento {

    @Autowired
    private ServiceDocumento serviceDocumento;

    @PostMapping(value = "/aggiungi")
    public ResponseEntity aggiungiDocumento(@RequestBody Documento documento){
        try{
            serviceDocumento.aggiungiDocumento(documento);
        }catch (DocumentoAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/rimuovi")
    public ResponseEntity rimuoviDocumento(@Param(value = "id") String id){
        serviceDocumento.rimuoviDocumento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "")
    public List<Documento> mostraDocumenti(Utente utente){
        return serviceDocumento.mostraDocumenti(utente);
    }

    @GetMapping(value = "/cercaPerAnno")
    public List<Documento> cercaPerAnno(@Param(value = "anno") int anno, @Param(value = "utente")Utente utente){
        return serviceDocumento.cercaPerAnnoAndUtente(anno,utente);
    }

    @GetMapping(value = "/spesePerAnno")
    public float[] spesePerAnno(@Param(value = "annoI") int annoI, @Param(value = "annoF") int annoF, @Param(value = "utente")String utente){
        return serviceDocumento.spesePerAnno(annoI,annoF,utente);
    }

    @GetMapping(value = "/spesePerCategoria")
    public float[] spesePerCategoria(@Param(value = "tag") String[] tag, @Param(value = "utente")String utente){
        return serviceDocumento.spesePerCategoria(tag,utente);
    }

    @GetMapping(value = "/spesePerCategoria_Anno")
    public float[] spesePerCategoria_Anno(@Param(value = "anno") int anno, @Param(value = "tag") String[] tag, @Param(value = "utente")String utente){
        return serviceDocumento.spesePerCategoria_Anno(anno,tag,utente);
    }

    @GetMapping(value = "/cerca")
    public List<Documento> ricercaConFiltri(@Param(value = "tag") String tag, @Param(value = "anno") Integer anno, @Param(value = "importo") Float importo, @Param(value = "utente")String utente){
        return serviceDocumento.ricercaConFiltri(tag,anno,importo,utente);
    }

}
