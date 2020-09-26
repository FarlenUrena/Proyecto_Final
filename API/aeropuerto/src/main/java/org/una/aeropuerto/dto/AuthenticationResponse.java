/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.Rol;
/**
 *
 * @author farle_000
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationResponse {
    private String jwt;
    private EmpleadoDTO empleado;
    private List<Rol> roles;

}
