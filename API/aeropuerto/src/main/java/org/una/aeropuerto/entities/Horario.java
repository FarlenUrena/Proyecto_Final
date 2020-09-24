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
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;
import lombok.Setter;

/**
 *
 * @author erik garcia
 */
@Entity
@Table(name = "horarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Horario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_entrada", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaEntrada;

    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaSalida;
    
 
    
    @Column(name = "hora_salida")
    private Long HoraSalida;
    
    @Column(name = "hora_entrada")
    private Long HoraEntrada;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaEntrada = new Date();
        fechaSalida = new Date();
    }

   
}

