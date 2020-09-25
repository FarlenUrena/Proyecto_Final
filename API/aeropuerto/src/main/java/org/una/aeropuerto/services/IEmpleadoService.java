/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Empleado;

/**
 *
 * @author erikg
 */
public interface IEmpleadoService {
    public Optional<List<Empleado>> findAll();
    public Optional<Empleado> findById(Long id);
    public Optional<List<Empleado>> findByCedulaAproximate(String cedula);
    public Optional<List<Empleado>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    public Empleado create(Empleado empleado);
    public Optional<Empleado> update(Empleado empleado, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<Empleado> login(Empleado empleado); 
    public Optional<Empleado> findByCedula(String cedula);
}
