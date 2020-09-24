/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author thony
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString

public class AlertaDTO {
    private Long id; 
    private String emisor;
    private String mensaje;
    private String asunto;
    private String receptor;
    private Date fechaRegistro; 
    private Date fechaModificacion;
    private Date fechaLectura;
    private boolean estado; 
}