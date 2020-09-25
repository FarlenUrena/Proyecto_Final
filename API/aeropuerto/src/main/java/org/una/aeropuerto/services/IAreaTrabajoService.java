/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.AreaTrabajo;

/**
 *
 * @author farle_000
 */
    public interface IAreaTrabajoService {

    public Optional<List<AreaTrabajo>> findAll();

    public Optional<AreaTrabajo> findById(Long id);
    
    public Optional<List<AreaTrabajo>> findByEstado(boolean estado);

    public AreaTrabajo create(AreaTrabajo areaTrabajo);

    public Optional<AreaTrabajo> update(AreaTrabajo areaTrabajo, Long id);

    public void delete(Long id);

    public void deleteAll();


}
