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
    public ResponseEntity aggiungiDocumento(@RequestBody Documento documento){
        try{
            serviceDocumento.aggiungiDocumento(documento);
        }catch (DocumentoAlreadyExistsException e){
            return new ResponseEntity<>("IL DOCUMENTO ESITE GIÀ",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("DOCUMENTO AGGIUNTO",HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/rimuovi")
    public ResponseEntity rimuoviDocumento(@Param(value = "id") String id){
        serviceDocumento.rimuoviDocumento(id);
        return new ResponseEntity<>("DOCUMENTO ELIMINATO",HttpStatus.OK);
    }

    @GetMapping(value = "")
    public List<Documento> mostraDocumenti(){
        return serviceDocumento.mostraDocumenti();
    }

    @GetMapping(value = "cercaPerAnno")
    public List<Documento> cercaPerAnno(int anno){
        return serviceDocumento.cercaPerAnno(anno);
    }

    @GetMapping(value = "/spesePerAnno")
    public float[] spesePerAnno(@Param(value = "annoI") int annoI, @Param(value = "annoF") int annoF){
        return serviceDocumento.spesePerAnno(annoI,annoF);
    }

    @GetMapping(value = "/spesePerCategoria")
    public Float spesePerCategoria(@Param(value = "tag") String tag){
        return serviceDocumento.spesePerCategoria(tag);
    }

    @GetMapping(value = "/spesePerCategoria_Anno")
    public Float spesePerCategoria_Anno(@Param(value = "anno") int anno, @Param(value = "tag") String tag){
        return serviceDocumento.spesePerCategoria_Anno(anno,tag);
    }

    @GetMapping(value = "/cerca")
    public List<Documento> ricercaConFiltri(@Param(value = "tag") String tag, @Param(value = "anno") Integer anno, @Param(value = "importo") Float importo){
        return serviceDocumento.ricercaConFiltri(tag,anno,importo);
    }

}
