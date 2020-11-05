/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.HoraMarcajeDTO;
import org.una.aeropuerto.services.IHoraMarcajeService;

/**
 *
 * @author erikg
 */
@RestController
@RequestMapping("/horaMarcajes")
@Api(tags = {"HoraMarcajes"})
public class HoraMarcajeController {

    @Autowired
    private IHoraMarcajeService horaMarcajeService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody HoraMarcajeDTO horaMarcaje) {
        try {
            return new ResponseEntity(horaMarcajeService.create(horaMarcaje), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody HoraMarcajeDTO horaMarcajeDTO) {

        try {
            Optional<HoraMarcajeDTO> horaMarcajeUpdated = horaMarcajeService.update(horaMarcajeDTO, id);
            if (horaMarcajeUpdated.isPresent()) {
                return new ResponseEntity(horaMarcajeUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(horaMarcajeService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(horaMarcajeService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
