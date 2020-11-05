/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.HoraMarcajeDTO;

/**
 *
 * @author erikg
 */
public interface IHoraMarcajeService {
    
    public Optional<List<HoraMarcajeDTO>> findAll();

    public Optional<HoraMarcajeDTO> findById(Long id);
    
    public HoraMarcajeDTO create(HoraMarcajeDTO horaMarcaje);

    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcaje, Long id);
}
