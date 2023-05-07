package com.sdcc.backend_sdcc.controllers;


import com.sdcc.backend_sdcc.entities.Documento;
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
    public ResponseEntity aggiungiProdotto(@RequestBody Documento documento){
        try{
            serviceDocumento.aggiungiDocumento(documento);
        }catch (DocumentoAlreadyExistsException e){
            return new ResponseEntity<>("IL DOCUMENTO ESITE GIÀ",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("DOCUMENTO AGGIUNTO",HttpStatus.CREATED);
    }

    @GetMapping(value = "/mostra")
    public List<Documento> mostraCatalogo(){
        return serviceDocumento.mostraDocumenti();
    }

    @GetMapping(value = "/cercaPerTipo")
    public List<Documento> cercaPerNome(String tipo){
        return serviceDocumento.cercaPerTipo(tipo);
    }

    @GetMapping(value = "/cerca")
    public List<Documento> ricercaConFiltri(@Param(value = "tipo") String tipo, @Param(value = "tag") String tag, @Param(value = "anno") Integer anno, @Param(value = "importo") Float importo){
        return serviceDocumento.ricercaConFiltri(tipo,tag,anno,importo);
    }

}
