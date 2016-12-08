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
import jsppostgre.modelos.Menus;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;

public class MenusControlador {
    
    
    public static Menus buscarId (int id) {
        Menus menus = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from menus where id_menu=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        menus = new Menus();
                        menus.setId_menu(rs.getInt("id_menu"));
                        menus.setNombre_menu(rs.getString("nombre_menu"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return menus;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()) {
            try {
                String sql = "select * from menus where upper(nombre_menu) like '%" + 
                        nombre.toUpperCase() + "%' " + "order by id_menu " + "offset " +
                        offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("---> " + sql);
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_menu") + "</td>" +
                                "<td>" + rs.getString("nombre_menu") + "</td>" +
                                "<td>" + rs.getString("codigo_menu") + "</td>" +
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
    
    public static boolean agregar (Menus menu) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into menus " + "(nombre_menu, codigo_menu) " + "values (?,?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, menu.getNombre_menu());
                ps.setString(2, menu.getCodigo_menu());
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
    
    public static boolean modificar (Menus menu) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update menus set nombre_menu=?, codigo_menu=? " +
                    "where id_menu=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, menu.getNombre_menu());
                ps.setString(2, menu.getCodigo_menu());
                ps.setInt(3, menu.getId_menu());
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
    
    public static boolean eliminar (Menus menu) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from menus where id_menu=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, menu.getId_menu());
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
