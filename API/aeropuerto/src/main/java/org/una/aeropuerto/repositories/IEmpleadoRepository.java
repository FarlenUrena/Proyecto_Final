/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Empleado;

/**
 *
 * @author erikg
 */

public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {
    public Empleado findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);
    public List<Empleado> findByCedulaContaining(String cedula);
    public List<Empleado> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    public Optional<Empleado> findByCedula(@Param("cedula")String cedula);
}

