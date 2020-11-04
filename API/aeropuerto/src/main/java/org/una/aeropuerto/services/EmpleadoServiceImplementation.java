/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.EmpleadoDTO;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.repositories.IEmpleadoRepository;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author erikg
 */

@Service
public class EmpleadoServiceImplementation implements IEmpleadoService,UserDetailsService{
    @Autowired
    private IEmpleadoRepository empleadoRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private EmpleadoDTO encriptarPassword(EmpleadoDTO empleado) {
       String password = empleado.getPasswordEncriptado();
        if (!password.isBlank()) {
            empleado.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
        return empleado;
    }

    @Override
    @Transactional(readOnly = true)
    
    public Optional<List<EmpleadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(empleadoRepository.findAll(), EmpleadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findByCedulaAproximate(String cedula) {
        return Optional.ofNullable(empleadoRepository.findByCedulaContaining(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return Optional.ofNullable(empleadoRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto));
    }

    @Override
    @Transactional
    public EmpleadoDTO create(EmpleadoDTO empleado) {
        empleado = encriptarPassword(empleado);
        Empleado user = MapperUtils.EntityFromDto(empleado, Empleado.class);
        user = empleadoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, EmpleadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<Empleado> update(Empleado empleado, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(empleadoRepository.save(empleado));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        empleadoRepository.deleteAll();
    }
   
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoDTO> findByCedula(String cedula) {
    return ServiceConvertionHelper.oneToOptionalDto(Optional.ofNullable(empleadoRepository.findByCedula(cedula)), EmpleadoDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Empleado> empleadoBuscado = Optional.ofNullable(empleadoRepository.findByCedula(username));
        if (empleadoBuscado.isPresent()) {
            Empleado empleado = empleadoBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(empleado.getCedula(), empleado.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }
    }
}