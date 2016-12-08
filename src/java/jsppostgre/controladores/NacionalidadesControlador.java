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
import jsppostgre.modelos.Nacionalidades;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;

public class NacionalidadesControlador {
    
    
    public static Nacionalidades buscarId (int id) {
        Nacionalidades nacionalidades = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from nacionalidades where id_nacionalidad=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        nacionalidades = new Nacionalidades();
                        nacionalidades.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                        nacionalidades.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return nacionalidades;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()) {
            try {
                String sql = "select * from nacionalidades where upper(nombre_nacionalidad) like '%" + 
                        nombre.toUpperCase() + "%' " + "order by id_nacionalidad " + "offset " +
                        offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("---> " + sql);
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_nacionalidad") + "</td>" +
                                "<td>" + rs.getString("nombre_nacionalidad") + "</td>" +
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
    
    public static boolean agregar (Nacionalidades nacionalidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into nacionalidades " + "(nombre_nacionalidad) " + "values (?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, nacionalidad.getNombre_nacionalidad());
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
    
    public static boolean modificar (Nacionalidades nacionalidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update nacionalidades set nombre_nacionalidad=? " +
                    "where id_nacionalidad=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, nacionalidad.getNombre_nacionalidad());
                ps.setInt(2, nacionalidad.getId_nacionalidad());
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
    
    public static boolean eliminar (Nacionalidades nacionalidad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from nacionalidades where id_nacionalidad=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, nacionalidad.getId_nacionalidad());
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
