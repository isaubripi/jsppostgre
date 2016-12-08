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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jsppostgre.modelos.Roles;
import jsppostgre.modelos.Usuarios;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;

public class UsuariosControlador {
    
    
    public static Usuarios buscarId (int id) {
        Usuarios usuarios = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios where id_usuario=?";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        usuarios = new Usuarios();
                        usuarios.setId_usuario(rs.getInt("id_usuario"));
                        usuarios.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuarios.setUsuario_usuario(rs.getString("usuario_usuario"));
                        usuarios.setClave_usuario(rs.getString("clave_usuario"));
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuarios;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if(Conexion.conectar()) {
            try {
                String sql = "select * from usuarios where upper(nombre_usuario) like '%" + 
                        nombre.toUpperCase() + "%' " + "order by id_usuario " + "offset " +
                        offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("---> " + sql);
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>" + 
                                "<td>" + rs.getString("id_usuario") + "</td>" +
                                "<td>" + rs.getString("nombre_usuario") + "</td>" +
                                "<td>" + rs.getString("usuario_usuario") + "</td>" +
                                "<td>" + rs.getString("clave_usuario") + "</td>" +
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
    
    public static boolean agregar (Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into usuarios " + "(nombre_usuario,usuario_usuario,clave_usuario) " + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, usuario.getNombre_usuario());
                ps.setString(2, usuario.getUsuario_usuario());
                ps.setString(3, Utiles.md5(Utiles.quitarGuiones(usuario.getClave_usuario())));
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
    
    public static boolean modificar (Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update usuarios set nombre_usuario=?, usuario_usuario=?, clave_usuario=?  " +
                    "where id_usuario=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setString(1, usuario.getNombre_usuario());
                ps.setString(2, usuario.getUsuario_usuario());
                ps.setString(3, usuario.getClave_usuario());
                ps.setInt(4, usuario.getId_usuario());
                
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
    
    public static boolean eliminar (Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from usuarios where id_usuario=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, usuario.getId_usuario());
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
    
    
    public static Usuarios validarAcceso(Usuarios usuario, HttpServletRequest request) {
        if(Conexion.conectar()) {
            try {
                String sql = "select * from usuarios u left join roles r on u.id_rol=r.id_rol "
                        + "where u.usuario_usuario=? and u.clave_usuario=?";
                try(PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setString(1, Utiles.quitarGuiones(usuario.getUsuario_usuario()));
                    ps.setString(2, Utiles.md5(Utiles.quitarGuiones(usuario.getClave_usuario())));
                    ResultSet rs = ps.executeQuery();
                    System.out.println("---> " + sql);
                    if(rs.next()) {
                        HttpSession sesion;
                        sesion = request.getSession(true);
                        Roles rol = new Roles();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        usuario = new Usuarios();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setUsuario_usuario(rs.getString("usuario_usuario"));
                        usuario.setClave_usuario(rs.getString("clave_usuario"));
                        usuario.setRol(rol);
                        
                        sesion.setAttribute("usuarioLogueado", usuario);
                    } else {
                        usuario = null;
                    }
                    ps.close();
                }
            } catch(SQLException ex) {
                System.out.println("---> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }
}
