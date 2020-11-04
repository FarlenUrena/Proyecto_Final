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
import org.una.aeropuerto.dto.TransaccionDivisaDTO;
import org.una.aeropuerto.entities.TransaccionDivisa;
import org.una.aeropuerto.entities.TransaccionDivisa;
import org.una.aeropuerto.repositories.ITransaccionDivisaRepository;
import org.una.aeropuerto.repositories.ITransaccionDivisaRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author erikg
 */
@Service
public class TransaccionDivisaServiceImplementation implements ITransaccionDivisaService{

    @Autowired
    private ITransaccionDivisaRepository transaccionDivisaRepository;

    @Override
    @Transactional
    public TransaccionDivisaDTO create(TransaccionDivisaDTO transaccionDivisaDto) {
        TransaccionDivisa transaccionDivisa = MapperUtils.EntityFromDto(transaccionDivisaDto, TransaccionDivisa.class);
        transaccionDivisa = transaccionDivisaRepository.save(transaccionDivisa);
        return MapperUtils.DtoFromEntity(transaccionDivisa, TransaccionDivisaDTO.class);
    }

    @Override
    @Transactional
    public Optional<TransaccionDivisaDTO> update(TransaccionDivisaDTO transaccionDivisaDTO, Long id) {
        if (transaccionDivisaRepository.findById(id).isPresent()) {
            TransaccionDivisa transaccionDivisa = MapperUtils.EntityFromDto(transaccionDivisaDTO, TransaccionDivisa.class);
            transaccionDivisa = transaccionDivisaRepository.save(transaccionDivisa);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(transaccionDivisa, TransaccionDivisaDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDivisaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(transaccionDivisaRepository.findById(id), TransaccionDivisaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDivisaDTO>> findAll() {
        return ServiceConvertionHelper.findList(transaccionDivisaRepository.findAll(), TransaccionDivisaDTO.class);
    }
}