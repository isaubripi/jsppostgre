/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.modelos;

import java.util.List;

/**
 *
 * @author user
 */
public class Producciones {
    private List<String> lado_der;
    private String lado_izq;
    private List<String> primero;
    private List<String> siguiente;

    public Producciones() {
    }

    public Producciones(List<String> lado_der, String lado_izq, List<String> primero, List<String> siguiente) {
        this.lado_der = lado_der;
        this.lado_izq = lado_izq;
        this.primero = primero;
        this.siguiente = siguiente;
    }

    public List<String> getLado_der() {
        return lado_der;
    }

    public void setLado_der(List<String> lado_der) {
        this.lado_der = lado_der;
    }

    public String getLado_izq() {
        return lado_izq;
    }

    public void setLado_izq(String lado_izq) {
        this.lado_izq = lado_izq;
    }

    public List<String> getPrimero() {
        return primero;
    }

    public void setPrimero(List<String> primero) {
        this.primero = primero;
    }

    public List<String> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(List<String> siguiente) {
        this.siguiente = siguiente;
    }

    

   

   

    
    
    
    
    
}
