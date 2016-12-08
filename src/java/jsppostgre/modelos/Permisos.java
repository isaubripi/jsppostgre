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
public class Permisos {
    
    private int id_permiso;
    private String nombre_permiso;

    public Permisos() {
    }

    public Permisos(int id_permiso, String nombre_permiso) {
        this.id_permiso = id_permiso;
        this.nombre_permiso = nombre_permiso;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getNombre_permiso() {
        return nombre_permiso;
    }

    public void setNombre_permiso(String nombre_permiso) {
        this.nombre_permiso = nombre_permiso;
    }
    
    
    
}
