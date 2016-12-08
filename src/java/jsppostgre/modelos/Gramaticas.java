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
public class Gramaticas {
    private List<Producciones> producciones;
    private String simbolo_inicial;

    public Gramaticas() {
    }

    public Gramaticas(List<Producciones> producciones, String simbolo_inicial) {
        this.producciones = producciones;
        this.simbolo_inicial = simbolo_inicial;
    }

    public List<Producciones> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Producciones> producciones) {
        this.producciones = producciones;
    }

    public String getSimbolo_inicial() {
        return simbolo_inicial;
    }

    public void setSimbolo_inicial(String simbolo_inicial) {
        this.simbolo_inicial = simbolo_inicial;
    }

    
    




}
