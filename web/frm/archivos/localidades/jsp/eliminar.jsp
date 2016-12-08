<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.LocalidadesControlador"%>
<%@page import = "jsppostgre.modelos.Localidades"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_localidad = Integer.parseInt(request.getParameter("id_localidad"));
    //String nombre_localidad = request.getParameter("nombre_localidad");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Localidades localidad = new Localidades();
    localidad.setId_localidad(id_localidad);
    //localidad.setNombre_localidad(nombre_localidad);
    
    if (LocalidadesControlador.eliminar(localidad)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>