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
import org.una.aeropuerto.dto.ParametroGeneralDTO;
import org.una.aeropuerto.entities.ParametroGeneral;
import org.una.aeropuerto.repositories.IParametroGeneralRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author erikg
 */

@Service
public class ParametroGeneralServiceImplementation implements IParametroGeneralService {
    
@Autowired
    private IParametroGeneralRepository parametroGeneralRepository;

    @Override
    @Transactional
    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneralDto) {
        ParametroGeneral parametroGeneral = MapperUtils.EntityFromDto(parametroGeneralDto, ParametroGeneral.class);
        parametroGeneral = parametroGeneralRepository.save(parametroGeneral);
        return MapperUtils.DtoFromEntity(parametroGeneral, ParametroGeneralDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneralDTO, Long id) {
        if (parametroGeneralRepository.findById(id).isPresent()) {
            ParametroGeneral parametroGeneral = MapperUtils.EntityFromDto(parametroGeneralDTO, ParametroGeneral.class);
            parametroGeneral = parametroGeneralRepository.save(parametroGeneral);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(parametroGeneral, ParametroGeneralDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametroGeneralDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(parametroGeneralRepository.findById(id), ParametroGeneralDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findAll() {
        return ServiceConvertionHelper.findList(parametroGeneralRepository.findAll(), ParametroGeneralDTO.class);
    }
}