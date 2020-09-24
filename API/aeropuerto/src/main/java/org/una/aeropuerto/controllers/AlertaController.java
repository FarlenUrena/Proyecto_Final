/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.aeropuerto.entities.Alerta;
import org.una.aeropuerto.services.IAlertaService;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.dto.AlertaDTO;

/**
 *
 * @author thony
 */

@RestController
@RequestMapping("/alertas") 
@Api(tags = {"Alertas"})

public class AlertaController {
    @Autowired
    private IAlertaService alertaService;
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una alerta a partir del id ingresado", response = AlertaDTO.class, responseContainer = "AlertaDto", tags = "Alertas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Alerta> alertaFound = alertaService.findById(id);
            if (alertaFound.isPresent()) {
                AlertaDTO alertaDto = MapperUtils.DtoFromEntity(alertaFound.get(), AlertaDTO.class);
                return new ResponseEntity<>(alertaDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{estado}")
    @ApiOperation(value = "Obtiene una lista de alertas segun el estado ingresado", response = AlertaDTO.class, responseContainer = "AlertaDto", tags = "Alertas")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {

            Optional<List<Alerta>> result = alertaService.findByEstado(estado);
            if (result.isPresent()) {
                List<AlertaDTO> alertasDTO = MapperUtils.DtoListFromEntityList(result.get(), AlertaDTO.class);
                return new ResponseEntity<>(alertasDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{fecha}")
    @ApiOperation(value = "Obtiene una lista de alertas según el rango de fechas ingresado", response = AlertaDTO.class, responseContainer = "AlertaDto", tags = "Alertas")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "fecha_registro") Date startDate,@PathVariable(value = "fecha_registro") Date endDate) {
        try {

            Optional<List<Alerta>> result = alertaService.findByFechaRegistroBetween(startDate, endDate);
            if (result.isPresent()) {
                List<AlertaDTO> alertasDTO = MapperUtils.DtoListFromEntityList(result.get(), AlertaDTO.class);
                return new ResponseEntity<>(alertasDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ApiOperation(value = "Crea una nueva alerta con la información suministrada", response = AlertaDTO.class, tags = "Alertas") 
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Alerta alerta) {
        try {
            Alerta alertaCreated = alertaService.create(alerta);
            AlertaDTO alertaDto = MapperUtils.DtoFromEntity(alertaCreated, AlertaDTO.class);
            return new ResponseEntity<>(alertaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
                 
    @ApiOperation(value = "Actualiza una alerta si su id coincide con el igresado y lo actualiza con de la información suministrada", response = AlertaDTO.class,  tags = "Alertas") 
    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Alerta alertaModified) {
        try {
            Optional<Alerta> alertaUpdated = alertaService.update(alertaModified, id);
            if (alertaUpdated.isPresent()) {
                AlertaDTO alertaDto = MapperUtils.DtoFromEntity(alertaUpdated.get(), AlertaDTO.class);
                return new ResponseEntity<>(alertaDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
    @ApiOperation(value = "Elimina una alerta si su id coincide con el ingresado", response = AlertaDTO.class,  tags = "Alertas") 
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            Optional<Alerta> alertaFound = alertaService.findById(id);
            if (alertaFound.isPresent()) {
                alertaService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}