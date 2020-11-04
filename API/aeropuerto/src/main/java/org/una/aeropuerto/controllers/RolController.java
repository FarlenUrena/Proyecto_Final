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
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.services.IRolService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author farle_000
 */
@RestController
@RequestMapping("/roles") 
@Api(tags = {"Roles"})
public class RolController {


    @Autowired
    private IRolService rolService;

    @ApiOperation(value = "Obtiene una lista de todos los roles", response = RolDTO.class, responseContainer = "List", tags = "Roles")
    @GetMapping() 
    @ResponseBody
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<Rol>> result = rolService.findAll();
            if (result.isPresent()) {
                List<RolDTO> rolDTO = MapperUtils.DtoListFromEntityList(result.get(), RolDTO.class);
                return new ResponseEntity<>(rolDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@ApiOperation(value = "Obtiene un rol con el id indicado", response = RolDTO.class, tags = "Roles")
    @GetMapping("/{id}") 
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Rol> rolFound = rolService.findById(id);
            if (rolFound.isPresent()) {
                RolDTO rolDto = MapperUtils.DtoFromEntity(rolFound.get(), RolDTO.class);
                return new ResponseEntity<>(rolDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Obtiene un rol con el codigo indicado", response = RolDTO.class, tags = "Rol")
    @GetMapping("/{codigo}") 
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "codigo") String codigo) {
        try {

                Optional<Rol> rolFound = rolService.findByCodigo(codigo);
            if (rolFound.isPresent()) {
                RolDTO rolDto = MapperUtils.DtoFromEntity(rolFound.get(), RolDTO.class);
                return new ResponseEntity<>(rolDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Crea un nuevo rol", response = RolDTO.class, tags = "Roles")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Rol rol) {
        try {
            Rol rolCreated = rolService.create(rol);
            RolDTO rolDto = MapperUtils.DtoFromEntity(rolCreated, RolDTO.class);
            return new ResponseEntity<>(rolDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "Actualiza un rol", response = RolDTO.class, tags = "Roles")
    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Rol rolModified) {
        try {
            Optional<Rol> rolUpdated = rolService.update(rolModified, id);
            if (rolUpdated.isPresent()) {
                RolDTO rolDto = MapperUtils.DtoFromEntity(rolUpdated.get(), RolDTO.class);
                return new ResponseEntity<>(rolDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "Elimina un rol", response = RolDTO.class, tags = "Roles")
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
         try {
            Optional<Rol> rolFound = rolService.findById(id);
            if (rolFound.isPresent()) {
                rolService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Elimina todos los roles", response = RolDTO.class, tags = "Roles")
    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
        try {
            Optional<List<Rol>> result = rolService.findAll();
            if (result.isPresent()) {
                rolService.deleteAll();
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}
