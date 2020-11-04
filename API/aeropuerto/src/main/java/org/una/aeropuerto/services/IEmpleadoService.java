/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Empleado;
import org.una.aeropuerto.dto.EmpleadoDTO;

/**
 *
 * @author erikg
 */
public interface IEmpleadoService {
    public Optional<List<EmpleadoDTO>> findAll();
    public Optional<Empleado> findById(Long id);
    public Optional<List<Empleado>> findByCedulaAproximate(String cedula);
    public Optional<List<Empleado>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);
    public EmpleadoDTO create(EmpleadoDTO empleado);
    public Optional<Empleado> update(Empleado empleado, Long id);
    public void delete(Long id);
    public void deleteAll();
    public Optional<EmpleadoDTO> findByCedula(String cedula);
}