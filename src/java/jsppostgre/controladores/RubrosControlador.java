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
import jsppostgre.modelos.Rubros;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;

public class RubrosControlador {
    
    
    public static Rubros buscarId (int id) {
        Rubros rubros = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from rubros where id_rubro=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        rubros = new Rubros();
                        rubros.setId_rubro(rs.getInt("id_rubro"));
                        rubros.setNombre_rubro(rs.getString("nombre_rubro"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return rubros;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()) {
            try {
                String sql = "select * from rubros where upper(nombre_rubro) like '%" + 
                        nombre.toUpperCase() + "%' " + "order by id_rubro " + "offset " +
                        offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("---> " + sql);
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_rubro") + "</td>" +
                                "<td>" + rs.getString("nombre_rubro") + "</td>" +
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
    
    public static boolean agregar (Rubros rubro) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into rubros " + "(nombre_rubro) " + "values (?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, rubro.getNombre_rubro());
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
    
    public static boolean modificar (Rubros rubro) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update rubros set nombre_rubro=? " +
                    "where id_rubro=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, rubro.getNombre_rubro());
                ps.setInt(2, rubro.getId_rubro());
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
    
    public static boolean eliminar (Rubros rubro) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from rubros where id_rubro=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, rubro.getId_rubro());
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
