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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.ParametroGeneralDTO;
import org.una.aeropuerto.entities.ParametroGeneral;
import org.una.aeropuerto.services.IParametroGeneralService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@RestController
@RequestMapping("/ParametroGeneral") 
public class ParametroGeneralController {

    @Autowired
    private IParametroGeneralService parametroGeneralService;

    @GetMapping() 
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametroGeneral>> result = parametroGeneralService.findAll();
            if (result.isPresent()) {
                List<ParametroGeneralDTO> parametroGeneralsDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(parametroGeneralsDTO, HttpStatus.OK);
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

            Optional<org.una.aeropuerto.entities.ParametroGeneral> parametroGeneralFound = parametroGeneralService.findById(id);
            if (parametroGeneralFound.isPresent()) {
                ParametroGeneralDTO parametroGeneralDto = MapperUtils.DtoFromEntity(parametroGeneralFound.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(parametroGeneralDto, HttpStatus.OK);
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
            Optional<List<ParametroGeneral>> result = parametroGeneralService.findAll();
            if (result.isPresent()) {
                parametroGeneralService.deleteAll();
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}