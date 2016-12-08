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
public class Pedidos {
    private int id_pedido;
    private String nombre_pedido;
    private String fecha_pedido;
    private Personas persona;

    public Pedidos() {
    }

    public Pedidos(int id_pedido, String nombre_pedido, String fecha_pedido, Personas persona) {
        this.id_pedido = id_pedido;
        this.nombre_pedido = nombre_pedido;
        this.fecha_pedido = fecha_pedido;
        this.persona = persona;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNombre_pedido() {
        return nombre_pedido;
    }

    public void setNombre_pedido(String nombre_pedido) {
        this.nombre_pedido = nombre_pedido;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }
    
    
}
