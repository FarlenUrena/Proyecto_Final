/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.HorarioDTO;

/**
 *
 * @author erikg
 */

public interface IHorarioService {

    public Optional<List<HorarioDTO>> findAll();

    public Optional<HorarioDTO> findById(Long id);

    public HorarioDTO create(HorarioDTO horario);

    public Optional<HorarioDTO> update(HorarioDTO horarioDTO, Long id);
}