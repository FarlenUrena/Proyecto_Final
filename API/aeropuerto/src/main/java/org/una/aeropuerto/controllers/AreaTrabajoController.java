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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AreaTrabajoDTO;
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.services.IAreaTrabajoService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author farle_000
 */
@RestController
@RequestMapping("/areasTrabajos")
@Api(tags = {"Areas de Trabajos"})
public class AreaTrabajoController {
    @Autowired
    private IAreaTrabajoService areaTrabajoService;

    @ApiOperation(value = "Obtiene una lista de todas las areas de trabajo", response = AreaTrabajoDTO.class, responseContainer = "List", tags = "Areas de Trabajos")
    @GetMapping() 
    @ResponseBody
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<AreaTrabajo>> result = areaTrabajoService.findAll();
            if (result.isPresent()) {
                List<AreaTrabajoDTO> areaTrabajoDTO = MapperUtils.DtoListFromEntityList(result.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(areaTrabajoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@ApiOperation(value = "Obtiene un area de trabajo con el id indicado", response = AreaTrabajoDTO.class, tags = "Areas de Trabajos")
    @GetMapping("/{id}") 
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<AreaTrabajo> areaTrabajoFound = areaTrabajoService.findById(id);
            if (areaTrabajoFound.isPresent()) {
                AreaTrabajoDTO areaTrabajoDto = MapperUtils.DtoFromEntity(areaTrabajoFound.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(areaTrabajoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Obtiene las areas de tabajo con el estado ingresado", response = AreaTrabajoDTO.class,responseContainer = "List", tags = "Areas de Trabajos")
    @GetMapping("/{codigo}") 
   ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<AreaTrabajo>> result = areaTrabajoService.findByEstado(true);
            if (result.isPresent()) {
                List<AreaTrabajoDTO> departamentoDTO = MapperUtils.DtoListFromEntityList(result.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Crea una nueva area de trabajo", response = AreaTrabajoDTO.class, tags = "Areas de Trabajos")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody AreaTrabajo areaTrabajo) {
        try {
            AreaTrabajo areaTrabajoCreated = areaTrabajoService.create(areaTrabajo);
            AreaTrabajoDTO areaTrabajoDto = MapperUtils.DtoFromEntity(areaTrabajoCreated, AreaTrabajoDTO.class);
            return new ResponseEntity<>(areaTrabajoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Actualiza un area de trabajo segun el id ingresado", response = AreaTrabajoDTO.class, tags = "Areas de Trabajos")
    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AreaTrabajo areaTrabajoModified) {
        try {
            Optional<AreaTrabajo> areaTrabajoUpdated = areaTrabajoService.update(areaTrabajoModified, id);
            if (areaTrabajoUpdated.isPresent()) {
                AreaTrabajoDTO areaTrabajoDto = MapperUtils.DtoFromEntity(areaTrabajoUpdated.get(), AreaTrabajoDTO.class);
                return new ResponseEntity<>(areaTrabajoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "Elimina un area trabajo", response = AreaTrabajoDTO.class, tags = "Areas de Trabajos")
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
         try {
            Optional<AreaTrabajo> areaTrabajoFound = areaTrabajoService.findById(id);
            if (areaTrabajoFound.isPresent()) {
                areaTrabajoService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Elimina todas las areas de trabajo", response = AreaTrabajoDTO.class, tags = "Areas de Trabajos")
    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
        try {
            Optional<List<AreaTrabajo>> result = areaTrabajoService.findAll();
            if (result.isPresent()) {
                areaTrabajoService.deleteAll();
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 


