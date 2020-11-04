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
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.repositories.IRolRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author farle_000
 */
@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolRepository;

    @Override
    @Transactional
    public RolDTO create(RolDTO rolDto) {
        Rol rol = MapperUtils.EntityFromDto(rolDto, Rol.class);
        rol = rolRepository.save(rol);
        return MapperUtils.DtoFromEntity(rol, RolDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolDTO> update(RolDTO rolDTO, Long id) {
        if (rolRepository.findById(id).isPresent()) {
            Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
            rol = rolRepository.save(rol);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(rol, RolDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(rolRepository.findById(id), RolDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findAll() {
        return ServiceConvertionHelper.findList(rolRepository.findAll(), RolDTO.class);
    }

    @Override
    public Optional<Rol> findByCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}