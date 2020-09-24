/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Alerta;

/**
 *
 * @author thony
 */

public interface IAlertaRepository extends JpaRepository<Alerta, Long>{
    public Optional<List<Alerta>> findByEstado(@Param("estado")boolean estado);
    public Optional<List<Alerta>> findByFechaRegistroBetween(@Param("fecha_registro")Date start,@Param("fecha_registro")Date end);
    public Optional<Alerta> findByCodigo(@Param("codigo")String codigo);
}