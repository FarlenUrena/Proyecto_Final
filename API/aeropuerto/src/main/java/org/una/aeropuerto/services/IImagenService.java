/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Imagen;

/**
 *
 * @author thony
 */

public interface IImagenService {
    public Optional<List<Imagen>> findAll();
    public Optional<Imagen> findById(Long id);
    public Imagen create(Imagen empleado);
    
}
