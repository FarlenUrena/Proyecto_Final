/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.loaders;

/**
 *
 * @author farle_000
 */
public enum Roles {
    Administrador("ADMIN"),
    Gestor("GEST"),
    Auditor("AUD"),
    Gerente("GER");

    private String codigo;

    Roles(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}