/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Rol;

/**
 *
 * @author farle_000
 */
public interface IRolService {
    public Optional<List<Rol>> findAll();

    public Optional<Rol> findById(Long id);
    
    public Optional<Rol> findByCodigo(String codigo);

    public Rol create(Rol rol);

    public Optional<Rol> update(Rol rol, Long id);

    public void delete(Long id);
    
    public void deleteAll();
}
