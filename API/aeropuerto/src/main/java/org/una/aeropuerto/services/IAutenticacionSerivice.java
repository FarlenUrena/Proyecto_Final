/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;

/**
 *
 * @author farle_000
 */

public interface IAutenticacionSerivice {
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
