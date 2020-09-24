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
import org.una.aeropuerto.dto.TransaccionDivisaDTO;
import org.una.aeropuerto.entities.TransaccionDivisa;
import org.una.aeropuerto.services.ITransaccionDivisaService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@RestController
@RequestMapping("/transaccionesDivisas") 
public class TransaccionDivisaController {

    @Autowired
    private ITransaccionDivisaService transaccionDivisasService;
    private Object transaccionDivisaService;

    /**
     *
     * @return
     */
    @GetMapping() 
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TransaccionDivisa>> result = transaccionDivisaService.findAll();
            if (result.isPresent()) {
                List<TransaccionDivisaDTO> transaccionDivisassDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDivisaDTO.class);
                return new ResponseEntity<>(transaccionDivisassDTO, HttpStatus.OK);
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

            Optional<TransaccionDivisa> transaccionDivisasFound = transaccionDivisasService.findById(id);
            if (transaccionDivisasFound.isPresent()) {
                TransaccionDivisaDTO transaccionDivisasDto = MapperUtils.DtoFromEntity(transaccionDivisasFound.get(), TransaccionDivisaDTO.class);
                return new ResponseEntity<>(transaccionDivisasDto, HttpStatus.OK);
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
    public ResponseEntity<?> create(@RequestBody TransaccionDivisa transaccionDivisas) {
        try {
            TransaccionDivisa transaccionDivisasCreated = transaccionDivisasService.create(transaccionDivisas);
            TransaccionDivisaDTO transaccionDivisasDto = MapperUtils.DtoFromEntity(transaccionDivisasCreated, TransaccionDivisaDTO.class);
            return new ResponseEntity<>(transaccionDivisasDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransaccionDivisa transaccionDivisasModified) {
        try {
            Optional<TransaccionDivisa> transaccionDivisasUpdated = transaccionDivisasService.update(transaccionDivisasModified, id);
            if (transaccionDivisasUpdated.isPresent()) {
                TransaccionDivisaDTO transaccionDivisasDto = MapperUtils.DtoFromEntity(transaccionDivisasUpdated.get(), TransaccionDivisaDTO.class);
                return new ResponseEntity<>(transaccionDivisasDto, HttpStatus.OK);

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
            Optional<TransaccionDivisa> transaccionDivisasFound = transaccionDivisasService.findById(id);
            if (transaccionDivisasFound.isPresent()) {
                transaccionDivisasService.delete(id);
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
            Optional<List<TransaccionDivisa>> result = transaccionDivisasService.findAll();
            if (result.isPresent()) {
                transaccionDivisasService.deleteAll();
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}



