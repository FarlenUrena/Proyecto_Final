/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ParametroGeneralDTO;

/**
 *
 * @author erikg
 */
public interface IParametroGeneralService {
    public Optional<List<ParametroGeneralDTO>> findAll();

    public Optional<ParametroGeneralDTO> findById(Long id);

    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneral);

    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneralDTO, Long id);
}