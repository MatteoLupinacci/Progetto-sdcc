package com.sdcc.backend_sdcc.repositories;


import com.sdcc.backend_sdcc.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryDocumento extends JpaRepository<Documento,String> {

    boolean existsById(String id);

    List<Documento> findByAnno(int anno);

    @Query( " select d from Documento d" +
            " WHERE (upper(d.tag) like :tag or :tag is null) and" +
            "       (d.anno = :anno or :anno is null) and" +
            "       (d.importo <= :importo or :importo is null)")
    List<Documento> ricercaConFiltri(String tag, Integer anno, Float importo);

    @Query( " select sum(d.importo) from Documento d" +
            " WHERE (d.anno = :anno)")
    int speseAnno(int anno);
}
