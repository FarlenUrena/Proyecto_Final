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
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.repositories.IHorarioRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author erikg
 */
@Service
public class HorarioServiceImplementation implements IHorarioService {

    @Autowired
    private IHorarioRepository horarioRepository;

    @Override
    @Transactional
    public HorarioDTO create(HorarioDTO horarioDto) {
        Horario horario = MapperUtils.EntityFromDto(horarioDto, Horario.class);
        horario = horarioRepository.save(horario);
        return MapperUtils.DtoFromEntity(horario, HorarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<HorarioDTO> update(HorarioDTO horarioDTO, Long id) {
        if (horarioRepository.findById(id).isPresent()) {
            Horario horario = MapperUtils.EntityFromDto(horarioDTO, Horario.class);
            horario = horarioRepository.save(horario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(horario, HorarioDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HorarioDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(horarioRepository.findById(id), HorarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<HorarioDTO>> findAll() {
        return ServiceConvertionHelper.findList(horarioRepository.findAll(), HorarioDTO.class);
    }
}