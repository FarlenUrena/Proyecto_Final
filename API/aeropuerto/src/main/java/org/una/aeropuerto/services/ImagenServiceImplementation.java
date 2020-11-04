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
import org.una.aeropuerto.dto.ImagenDTO;
import org.una.aeropuerto.entities.Imagen;
import org.una.aeropuerto.repositories.IImagenRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author thony
 */
@Service
public class ImagenServiceImplementation implements IImagenService {
    
    @Autowired
    private IImagenRepository imagenRepository;

    @Override
    @Transactional
    public ImagenDTO create(ImagenDTO imagenDto) {
        Imagen imagen = MapperUtils.EntityFromDto(imagenDto, Imagen.class);
        imagen = imagenRepository.save(imagen);
        return MapperUtils.DtoFromEntity(imagen, ImagenDTO.class);
    }

    @Override
    @Transactional
    public Optional<ImagenDTO> update(ImagenDTO imagenDTO, Long id) {
        if (imagenRepository.findById(id).isPresent()) {
            Imagen imagen = MapperUtils.EntityFromDto(imagenDTO, Imagen.class);
            imagen = imagenRepository.save(imagen);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(imagen, ImagenDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ImagenDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(imagenRepository.findById(id), ImagenDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ImagenDTO>> findAll() {
        return ServiceConvertionHelper.findList(imagenRepository.findAll(), ImagenDTO.class);
    }
}