/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.EmpleadoDTO;import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.services.IAutenticacionSerivice;

/**
 *
 * @author farle_000
 */
@RestController
@RequestMapping("/autenticacion") 
@Api(tags = {"Seguridad"})
public class AutenticacionController {
    @Autowired
    private IAutenticacionSerivice autenticacionService;
    
        final String MENSAJE_VERIFICAR_CREDENCIALES = "Debe verificar y proporcionar credenciales correctas";
        final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su formulario";    
        
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = EmpleadoDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String token = autenticacionService.login(authenticationRequest).getJwt();
            if (!token.isBlank()) {
                authenticationResponse.setJwt(token);
                Optional<Empleado> user = autenticacionService.findByCedula(authenticationRequest.getCedula());
                EmpleadoDTO userDto = MapperUtils.DtoFromEntity(user.get(), EmpleadoDTO.class);
                authenticationResponse.setEmpleado(userDto);
                authenticationResponse.setRol(userDto.getRol());
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
            }
        } catch(UsernameNotFoundException | BadCredentialsException ex){
            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
}
}
