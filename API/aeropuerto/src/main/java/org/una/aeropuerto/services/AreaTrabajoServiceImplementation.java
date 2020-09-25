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
import org.una.aeropuerto.entities.AreaTrabajo;
import org.una.aeropuerto.repositories.IAreaTrabajoRepository;

/**
 *
 * @author farle_000
 */
@Service
public class AreaTrabajoServiceImplementation implements IAreaTrabajoService {

    @Autowired
    private IAreaTrabajoRepository areaTrabajoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreaTrabajo>> findAll() {
        return Optional.ofNullable(areaTrabajoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AreaTrabajo> findById(Long id) {
        return areaTrabajoRepository.findById(id);
    }

    @Override
    @Transactional
    public AreaTrabajo create(AreaTrabajo areaTrabajo) {
        return areaTrabajoRepository.save(areaTrabajo);
    }

    @Override
    @Transactional
    public Optional<AreaTrabajo> update(AreaTrabajo areaTrabajo, Long id) {
        if (areaTrabajoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(areaTrabajoRepository.save(areaTrabajo));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        areaTrabajoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        areaTrabajoRepository.deleteAll();
    }

    @Override
    public Optional<List<AreaTrabajo>> findByEstado(boolean estado) {
     return Optional.ofNullable(areaTrabajoRepository.findByEstado(estado));}
 
}
    

