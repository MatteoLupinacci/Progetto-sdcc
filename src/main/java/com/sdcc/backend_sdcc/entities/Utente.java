package com.sdcc.backend_sdcc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Utente")
public class Utente {

    @Id
    @Column(nullable = false)
    private String id;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.PERSIST) @JsonIgnore
    private List<Documento> documenti;
}
