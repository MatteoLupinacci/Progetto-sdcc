package com.sdcc.backend_sdcc.repositories;


import com.sdcc.backend_sdcc.entities.Documento;
import com.sdcc.backend_sdcc.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryDocumento extends JpaRepository<Documento,String> {

    boolean existsByIdAndUtente(String id, Utente utente);

    void deleteByIdAndUtente(String id, Utente utente);

    List<Documento> findByAnnoAndUtente(int anno, Utente utente);

    List<Documento> findByUtente(Utente utente);

    @Query( " select d from Documento d" +
            " WHERE (d.utente like :utente) and" +
            "       (upper(d.tag) like :tag or :tag is null) and" +
            "       (d.anno = :anno or :anno is null) and" +
            "       (d.importo <= :importo or :importo is null)")
    List<Documento> ricercaConFiltri(String tag, Integer anno, Float importo, String utente);

    @Query( " select  COALESCE(SUM(d.importo), 0) from Documento d" +
            " WHERE (d.utente like :utente) and" +
            "(d.anno = :anno)")
    Float speseAnno(int anno, String utente);

    @Query( " select  COALESCE(SUM(d.importo), 0) from Documento d" +
            " WHERE (d.utente like :utente) and" +
            "(d.tag = :tag)")
    Float spesePerCategoria(String tag, String utente);

    @Query( " select COALESCE(SUM(d.importo), 0) from Documento d" +
            " WHERE (d.utene like :utente) and" +
            "(d.anno = :anno) and (d.tag = :tag)")
    Float spesePerCategoria_Anno(int anno, String tag, String utente);
}
