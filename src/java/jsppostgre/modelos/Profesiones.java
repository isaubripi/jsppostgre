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
public class Profesiones {
    
    private int id_profesion;
    private String nombre_profesion;

    public Profesiones() {
    }

    public Profesiones(int id_profesion, String nombre_profesion) {
        this.id_profesion = id_profesion;
        this.nombre_profesion = nombre_profesion;
    }

    public int getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
    }

    public String getNombre_profesion() {
        return nombre_profesion;
    }

    public void setNombre_profesion(String nombre_profesion) {
        this.nombre_profesion = nombre_profesion;
    }
    
    
    
}
