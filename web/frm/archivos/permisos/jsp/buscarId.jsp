<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Permisos"%>
<%@page import = "jsppostgre.controladores.PermisosControlador"%>

<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Permisos permiso = PermisosControlador.buscarId(id_permiso);
    
    if (permiso != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        permiso = new Permisos();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_permiso", permiso.getId_permiso());
    obj.put("nombre_permiso", permiso.getNombre_permiso());
    
    out.print(obj);
    out.flush();
%>