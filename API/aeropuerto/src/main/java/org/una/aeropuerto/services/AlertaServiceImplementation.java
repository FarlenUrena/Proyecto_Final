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
import org.una.aeropuerto.dto.AlertaDTO;
import org.una.aeropuerto.entities.Alerta;
import org.una.aeropuerto.repositories.IAlertaRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author thony
 */
@Service
public class AlertaServiceImplementation implements IAlertaService {

    @Autowired
    private IAlertaRepository alertaRepository;

    @Override
    @Transactional
    public AlertaDTO create(AlertaDTO alertaDto) {
        Alerta alerta = MapperUtils.EntityFromDto(alertaDto, Alerta.class);
        alerta = alertaRepository.save(alerta);
        return MapperUtils.DtoFromEntity(alerta, AlertaDTO.class);
    }

    @Override
    @Transactional
    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id) {
        if (alertaRepository.findById(id).isPresent()) {
            Alerta alerta = MapperUtils.EntityFromDto(alertaDTO, Alerta.class);
            alerta = alertaRepository.save(alerta);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(alerta, AlertaDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(alertaRepository.findById(id), AlertaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findAll() {
        return ServiceConvertionHelper.findList(alertaRepository.findAll(), AlertaDTO.class);
    }
}