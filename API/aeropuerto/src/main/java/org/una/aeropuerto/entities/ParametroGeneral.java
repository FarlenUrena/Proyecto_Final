/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author erikg
 */
@Entity
@Table(name = "parametros_generales")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ParametroGeneral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne 
//    @JoinColumn(name="usuarios_id")
//    private Usuario usuario;
    
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametroGeneral") 
//    private List<ParametroGeneral> parametrosgenerales = new ArrayList<>();

    
//    @ManyToOne 
//    @JoinColumn(name="permisos_id")
//    private Permiso permiso;
    
    @Column
    private boolean estado;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;


    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date();
    }
}

