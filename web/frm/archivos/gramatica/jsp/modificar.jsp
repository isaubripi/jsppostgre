<%-- 
    Document   : modificar
    Created on : 07-jul-2016, 10:45:02
    Author     : Administrator
--%>

<%@page import="jsppostgre.modelos.Rubros"%>
<%@page import = "jsppostgre.controladores.ArticulosControlador"%>
<%@page import = "jsppostgre.modelos.Articulos"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    String nombre_articulo = request.getParameter("nombre_articulo");
    String serie_articulo = request.getParameter("serie_articulo");
    String codigo_articulo = request.getParameter("codigo_articulo");
    int id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    articulo.setNombre_articulo(nombre_articulo);
    articulo.setCodigo_articulo(codigo_articulo);
    articulo.setSerie_articulo(serie_articulo);
    Rubros rubro = new Rubros();
    rubro.setId_rubro(id_rubro);
    articulo.setRubro(rubro);
    
    if (ArticulosControlador.modificar(articulo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
