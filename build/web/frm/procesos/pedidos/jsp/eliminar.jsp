<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.PedidosControlador"%>
<%@page import = "jsppostgre.modelos.Pedidos"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    //String nombre_pedido = request.getParameter("nombre_pedido");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Pedidos pedido = new Pedidos();
    pedido.setId_pedido(id_pedido);
    //pedido.setNombre_pedido(nombre_pedido);
    
    if (PedidosControlador.eliminar(pedido)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>