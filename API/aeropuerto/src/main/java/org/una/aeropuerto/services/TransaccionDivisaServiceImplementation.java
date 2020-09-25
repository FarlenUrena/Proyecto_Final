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
import org.una.aeropuerto.entities.TransaccionDivisa;
import org.una.aeropuerto.repositories.ITransaccionDivisaRepository;

/**
 *
 * @author erikg
 */
@Service
public class TransaccionDivisaServiceImplementation implements ITransaccionDivisaService{

     @Autowired
    private ITransaccionDivisaRepository transaccionDivisaRepository;
     
    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDivisa> findById(Long id) {
        return transaccionDivisaRepository.findById(id);
    }

    @Override
    @Transactional
    public TransaccionDivisa create(TransaccionDivisa transaccionDivisas) {
    return transaccionDivisaRepository.save(transaccionDivisas);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDivisa>> findAll() {
        return Optional.ofNullable(transaccionDivisaRepository.findAll());
    }
    
}
