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
 * @author erikg
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class HorarioDTO {
 
    private Long id; 
    private Date diaEntrada;
    private Date diaSalida;
    private Long horaEntrada;
    private Long horaSalida;

   
}