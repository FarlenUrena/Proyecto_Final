/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Alerta;

/**
 *
 * @author thony
 */

public interface IAlertaService {
    public Optional<List<Alerta>> findAll();
    public Optional<Alerta> findById(Long id);
    public Optional<List<Alerta>> findByEstado(boolean estado);
    public Optional<List<Alerta>> findByFechaRegistroBetween(Date startDate, Date endDate);
    public Alerta create(Alerta alerta);
    public Optional<Alerta> update(Alerta alerta, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<Alerta> findByCodigo(String codigo);
}