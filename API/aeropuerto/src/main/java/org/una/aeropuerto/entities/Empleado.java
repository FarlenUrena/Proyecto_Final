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
import javax.persistence.PreUpdate;
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
@Table(name = "empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Empleado implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", length = 50)
    private String nombreCompleto;

    @Column(length = 25, unique = true)
    private String cedula;
    
    @Column
    private boolean estado;
    
    @Column(length = 100, name = "password_encriptado")
    private String passwordEncriptado;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaModificacion;
    
    @Column(name = "es_jefe")
    private boolean esJefe;
    
    @Column(name = "es_usuario")
    private boolean esUsuario;
    
    @Column(name = "rol_id")
    private Long RolId; 
    
    @Column(name = "area_trabajo_id")
    private Long AreaTrabajoId;
    
    @Column(name = "horario_id")
    private Long HorarioId;
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        esJefe=false;
        esUsuario=false;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }

}
