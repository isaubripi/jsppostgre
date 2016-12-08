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
import jsppostgre.modelos.Pedidos;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;

public class PedidosControlador {
    
    
    public static Pedidos buscarId (int id) {
        Pedidos pedidos = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from pedidos where id_pedido=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pedidos = new Pedidos();
                        pedidos.setId_pedido(rs.getInt("id_pedido"));
                        pedidos.setNombre_pedido(rs.getString("nombre_pedido"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return pedidos;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()) {
            try {
                String sql = "select * from pedidos where upper(nombre_pedido) like '%" + 
                        nombre.toUpperCase() + "%' " + "order by id_pedido " + "offset " +
                        offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("---> " + sql);
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_pedido") + "</td>" +
                                "<td>" + rs.getString("nombre_pedido") + "</td>" +
                                "<td>" + rs.getString("fecha_pedido") + "</td>" +
                                "<td>" + rs.getString("id_persona") + "</td>" +
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
    
    public static boolean agregar (Pedidos pedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into pedidos " + "(nombre_pedido, fecha_pedido, id_persona) " + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, pedido.getNombre_pedido());
                ps.setString(2, pedido.getFecha_pedido());
                ps.setInt(3, pedido.getPersona().getId_persona());
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
    
    public static boolean modificar (Pedidos pedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update pedidos set nombre_pedido=?, fecha_pedido=?, id_persona=? " +
                    "where id_pedido=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, pedido.getNombre_pedido());
                ps.setString(2, pedido.getFecha_pedido());
                ps.setInt(3, pedido.getPersona().getId_persona());
                ps.setInt(4, pedido.getId_pedido());
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
    
    public static boolean eliminar (Pedidos pedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from pedidos where id_pedido=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, pedido.getId_pedido());
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
