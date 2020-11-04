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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.dto.RolDTO;
import org.una.aeropuerto.dto.EmpleadoDTO;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.jwt.JwtProvider;
import org.una.aeropuerto.repositories.IEmpleadoRepository;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author farle_000
 */
@Service
public class AutenticacionServiceImplementation implements IAutenticacionSerivice, UserDetailsService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Optional<Empleado> empleado = findByCedula(authenticationRequest.getCedula());

        if (empleado.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            EmpleadoDTO empleadoDto = MapperUtils.DtoFromEntity(empleado.get(), EmpleadoDTO.class);
            authenticationResponse.setEmpleado(empleadoDto);
            System.out.println(authenticationResponse.getEmpleado().getRol());
            RolDTO rolesDto = MapperUtils.DtoFromEntity(empleado.get().getRol(), RolDTO.class);
            authenticationResponse.setRol(rolesDto);

            System.out.println(authenticationResponse.getRol().getDescripcion());
            return authenticationResponse;
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empleado> empleadoBuscado = findByCedula(username);
        if (empleadoBuscado.isPresent()) {
            Empleado empleado = empleadoBuscado.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(empleado.getRol().getCodigo()));

            for (GrantedAuthority aut : authorities) {
                System.out.println(aut + "----------------------------------------");
            }
            //List<GrantedAuthority> roles = new ArrayList<>();
            //roles.add(new SimpleGrantedAuthority("ADMIN"));
            UserDetails userDetails = new User(empleado.getCedula(), empleado.getPasswordEncriptado(), authorities);
            return userDetails;
        } else {
            System.out.println("loadUserByUserName: fail");
            return null;
        }
    }

    @Override
    public Optional<Empleado> findByCedula(String cedula) {
        return Optional.ofNullable(empleadoRepository.findByCedula(cedula));
    }
}