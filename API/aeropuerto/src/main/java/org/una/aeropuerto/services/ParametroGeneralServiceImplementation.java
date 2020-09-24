/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.repositories.IParametroGeneralRepository;

/**
 *
 * @author erikg
 */
@Service
public class ParametroGeneralServiceImplementation implements org.una.tramites.services.IParametroGeneralService {
    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroGeneral> findById(Long Id) {
    return parametroGeneralRepository.findById(Id);
    }
    

    @Override
    public Optional<List<ParametroGeneral>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ParametroGeneral create(ParametroGeneral parametroGeneral) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
