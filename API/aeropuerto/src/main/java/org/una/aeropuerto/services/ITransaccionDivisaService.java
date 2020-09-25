/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.TransaccionDivisa;

/**
 *
 * @author erikg
 */
public interface ITransaccionDivisaService {

    public Optional<TransaccionDivisa> findById(Long id);

    public TransaccionDivisa create(TransaccionDivisa transaccionDivisas);

    public Optional<List<TransaccionDivisa>> findAll();
    
}
