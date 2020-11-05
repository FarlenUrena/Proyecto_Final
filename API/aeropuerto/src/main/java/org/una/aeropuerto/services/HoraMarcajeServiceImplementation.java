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
import org.una.aeropuerto.dto.HoraMarcajeDTO;
import org.una.aeropuerto.entities.HoraMarcaje;
import org.una.aeropuerto.repositories.IHoraMarcajeRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author erikg
 */
@Service
public class HoraMarcajeServiceImplementation implements IHoraMarcajeService{
    
    @Autowired
    private IHoraMarcajeRepository horaMarcajeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HoraMarcajeDTO>> findAll() {
        return ServiceConvertionHelper.findList(horaMarcajeRepository.findAll(), HoraMarcajeDTO.class);
    }
    @Override
    @Transactional(readOnly = true)
     public Optional<HoraMarcajeDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(horaMarcajeRepository.findById(id), HoraMarcajeDTO.class);
    }

    
    @Override
    @Transactional
      public HoraMarcajeDTO create(HoraMarcajeDTO alertaDto) {
        HoraMarcaje alerta = MapperUtils.EntityFromDto(alertaDto, HoraMarcaje.class);
        alerta = horaMarcajeRepository.save(alerta);
        return MapperUtils.DtoFromEntity(alerta, HoraMarcajeDTO.class);
    }

    @Override
    @Transactional
    public Optional<HoraMarcajeDTO> update(HoraMarcajeDTO horaMarcajeDTO, Long id) {
        if (horaMarcajeRepository.findById(id).isPresent()) {
            HoraMarcaje horaMarcaje = MapperUtils.EntityFromDto(horaMarcajeDTO, HoraMarcaje.class);
            horaMarcaje = horaMarcajeRepository.save(horaMarcaje);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(horaMarcaje, HoraMarcajeDTO.class));
        } else {
            return null;
        }
    }
    
}
