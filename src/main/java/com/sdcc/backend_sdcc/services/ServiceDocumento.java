package com.sdcc.backend_sdcc.services;

import com.sdcc.backend_sdcc.entities.Documento;
import com.sdcc.backend_sdcc.exceptions.DocumentoAlreadyExistsException;
import com.sdcc.backend_sdcc.repositories.RepositoryDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ServiceDocumento {

    @Autowired
    private RepositoryDocumento repositoryDocumento;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Documento aggiungiDocumento(Documento documento) throws DocumentoAlreadyExistsException {
        if(repositoryDocumento.existsById(documento.getId()))
            throw new DocumentoAlreadyExistsException();
        return repositoryDocumento.save(documento);
    }

    /** ------------ metodi di lettura ---------- **/
    @Transactional(readOnly = true)
    public List<Documento> mostraDocumenti(){
        return repositoryDocumento.findAll();
    }

    @Transactional(readOnly = true)
    public List<Documento> cercaPerTipo(String tipo){
        return repositoryDocumento.findByTipo(tipo);
    }

    @Transactional(readOnly = true)
    public List<Documento> ricercaConFiltri(String tipo, String tag, Integer anno, Float importo){
        String tip=null,ta = null;
        float imp = Float.MAX_VALUE;
        int an = Integer.MAX_VALUE;
        if(!tip.equals(" "))
            tip = tipo;
        if (!ta.equals(" "))
            ta=tag;
        if(anno != 0)
            an = anno;
        if(importo != 0)
            imp = importo;
        return repositoryDocumento.ricercaConFiltri(tip,ta,an,imp);
    }
}
