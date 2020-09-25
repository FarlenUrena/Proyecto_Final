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

        if (empleadoService.findByCedula(cedula).isEmpty()) {

            Rol rol;
            final String codigo = "ADMIN"; 
            Optional<Rol> rolBuscado = rolService.findByCodigo(codigo);

            if (rolBuscado.isEmpty()) { 
                rol = new Rol();
                rol.setCodigo(codigo);
                rol.setDescripcion("Registrar usuario nuevo");
                rolService.create(rol);

            } else {
                rol = rolBuscado.get();
            }
            
            Empleado empleado = new Empleado();
            empleado.setNombreCompleto("Usuario Admin");
            empleado.setCedula(cedula);
            empleado.setPasswordEncriptado(password);
            empleado.setRol(rol);
            empleadoService.create(empleado);

            

            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }
    }
}