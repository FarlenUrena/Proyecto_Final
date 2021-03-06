/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.AreaTrabajo;

/**
 *
 * @author farle_000
 */
public interface IAreaTrabajoRepository extends JpaRepository<AreaTrabajo, Long> {

    public List<AreaTrabajo> findByEstado(@Param("estado") boolean estado);
}