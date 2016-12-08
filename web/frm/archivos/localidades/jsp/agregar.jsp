<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>
<%@page import = "jsppostgre.controladores.LocalidadesControlador"%>
<%@page import = "jsppostgre.modelos.Localidades"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_localidad = Integer.parseInt(request.getParameter("id_localidad"));
    String nombre_localidad = request.getParameter("nombre_localidad");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Localidades localidad = new Localidades();
    localidad.setId_localidad(id_localidad);
    localidad.setNombre_localidad(nombre_localidad);
    
    if (LocalidadesControlador.agregar(localidad)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
