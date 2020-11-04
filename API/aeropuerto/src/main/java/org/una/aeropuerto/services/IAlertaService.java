/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AlertaDTO;

/**
 *
 * @author thony
 */

public interface IAlertaService {
    public Optional<List<AlertaDTO>> findAll();

    public Optional<AlertaDTO> findById(Long id);

    public AlertaDTO create(AlertaDTO alerta);

    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id);
}