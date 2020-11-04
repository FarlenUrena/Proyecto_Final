/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.TransaccionDivisaDTO;

/**
 *
 * @author erikg
 */
public interface ITransaccionDivisaService {

    public Optional<List<TransaccionDivisaDTO>> findAll();

    public Optional<TransaccionDivisaDTO> findById(Long id);

    public TransaccionDivisaDTO create(TransaccionDivisaDTO transaccionDivisa);

    public Optional<TransaccionDivisaDTO> update(TransaccionDivisaDTO transaccionDivisaDTO, Long id);
}