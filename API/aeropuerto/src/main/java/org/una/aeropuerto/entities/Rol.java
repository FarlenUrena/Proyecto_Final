/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author farle_000
 */
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rol implements Serializable{
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol") 
    private List<Empleado> empleados= new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 25)
    private String nombre;
    
    @Column(name = "codigo", length = 7)
    private String codigo;
    
     @Column(name = "descripcion")
    private String descripcion;
    
}
