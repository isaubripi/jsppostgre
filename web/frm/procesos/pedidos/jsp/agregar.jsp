<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>


<%@page import="java.sql.Date"%>
<%@page import="jsppostgre.modelos.Personas"%>
<%@page import = "jsppostgre.controladores.PedidosControlador"%>
<%@page import = "jsppostgre.modelos.Pedidos"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    //String nombre_pedido = request.getParameter("nombre_pedido");
    //String fecha_pedido = request.getParameter("fecha_pedido");
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);
    //pedido.setNombre_pedido(nombre_pedido);
    //pedido.setFecha_pedido(fecha_pedido);
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    pedido.setPersona(persona);
    
    if (PedidosControlador.agregar(pedido)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pedido", String.valueOf(pedido.getId_pedido()));
    out.print(obj);
    out.flush();
%>
