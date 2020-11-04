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
import org.una.aeropuerto.entities.Rol;
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
            //            List<PermisoOtorgadoDTO> po = new ArrayList<>();
            //            PermisoOtorgadoDTO perotor;
            //            for(Permisos permiso : Permisos.values()){
            Rol r = rolService.findByCodigo("ADMIN").get();
            //                perotor = new PermisoOtorgadoDTO();
            //                perotor.setPermiso(per);
            //                po.add(perotor);
            //            }
            rol = MapperUtils.EntityFromDto(r, RolDTO.class);
            empleado.setCedula(cedula);
            empleado.setPasswordEncriptado(password);
            empleado.setNombreCompleto("Usuario Administrador");
            empleado.setRol(rol);

            empleado = empleadoService.create(empleado);

            //            Permiso permiso;
            //            final String codigo = "Usu01"; 
            //            Optional<Permiso> permisoBuscado = permisoService.findByCodigo(codigo);
            //
            //            if (permisoBuscado.isEmpty()) { 
            //                permiso = new Permiso();
            //                permiso.setCodigo(codigo);
            //                permiso.setDescripcion("Registrar usuario nuevo");
            //                permiso = permisoService.create(permiso);
            //
            //            } else {
            //                permiso = permisoBuscado.get();
            //            }
            //            
            //            createPermisos();
            //            Usuario usuario = new Usuario();
            //            usuario.setNombreCompleto("Usuario Admin");
            //            usuario.setCedula(cedula);
            //            usuario.setPasswordEncriptado(password);
            //            usuario = usuarioService.create(usuario);
            //
            //            PermisoOtorgado permisoOtorgado = new PermisoOtorgado();
            //            permisoOtorgado.setPermiso(permiso);
            //            permisoOtorgado.setUsuario(usuario);
            //            permisoOtorgadoService.create(permisoOtorgado);
            System.out.println("Se agrega el usuario inicial");
            System.out.println(empleado + "++++++++++++++++++++++++++++++++");
        } else {
            System.out.println("Se encontro el admin");
        }

    }

    private void createRoles() {
        for (Roles rol : Roles.values()) {
            RolDTO nuevoRol = new RolDTO();
            nuevoRol.setCodigo(rol.getCodigo());

            nuevoRol.setDescripcion(rol.name());
            nuevoRol.setNombre(rol.name());
            rolService.create(nuevoRol);
        }
    }

    private Rol getRolAdmin() {
        RolDTO crearAdmin;
        final String codigoRol = "ADMIN";
        Optional<Rol> rolBuscado = rolService.findByCodigo(codigoRol);

        if (rolBuscado.isPresent()) {
            crearAdmin = MapperUtils.EntityFromDto(rolBuscado.get(), RolDTO.class);
        } else {
            crearAdmin = new RolDTO();
            crearAdmin.setCodigo(codigoRol);
            crearAdmin.setDescripcion("Rol de administrador");
            crearAdmin = rolService.create(crearAdmin);
        }
        System.out.println(crearAdmin.getId());
        return MapperUtils.DtoFromEntity(crearAdmin, Rol.class);
    }

    //    private void createAdmin(Rol rolAdmin) { 
    //        EmpleadoDTO empleado = new EmpleadoDTO(); 
    //        empleado.setNombreCompleto("Usuario Admin"); 
    //        empleado.setCedula(cedula); 
    //        empleado.setPasswordEncriptado(password); 
    //        empleado.setRol(rolAdmin);
    //        empleadoService.create(empleado); 
    //        
    //    } 
}
