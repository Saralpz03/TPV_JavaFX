/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpv;

/**
 *
 * @author FPMananaA
 */
public class Dependiente {
    private String nombre;
    private int id_dependiente;

    public Dependiente(String nombre, int id_dependiente) {
        this.nombre = nombre;
        this.id_dependiente = id_dependiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_dependiente() {
        return id_dependiente;
    }

    public void setId_dependiente(int id_dependiente) {
        this.id_dependiente = id_dependiente;
    }
    
}
