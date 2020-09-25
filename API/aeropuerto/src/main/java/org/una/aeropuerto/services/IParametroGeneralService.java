/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.ParametroGeneral;

/**
 *
 * @author erikg
 */
public interface IParametroGeneralService {
    public Optional<ParametroGeneral> findById(Long id);
    public ParametroGeneral create(ParametroGeneral parametroGeneral);

    public Optional<List<org.una.aeropuerto.entities.ParametroGeneral>> findAll();

    public void deleteAll();
}
