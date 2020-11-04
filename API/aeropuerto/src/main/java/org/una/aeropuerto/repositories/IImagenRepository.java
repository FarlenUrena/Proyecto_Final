/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Imagen;

/**
 *
 * @author thony
 */
public interface IImagenRepository extends JpaRepository<Imagen, Long> {
    
    // public Optional<List<Imagen>> findByObjetoAndFechaRegistroBetween(@Param("objeto")String objeto,@Param("fecha_registro") Date startDate,@Param("fecha_registro") Date endDate);
    
    // public Optional<List<Imagen>> findByFechaRegistroBetween(@Param("fecha_registro")Date startDate,@Param("fecha_registro")Date endDate);        
}