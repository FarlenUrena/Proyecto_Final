/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.una.aeropuerto.dto.ImagenDTO;
import org.una.aeropuerto.entities.Imagen;
import org.una.aeropuerto.services.IImagenService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author thony
 */

public class ImagenController {
    @Autowired
    private IImagenService ImagenService;
      
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una imágen", response = ImagenDTO.class, responseContainer = "ImagenDto", tags = "Imagenes")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Imagen> imagenFound = ImagenService.findById(id);
            if (imagenFound.isPresent()) {
                ImagenDTO imagenDto = MapperUtils.DtoFromEntity(imagenFound.get(), ImagenDTO.class);
                return new ResponseEntity<>(imagenDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}