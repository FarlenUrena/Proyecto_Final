/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author farle_000
 */
public interface IRolService {
    public Optional<List<RolDTO>> findAll();

    public Optional<RolDTO> findById(Long id);

    public RolDTO create(RolDTO rol);

    public Optional<RolDTO> update(RolDTO rolDTO, Long id);
    
    public Optional<RolDTO> findByCodigo(String codigo);
}