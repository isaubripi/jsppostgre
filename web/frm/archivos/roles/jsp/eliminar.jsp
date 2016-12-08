<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.RolesControlador"%>
<%@page import = "jsppostgre.modelos.Roles"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    //String nombre_rol = request.getParameter("nombre_rol");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    //rol.setNombre_rol(nombre_rol);
    
    if (RolesControlador.eliminar(rol)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>