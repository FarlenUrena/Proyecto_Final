/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Rol;
import org.una.aeropuerto.repositories.IRolRepository;

/**
 *
 * @author farle_000
 */
@Service
public class RolServiceImplementation implements IRolService {

    @Autowired
    private IRolRepository rolRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Rol>> findAll() {
        return Optional.ofNullable(rolRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findById(Long id) {
             return rolRepository.findById(id);}

    @Transactional
    @Override
    public Rol create(Rol rol) {
            return rolRepository.save(rol);
    }

    @Transactional
    @Override
    public Optional<Rol> update(Rol rol, Long id) {
            if (rolRepository.findById(id).isPresent()) {
            return Optional.ofNullable(rolRepository.save(rol));
        } else {
            return null;
        }
}

    @Override
    public void delete(Long id) {
    rolRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
    rolRepository.deleteAll();
            }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findByCodigo(String codigo) {
    return Optional.ofNullable(rolRepository.findByCodigo(codigo));
    }
    
}
