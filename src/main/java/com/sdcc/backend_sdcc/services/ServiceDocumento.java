package com.sdcc.backend_sdcc.services;

import com.sdcc.backend_sdcc.entities.Documento;
import com.sdcc.backend_sdcc.entities.Utente;
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
        if(repositoryDocumento.existsByIdAndUtente(documento.getId(),documento.getUtente()))
            throw new DocumentoAlreadyExistsException();
        return repositoryDocumento.save(documento);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void rimuoviDocumento(String id, Utente utente) {
        repositoryDocumento.deleteByIdAndUtente(id, utente);
    }

    /** ------------ metodi di lettura ---------- **/
    @Transactional(readOnly = true)
    public List<Documento> mostraDocumenti(Utente utente){
        return repositoryDocumento.findByUtente(utente);
    }

    public List<Documento> cercaPerAnnoAndUtente(int anno, Utente utente) {
        return repositoryDocumento.findByAnnoAndUtente(anno,utente);
    }

    @Transactional(readOnly = true)
    public List<Documento> ricercaConFiltri(String tag, Integer anno, Float importo, String utente){
        String ta = null;
        float imp = Float.MAX_VALUE;
        int an = Integer.MAX_VALUE;
        if (!tag.equals(" ") && !tag.equals(""))
            ta=tag;
        if(anno != 0)
            an = anno;
        if(importo != 0)
            imp = importo;
        return repositoryDocumento.ricercaConFiltri(ta,an,imp,utente);
    }

    public float[] spesePerAnno(int annoI, int annoF, String utente) {
        float[] ret = new float[annoF-annoI+1];
        int pos = 0;
        for(int i = annoI; i <= annoF; i++) {
            ret[pos] = repositoryDocumento.speseAnno(i,utente);
            pos++;
        }
        return ret;
    }

    public float[] spesePerCategoria(String[] tag, String utente){
        float[] ret = new float[tag.length];
        int pos = 0;
        for(String t : tag){
            ret[pos] = repositoryDocumento.spesePerCategoria(t, utente);
            pos++;
        }
        return ret;
    }

    public float[] spesePerCategoria_Anno(int anno, String[] tag, String utente){
        float[] ret = new float[tag.length];
        int pos = 0;
        for(String t : tag){
            ret[pos] = repositoryDocumento.spesePerCategoria_Anno(anno,t, utente);
            pos++;
        }
        return ret;
    }
}
