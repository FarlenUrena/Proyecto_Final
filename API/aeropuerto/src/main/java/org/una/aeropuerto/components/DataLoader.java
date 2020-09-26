/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.components;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.loaders.Roles;
import org.una.aeropuerto.services.IEmpleadoService;
import org.una.aeropuerto.services.IRolService;

/**
 *
 * @author thony
 */

@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IRolService rolService;


    @Override
    public void run(ApplicationArguments args) {

         if (rolService.countByEstado(true) <= 0) { 
            createRoles(); 
 
        } 
        if (empleadoService.findByCedula(cedula).isEmpty()) { 
            createAdmin(getRolAdmin()); 
            System.out.println("Se agrega el usuario inicial"); 
 
        } else { 
            System.out.println("Se encontro el admin"); 
 
        } 
 
    } 
 
    private void createRoles() { 
        for (Roles rol : Roles.values()) { 
            Rol nuevoRol = new Rol(); 
            nuevoRol.setCodigo(rol.getCodigo()); 
            nuevoRol.setDescripcion(rol.name()); 
            rolService.create(nuevoRol); 
        }  
    } 
 
    private Rol getRolAdmin() { 
 
        Rol crearAdmin; 
        final String codigoRol = "ADMIN"; 
        Optional<Rol> rolBuscado = rolService.findByCodigo(codigoRol); 
 
        if (rolBuscado.isPresent()) { 
            crearAdmin = rolBuscado.get(); 
        } else { 
            crearAdmin = new Rol(); 
            crearAdmin.setCodigo(codigoRol); 
            crearAdmin.setDescripcion("Rol de administrador"); 
            crearAdmin = rolService.create(crearAdmin); 
        } 
        return crearAdmin; 
    } 
 
    private void createAdmin(Rol rolAdmin) { 
        Empleado empleado = new Empleado(); 
        empleado.setNombreCompleto("Usuario Admin"); 
        empleado.setCedula(cedula); 
        empleado.setPasswordEncriptado(password); 
        empleado.setRol(rolAdmin);
        empleado = empleadoService.create(empleado); 
        
    } 
}