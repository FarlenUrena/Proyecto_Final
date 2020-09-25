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
import org.una.aeropuerto.dto.EmpleadoDTO;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.services.IEmpleadoService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author erikg
 */
@RestController
@RequestMapping("/empleados") 
@Api(tags = {"Empleados"})
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @ApiOperation(value = "Obtiene una lista de todos los empleados", response = EmpleadoDTO.class, responseContainer = "List", tags = "Empleados")
    @GetMapping() 
    @ResponseBody
    public ResponseEntity<?> findAll() {
        try {
            Optional<List<Empleado>> result = empleadoService.findAll();
            if (result.isPresent()) {
                List<EmpleadoDTO> empleadosDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(empleadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @ApiOperation(value = "Obtiene un empleado con el id indicado", response = EmpleadoDTO.class, tags = "Empleados")
    @GetMapping("/{id}") 
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Empleado> empleadoFound = empleadoService.findById(id);
            if (empleadoFound.isPresent()) {
                EmpleadoDTO empleadoDto = MapperUtils.DtoFromEntity(empleadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(empleadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = EmpleadoDTO.class, tags = "Seguridad")
    @PutMapping("/login")
    @ResponseBody 
    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
        try {
            Empleado empleado = new Empleado();
            empleado.setCedula(cedula);
            empleado.setPasswordEncriptado(password);
            Optional<Empleado> empleadoFound = empleadoService.login(empleado);
            if (empleadoFound.isPresent()) {
                EmpleadoDTO empleadoDto = MapperUtils.DtoFromEntity(empleadoFound.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(empleadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    
    @ApiOperation(value = "Obtiene un empleado con el numero de cédula indicado", response = EmpleadoDTO.class, tags = "Empleados")
    @GetMapping("/cedula/{term}") 
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Empleado>> result = empleadoService.findByCedulaAproximate(term);
            if (result.isPresent()) {
                List<EmpleadoDTO> empleadosDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(empleadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtiene una lista de todos los empleados que contengan en su nombre completo los caracteres ingresados", response = EmpleadoDTO.class, responseContainer = "List", tags = "Empleados")
    @GetMapping("/nombre/{term}") 
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Empleado>> result = empleadoService.findByNombreCompletoAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<EmpleadoDTO> empleadosDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(empleadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Crea un nuevo empleado", response = EmpleadoDTO.class, tags = "Empleados")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoCreated = empleadoService.create(empleado);
            EmpleadoDTO empleadoDto = MapperUtils.DtoFromEntity(empleadoCreated, EmpleadoDTO.class);
            return new ResponseEntity<>(empleadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Actualiza un empleado", response = EmpleadoDTO.class, tags = "Empleados")
    @PutMapping("/{id}") 
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Empleado empleadoModified) {
        try {
            Optional<Empleado> empleadoUpdated = empleadoService.update(empleadoModified, id);
            if (empleadoUpdated.isPresent()) {
                EmpleadoDTO empleadoDto = MapperUtils.DtoFromEntity(empleadoUpdated.get(), EmpleadoDTO.class);
                return new ResponseEntity<>(empleadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Elimina un empleado", response = EmpleadoDTO.class, tags = "Empleados")
    @DeleteMapping("/{id}") 
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
         try {
            Optional<Empleado> empleadoFound = empleadoService.findById(id);
            if (empleadoFound.isPresent()) {
                empleadoService.delete(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Elimina todos los empleados", response = EmpleadoDTO.class, tags = "Empleados")
    @DeleteMapping("/") 
    public ResponseEntity<?> deleteAll() {
        try {
            Optional<List<Empleado>> result = empleadoService.findAll();
            if (result.isPresent()) {
                empleadoService.deleteAll();
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
}


