/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ImagenDTO;

/**
 *
 * @author thony
 */

public interface IImagenService {
    public Optional<List<ImagenDTO>> findAll();

    public Optional<ImagenDTO> findById(Long id);

    public ImagenDTO create(ImagenDTO imagen);

    public Optional<ImagenDTO> update(ImagenDTO imagenDTO, Long id);
}