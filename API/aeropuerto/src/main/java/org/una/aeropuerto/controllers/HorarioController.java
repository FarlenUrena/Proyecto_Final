/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

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
import org.una.aeropuerto.dto.HorarioDTO;
import org.una.aeropuerto.entities.Horario;
import org.una.aeropuerto.services.IHorarioService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@RestController
@RequestMapping("/horarios") 
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;

    @GetMapping() 
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Horario>> result = horarioService.findAll();
            if (result.isPresent()) {
                List<HorarioDTO> horariosDTO = MapperUtils.DtoListFromEntityList(result.get(), HorarioDTO.class);
                return new ResponseEntity<>(horariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Horario> horarioFound = horarioService.findById(id);
            if (horarioFound.isPresent()) {
                HorarioDTO horarioDto = MapperUtils.DtoFromEntity(horarioFound.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Horario horario) {
        try {
            Horario horarioCreated = horarioService.create(horario);
            HorarioDTO horarioDto = MapperUtils.DtoFromEntity(horarioCreated, HorarioDTO.class);
            return new ResponseEntity<>(horarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Horario horarioModified) {
        try {
            Optional<Horario> horarioUpdated = horarioService.update(horarioModified, id);
            if (horarioUpdated.isPresent()) {
                HorarioDTO horarioDto = MapperUtils.DtoFromEntity(horarioUpdated.get(), HorarioDTO.class);
                return new ResponseEntity<>(horarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
         try {
            Optional<Horario> horarioFound = horarioService.findById(id);
            if (horarioFound.isPresent()) {
                horarioService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
 	//TODO: Implementar este método
        try {
            Optional<List<Horario>> result = horarioService.findAll();
            if (result.isPresent()) {
                horarioService.deleteAll();
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}



