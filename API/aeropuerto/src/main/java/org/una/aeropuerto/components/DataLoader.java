/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.components;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.aeropuerto.loaders.Roles;
import org.una.aeropuerto.services.IEmpleadoService;
import org.una.aeropuerto.services.IRolService;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.EmpleadoDTO;
import org.una.aeropuerto.utils.MapperUtils;
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
        if (empleadoService.findByCedula(cedula) == null) {
            
            createRoles();
            RolDTO rol;
            EmpleadoDTO empleado = new EmpleadoDTO();
                RolDTO r = rolService.findByCodigo("ADMIN").get();
            rol = MapperUtils.EntityFromDto(r,RolDTO.class);
            empleado.setCedula(cedula);
            empleado.setPasswordEncriptado(password);
            empleado.setNombreCompleto("Usuario Administrador");
            empleado.setRol(rol);
            
            empleadoService.create(empleado);
            
            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }
 
    } 
 
    private void createRoles() { 
        for (Roles rol : Roles.values()) { 
            RolDTO nuevoRol = new RolDTO(); 
            nuevoRol.setCodigo(rol.getCodigo()); 
            nuevoRol.setCodigo(rol.getCodigo());

            nuevoRol.setDescripcion(rol.name());
            nuevoRol.setNombre(rol.name());
            rolService.create(nuevoRol); 
        }  
    } }