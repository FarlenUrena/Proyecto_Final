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
@Api(tags = {"Transacciones de Divisas"})
public class TransaccionDivisaController {

    @Autowired
    private ITransaccionDivisaService transaccionDivisasService;

    /**
     *
     * @return
     */
    
    @ApiOperation(value = "Obtiene una lista de todos los empleados", response = TransaccionDivisaDTO.class, responseContainer = "List", tags = "Transacciones de Divisas")
    @GetMapping() 
    @ResponseBody
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<TransaccionDivisa>> result = transaccionDivisasService.findAll();
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

    @ApiOperation(value = "Obtiene un Transaccion de divisas con el id indicado", response = TransaccionDivisaDTO.class, tags = "Transacciones de Divisas")
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


    @ApiOperation(value = "Crea una nueva Transaccion de divisas", response = TransaccionDivisaDTO.class, tags = "Transacciones de Divisas")
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

}