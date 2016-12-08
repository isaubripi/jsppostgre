<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Roles"%>
<%@page import = "jsppostgre.controladores.RolesControlador"%>

<%
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Roles rol = RolesControlador.buscarId(id_rol);
    
    if (rol != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        rol = new Roles();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_rol", rol.getId_rol());
    obj.put("nombre_rol", rol.getNombre_rol());
    
    out.print(obj);
    out.flush();
%>