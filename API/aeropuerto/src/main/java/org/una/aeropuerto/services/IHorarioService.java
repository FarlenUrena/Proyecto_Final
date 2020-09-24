/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Horario;

/**
 *
 * @author erikg
 */
public interface IHorarioService {

    public Optional<Horario> findById(Long id);

    public Optional<List<Horario>> findByCedulaAproximate(String term);

    public Horario create(Horario horario);

    public Optional<Horario> update(Horario horarioModified, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Optional<List<Horario>> findAll();
    
}
