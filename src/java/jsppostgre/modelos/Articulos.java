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
public class Articulos {
    
    private int id_articulo;
    private String nombre_articulo;
    private String codigo_articulo;
    private String serie_articulo;
    private Rubros rubro;

    public Articulos() {
    }

    public Articulos(int id_articulo, String nombre_articulo, String codigo_articulo, String serie_articulo, Rubros rubro) {
        this.id_articulo = id_articulo;
        this.nombre_articulo = nombre_articulo;
        this.codigo_articulo = codigo_articulo;
        this.serie_articulo = serie_articulo;
        this.rubro = rubro;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public String getSerie_articulo() {
        return serie_articulo;
    }

    public void setSerie_articulo(String serie_articulo) {
        this.serie_articulo = serie_articulo;
    }

    public Rubros getRubro() {
        return rubro;
    }

    public void setRubro(Rubros rubro) {
        this.rubro = rubro;
    }
    
    
   
    
    

   
    
    
}
