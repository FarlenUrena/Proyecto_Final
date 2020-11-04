/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author erik garcia
 */
@Entity
@Table(name = "transaccionDivisas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransaccionDivisa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fecha;

    @Column(name = "monedaOrigen", length = 50)
    private String monedaOrigen;

    @Column(name = "monedaDestino", length = 50)
    private String monedaDestino;

    @Column(name = "codigoCliente", length = 50)
    private String codigoCliente;
}