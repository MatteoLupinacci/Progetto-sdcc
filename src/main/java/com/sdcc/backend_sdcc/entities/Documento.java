package com.sdcc.backend_sdcc.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Documento")
public class Documento implements Serializable {

    @Id @Column(nullable = false)
    private String id;

    @ManyToOne @JoinColumn(name = "id_utente")
    private Utente utente;

    @Basic
    private String descrizione;

    @Basic
    private float importo;

    @Basic
    private int anno;

   @Basic
   private String tag; //può essere: sanità, scuola, sport, trasporti, etc
}
