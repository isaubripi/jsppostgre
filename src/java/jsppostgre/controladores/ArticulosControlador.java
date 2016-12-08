/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.controladores;

/**
 *
 * @author Administrator
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsppostgre.modelos.Articulos;
import jsppostgre.modelos.Rubros;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;

public class ArticulosControlador {
    
    
    public static Articulos buscarId (int id) {
        Articulos articulos = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from articulos where id_articulo=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        articulos = new Articulos();
                        articulos.setId_articulo(rs.getInt("id_articulo"));
                        articulos.setNombre_articulo(rs.getString("nombre_articulo"));
                        articulos.setCodigo_articulo(rs.getString("codigo_articulo"));
                        articulos.setSerie_articulo(rs.getString("serie_articulo"));
                        
                        Rubros rubro = new Rubros();
                        int id_rubro = rs.getInt("id_rubro");
                        
                        rubro.setId_rubro(id_rubro);
                        //rubro.setNombre_rubro();
                        articulos.setRubro(rubro);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return articulos;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()) {
            try {
                String sql = "select * from articulos where upper(nombre_articulo) like '%" + 
                        nombre.toUpperCase() + "%' " + "order by id_articulo " + "offset " +
                        offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("---> " + sql);
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_articulo") + "</td>" +
                                "<td>" + rs.getString("nombre_articulo") + "</td>" +
                                "<td>" + rs.getString("codigo_articulo") + "</td>" +
                                "<td>" + rs.getString("serie_articulo") + "</td>" +
                                "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch(SQLException ex) {
                System.out.println("---.> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean agregar (Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into articulos " + "(nombre_articulo, codigo_articulo, serie_articulo, id_rubro) " + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, articulo.getNombre_articulo());
                ps.setString(2, articulo.getCodigo_articulo());
                ps.setString(3, articulo.getSerie_articulo());
                ps.setInt(4, articulo.getRubro().getId_rubro());
                ps.executeUpdate();
                ps.close();
                Conexion.getCon().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
                try {
                    Conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("---> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean modificar (Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update articulos set nombre_articulo=?, codigo_articulo=?, serie_articulo=?, id_rubro=?" +
                    "where id_articulo=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, articulo.getNombre_articulo());
                ps.setString(2, articulo.getCodigo_articulo());
                ps.setString(3, articulo.getSerie_articulo());
                ps.setInt(4, articulo.getRubro().getId_rubro());
                ps.setInt(5, articulo.getId_articulo());
                ps.executeUpdate();
                ps.close();
                Conexion.getCon().commit();
                System.out.println("---> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
                try {
                    Conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("---> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    public static boolean eliminar (Articulos articulo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from articulos where id_articulo=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, articulo.getId_articulo());
                ps.executeUpdate();
                ps.close();
                Conexion.getCon().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
                try {
                    Conexion.getCon().rollback();
                } catch (SQLException ex1) {
                    System.out.println("---> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
