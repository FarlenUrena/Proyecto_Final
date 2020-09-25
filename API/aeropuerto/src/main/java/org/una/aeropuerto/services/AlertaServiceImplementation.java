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
import org.una.aeropuerto.entities.Alerta;
import org.una.aeropuerto.repositories.IAlertaRepository;

/**
 *
 * @author thony
 */

@Service
public class AlertaServiceImplementation implements IAlertaService{
    @Autowired
    private IAlertaRepository alertaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Alerta> findById(Long id) {
        return alertaRepository.findById(id);
    }

    @Override
    @Transactional
    public Alerta create(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    @Transactional
    public Optional<Alerta> update(Alerta alerta, Long id) {
    if (alertaRepository.findById(id).isPresent()) {
            return Optional.ofNullable(alertaRepository.save(alerta));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alertaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        alertaRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Alerta>> findAll() {
        return Optional.ofNullable(alertaRepository.findAll());
    }

   
}
