/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.una.aeropuerto.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.Imagen;
import org.una.aeropuerto.repositories.IImagenRepository;

/**
 *
 * @author thony
 */

public class ImagenServiceImplementation implements IImagenService {
    @Autowired
    private IImagenRepository imagenRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Imagen> findById(Long id) {
        return imagenRepository.findById(id);
    }
}