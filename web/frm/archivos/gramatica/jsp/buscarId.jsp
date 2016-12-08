<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Articulos"%>
<%@page import = "jsppostgre.controladores.ArticulosControlador"%>

<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Articulos articulo = ArticulosControlador.buscarId(id_articulo);
    
    if (articulo != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        articulo = new Articulos();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_articulo", articulo.getId_articulo());
    obj.put("nombre_articulo", articulo.getNombre_articulo());
    obj.put("codigo_articulo", articulo.getCodigo_articulo());
    obj.put("serie_articulo", articulo.getSerie_articulo());
    obj.put("id_rubro", articulo.getRubro().getId_rubro());
    obj.put("nombre_rubro", articulo.getRubro().getNombre_rubro());
    
    
    out.print(obj);
    out.flush();
%>