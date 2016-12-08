<%-- 
    Document   : buscarNombre
    Created on : 07-jul-2016, 8:49:33
    Author     : Administrator
--%>

<%@page import="jsppostgre.controladores.ArticulosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>

<%
    String nombre_articulo = request.getParameter("bnombre_articulo");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda exitosa.";
    String contenido = ArticulosControlador.buscarNombre(nombre_articulo, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>
