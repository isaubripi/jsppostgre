/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.modelos;

/**
 *
 * @author Administrator
 */
public class Localidades {
    private int id_localidad;
    private String nombre_localidad;

    public Localidades() {
    }

    public Localidades(int id_localidad, String nombre_localidad) {
        this.id_localidad = id_localidad;
        this.nombre_localidad = nombre_localidad;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getNombre_localidad() {
        return nombre_localidad;
    }

    public void setNombre_localidad(String nombre_localidad) {
        this.nombre_localidad = nombre_localidad;
    }
    
    
}
