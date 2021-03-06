/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
 * @author thony
 */
@Entity
@Table(name = "alertas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Alerta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empleados_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "areasTrabajos_id")
    private AreaTrabajo areaTrabajo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alerta")
    private List<Imagen> imagenes = new ArrayList<>();

    @Column(name = "codigo", length = 10)
    private String codigo;

    @Column(length = 100, name = "descripcion")
    private String descripcion;
    
    @Column(length = 50, name = "emisor")
    private String emisor;
    
    @Column(length = 50, name = "receptor")
    private String receptor;
    
    @Column(length = 30, name = "asunto")
    private String asunto;
    
    @Column(length = 256, name = "mensaje")
    private String mensaje;

    @Column
    private boolean estado;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
        @Column(name = "fecha_lectura", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaLectura;

    @PrePersist
    public void prePersist() {
        estado = true;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
}