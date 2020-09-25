/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.ImagenDTO;
import org.una.aeropuerto.entities.Imagen;
import org.una.aeropuerto.services.IImagenService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author thony
 */
@RestController
@RequestMapping("/imagenes") 
@Api(tags = {"Imagenes"})
public class ImagenController {
    
    @Autowired
    private IImagenService imagenService;
      
    @ApiOperation(value = "Obtiene una lista de todas los imagenes", response = ImagenDTO.class, responseContainer = "List", tags = "Imagenes")
    @GetMapping() 
    @ResponseBody
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<Imagen>> result = imagenService.findAll();
            if (result.isPresent()) {
                List<ImagenDTO> imagensDTO = MapperUtils.DtoListFromEntityList(result.get(), ImagenDTO.class);
                return new ResponseEntity<>(imagensDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ApiOperation(value = "Obtiene una imágen", response = ImagenDTO.class, responseContainer = "ImagenDto", tags = "Imagenes")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Imagen> imagenFound = imagenService.findById(id);
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
    
        @ApiOperation(value = "Crea un nuevo imagen", response = ImagenDTO.class, tags = "Imagenes")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Imagen imagen) {
        try {
            Imagen imagenCreated = imagenService.create(imagen);
            ImagenDTO imagenDto = MapperUtils.DtoFromEntity(imagenCreated, ImagenDTO.class);
            return new ResponseEntity<>(imagenDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}