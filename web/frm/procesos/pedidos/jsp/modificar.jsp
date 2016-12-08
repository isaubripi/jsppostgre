<%-- 
    Document   : modificar
    Created on : 07-jul-2016, 10:45:02
    Author     : Administrator
--%>

<%@page import="jsppostgre.modelos.Personas"%>
<%@page import = "jsppostgre.controladores.PedidosControlador"%>
<%@page import = "jsppostgre.modelos.Pedidos"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    String nombre_pedido = request.getParameter("nombre_pedido");
    String fecha_pedido = request.getParameter("fecha_pedido");
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);
    pedido.setNombre_pedido(nombre_pedido);
    pedido.setFecha_pedido(fecha_pedido);
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    pedido.setPersona(persona);
    
    if (PedidosControlador.modificar(pedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
