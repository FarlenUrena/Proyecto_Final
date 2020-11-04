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
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.repositories.IAreaTrabajoRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author farle_000
 */
@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {

    @Autowired
    private IAreaTrabajoRepository areaTrabajoRepository;

    @Override
    @Transactional
    public AreaTrabajoDTO create(AreaTrabajoDTO areaTrabajoDto) {
        AreaTrabajo areaTrabajo = MapperUtils.EntityFromDto(areaTrabajoDto, AreaTrabajo.class);
        areaTrabajo = areaTrabajoRepository.save(areaTrabajo);
        return MapperUtils.DtoFromEntity(areaTrabajo, AreaTrabajoDTO.class);
    }

    @Override
    @Transactional
    public Optional<AreaTrabajoDTO> update(AreaTrabajoDTO areaTrabajoDTO, Long id) {
        if (areaTrabajoRepository.findById(id).isPresent()) {
            AreaTrabajo areaTrabajo = MapperUtils.EntityFromDto(areaTrabajoDTO, AreaTrabajo.class);
            areaTrabajo = areaTrabajoRepository.save(areaTrabajo);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(areaTrabajo, AreaTrabajoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(areaTrabajoRepository.findById(id), AreaTrabajoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajoDTO>> findAll() {
        return ServiceConvertionHelper.findList(areaTrabajoRepository.findAll(), AreaTrabajoDTO.class);
    }
}