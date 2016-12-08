/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.controladores;

import jsppostgre.modelos.Articulos;
//import jsppostgre.modelos.DetallesPedidos;
import jsppostgre.modelos.Pedidos;
import jsppostgre.utiles.Conexion;
import jsppostgre.utiles.Utiles;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
/**
 *
 * @author Administrator
 */
/*public class DetallesPedidosControlador {
    
    public static DetallesPedidos buscarId(int id) {
        DetallesPedidos detallepedido = null;
        if (Conexion.conectar()){
            try {
                String sql = "select * from detallespedido dp "+
                        "left join pedidos p on p.id_pedido=dp.id_pedido "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where dp.id_detallepedido=?";
                try (PreparedStatement ps= Conexion.getCon().prepareStatement(sql)){
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        detallepedido = new DetallesPedidos();
                        detallepedido.setId_detallepedido(rs.getInt("id_detallepedido"));
                        detallepedido.setCantidad_articulopedido(rs.getInt("cantidad_articulopedido"));
                        
                        Articulos articulo = new Articulos();
                        articulo.setId_articulo(rs.getInt("id_articulo"));
                        articulo.setNombre_articulo(rs.getString("nombre_articulo"));
                        detallepedido.setArticulo(articulo);
                        
                        Pedidos pedido = new Pedidos();
                        pedido.setId_pedido(rs.getInt("id_pedido"));
                        detallepedido.setPedido(pedido);
                    }
                    ps.close();
                } 
            } catch (SQLException ex) {
                System.out.println("-->"+ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallepedido;
    }
    
    
    public static String buscarIdPedido(int id){
        String valor="";
        if (Conexion.conectar()){
            try {
                String sql = "select * from detallespedidos dp "+
                        "left join pedidos p on p.id_pedido=dp.id_pedido "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where dp.id_pedido="+id+" "+
                        "order by id_detallepedido";
                System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###.00");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO; 
                    while (rs.next()){
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        total = total.add(cantidad);
                        //System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedido") + "</td>"
                                + "<td>" + rs.getString("id_articulo") + "</td>"
                                + "<td>" + rs.getString("nombre_articulo") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea ("+rs.getString("id_detallepedido")+")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon-pencil'> "
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td colspan=6> No existen registros ... </td></tr>";
                    } else {
                        tabla += "<tr><td colspan=3> TOTAL </td><td class='centrado'>" +df.format(total)+ "</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            }catch (SQLException ex){
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor= "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from detallespedidos dp "+
                        "left join pedidos p on p.id_pedido=dp.id_pedido "+
                        "left join articulos a on a.id_articulo=dp.id_articulo "+
                        "where upper(a.nombre_articulo) like '%"+
                        nombre.toUpperCase() +
                        "%' "+
                        "order by id_detallepedido "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                System.out.println("--> "+sql);
                try (PreparedStatement ps= Conexion.getCon().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while(rs.next()){
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallepedido")+"</td>"
                                + "<td>" + rs.getString("id_pedido")+"</td>"
                                + "<td>" + rs.getString("id_articulo")+"</td>"
                                + "<td>" + rs.getString("nombre_articulo")+"</td>"
                                + "<td>" + rs.getInt("cantidad_articulopedido")+"</td>"
                                + "<tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td colspan=6> No existen registros ... </td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
                
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
        
        public static boolean agregar(DetallesPedidos detallepedido) {
            boolean valor = false;
            if (Conexion.conectar()){
                String sql = "insert into detallespedidos "
                        + "(id_pedido, id_articulo, cantidad_articulopedido) "
                        + "values (?,?,?)";
                try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, detallepedido.getPedido().getId_pedido());
                    ps.setInt(2, detallepedido.getArticulo().getId_articulo());
                    ps.setInt(3, detallepedido.getCantidad_articulopedido());
                    ps.executeUpdate();
                    ps.close();
                    Conexion.getCon().commit();
                    valor = true;
                } catch (SQLException ex) {
                    System.out.println("--> " + ex.getLocalizedMessage());
                    try {
                        Conexion.getCon().rollback();
                    } catch (SQLException ex1) {
                        System.out.println("--> " + ex1.getLocalizedMessage());
                    }
                }
            }
            Conexion.cerrar();
            return valor;
        }
        
        
        public static boolean modificar (DetallesPedidos detallepedido) {
             boolean valor = false;
            if (Conexion.conectar()){
                String sql = "update detallespedidos set "
                        + "id_pedido=?, "
                        + "id_articulo=?, "
                        + "cantidad_articulopedido=? "
                        + "where id_detallepedido=?";
                    try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                    ps.setInt(1, detallepedido.getPedido().getId_pedido());
                    ps.setInt(2, detallepedido.getArticulo().getId_articulo());
                    ps.setInt(3, detallepedido.getCantidad_articulopedido());
                    ps.setInt(4, detallepedido.getId_detallepedido());
                    ps.executeUpdate();
                    ps.close();
                    Conexion.getCon().commit();
                    System.out.println("--> Grabado");
                    valor = true;
                } catch (SQLException ex) {
                    System.out.println("--> " + ex.getLocalizedMessage());
                    try {
                        Conexion.getCon().rollback();
                    } catch (SQLException ex1) {
                        System.out.println("--> " + ex1.getLocalizedMessage());
                    }
                }
            }
            Conexion.cerrar();
            return valor;
        }
        
        public static boolean eliminar (DetallesPedido detallepedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallespedidos where id_detallepedido=?";
            try (PreparedStatement ps = Conexion.getCon().prepareStatement(sql)) {
                ps.setInt(1, detallepedido.getId_detallepedido());
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
        
        
     public static boolean eliminarArticuloPedido (Pedidos pedido) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallespedidos where id_pedido=?";
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
}*/
