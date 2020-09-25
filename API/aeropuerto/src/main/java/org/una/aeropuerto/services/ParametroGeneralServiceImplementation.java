/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.ParametroGeneral;
import org.una.aeropuerto.repositories.IParametroGeneralRepository;

/**
 *
 * @author erikg
 */

@Service
public class ParametroGeneralServiceImplementation implements IParametroGeneralService {
    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroGeneral> findById(Long Id) {
    return parametroGeneralRepository.findById(Id);
    }
    


    @Override
    @Transactional
    public ParametroGeneral create(ParametroGeneral parametroGeneral) {
    return parametroGeneralRepository.save(parametroGeneral);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneral>> findAll() {
    return Optional.ofNullable(parametroGeneralRepository.findAll());
    }

    @Override
    @Transactional
    public void deleteAll() {
    parametroGeneralRepository.deleteAll();
    }
}
