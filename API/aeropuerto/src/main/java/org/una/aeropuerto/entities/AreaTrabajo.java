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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author farle_000
 */
@Entity
@Table(name = "areasTrabajos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AreaTrabajo implements Serializable {
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo") 
    private List<Empleado> empleados= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo") 
    private List<Alerta> alertas= new ArrayList<>();
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column
    private boolean estado;
    
    @Column(name = "codigo", length = 10)
    private String codigo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
}
