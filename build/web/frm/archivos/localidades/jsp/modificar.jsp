<%-- 
    Document   : modificar
    Created on : 07-jul-2016, 10:45:02
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
    String mensaje = "Datos no modificados.";
    
    Localidades localidad = new Localidades();
    localidad.setId_localidad(id_localidad);
    localidad.setNombre_localidad(nombre_localidad);
    
    if (LocalidadesControlador.modificar(localidad)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
