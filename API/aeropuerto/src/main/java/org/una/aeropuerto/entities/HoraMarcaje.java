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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author erikg
 */
@Entity
@Table(name = "horas_marcajes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HoraMarcaje implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "hora_entrada", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date horaEntrada;
    
    @Column(name = "hora_salida", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date horaSalida;
    
    @Column(name = "fecha_modificacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaModificacion;
    
    
    
    @ManyToOne 
    @JoinColumn(name="horario_id")
    private Horario horario;
    
    @Column
    private boolean estado;
    
    @PrePersist
    public void prePersist() {
        horaEntrada = new Date();
        fechaModificacion = new Date();
    } 
    
    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }
    
}