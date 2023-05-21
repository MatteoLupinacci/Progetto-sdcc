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

    public List<Documento> cercaPerAnno(int anno) {
        return repositoryDocumento.findByAnno(anno);
    }

    @Transactional(readOnly = true)
    public List<Documento> ricercaConFiltri(String tag, Integer anno, Float importo){
        String ta = null;
        float imp = Float.MAX_VALUE;
        int an = Integer.MAX_VALUE;
        if (!ta.equals(" "))
            ta=tag;
        if(anno != 0)
            an = anno;
        if(importo != 0)
            imp = importo;
        return repositoryDocumento.ricercaConFiltri(ta,an,imp);
    }

    public void rimuoviDocumento(String id) {
        repositoryDocumento.deleteById(id);
    }

    public float[] spesePerAnno(int annoI, int annoF) {
        float[] ret = new float[annoF-annoI+1];
        int pos = 0;
        for(int i = annoI; i <= annoF; i++) {
            ret[pos] = repositoryDocumento.speseAnno(i);
            pos++;
        }
        return ret;
    }

    public Float spesePerCategoria(String tag){
        return repositoryDocumento.spesePerCategoria(tag);
    }

    public Float spesePerCategoria_Anno(int anno, String tag){
        return repositoryDocumento.spesePerCategoria_Anno(anno,tag);
    }
}
