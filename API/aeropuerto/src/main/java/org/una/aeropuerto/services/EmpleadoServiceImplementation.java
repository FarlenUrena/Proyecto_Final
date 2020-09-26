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
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.repositories.IEmpleadoRepository;

/**
 *
 * @author erikg
 */

@Service
public class EmpleadoServiceImplementation implements UserDetailsService, IEmpleadoService{

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private void encriptarPassword(Empleado empleado) {
        String password = empleado.getPasswordEncriptado();
        if (!password.isBlank()) {
            empleado.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleado>> findAll() {
        return Optional.ofNullable(empleadoRepository.findAll());
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
    public Empleado create(Empleado empleado) {
        encriptarPassword(empleado);
        return empleadoRepository.save(empleado);
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
    public Optional<Empleado> findByCedula(String cedula) {
    return empleadoRepository.findByCedula(cedula);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Empleado> empleadoBuscado = empleadoRepository.findByCedula(username);
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
