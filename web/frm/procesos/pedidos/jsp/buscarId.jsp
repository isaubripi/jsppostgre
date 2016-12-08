<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Pedidos"%>
<%@page import = "jsppostgre.controladores.PedidosControlador"%>

<%
    int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Pedidos pedido = PedidosControlador.buscarId(id_pedido);
    
    if (pedido != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        pedido = new Pedidos();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_pedido", pedido.getId_pedido());
    obj.put("nombre_pedido", pedido.getNombre_pedido());
    
    out.print(obj);
    out.flush();
%>