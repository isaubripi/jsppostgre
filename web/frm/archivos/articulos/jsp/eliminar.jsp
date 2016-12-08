<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.ArticulosControlador"%>
<%@page import = "jsppostgre.modelos.Articulos"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    //String nombre_articulo = request.getParameter("nombre_articulo");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    //articulo.setNombre_articulo(nombre_articulo);
    
    if (ArticulosControlador.eliminar(articulo)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>