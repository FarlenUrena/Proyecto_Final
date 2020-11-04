/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AreaTrabajoDTO;

/**
 *
 * @author farle_000
 */
public interface IAreaTrabajoService {

    public Optional<List<AreaTrabajoDTO>> findAll();

    public Optional<AreaTrabajoDTO> findById(Long id);

    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajo);

    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajoDTO, Long id);
}