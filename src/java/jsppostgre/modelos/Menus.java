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
public class Menus {
    
    private int id_menu;
    private String nombre_menu;
    private String codigo_menu;

    public Menus() {
    }

    public Menus(int id_menu, String nombre_menu, String codigo_menu) {
        this.id_menu = id_menu;
        this.nombre_menu = nombre_menu;
        this.codigo_menu = codigo_menu;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getNombre_menu() {
        return nombre_menu;
    }

    public void setNombre_menu(String nombre_menu) {
        this.nombre_menu = nombre_menu;
    }

    public String getCodigo_menu() {
        return codigo_menu;
    }

    public void setCodigo_menu(String codigo_menu) {
        this.codigo_menu = codigo_menu;
    }

    
    
    
    
}
