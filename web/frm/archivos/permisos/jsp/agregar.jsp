<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>
<%@page import = "jsppostgre.controladores.PermisosControlador"%>
<%@page import = "jsppostgre.modelos.Permisos"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    String nombre_permiso = request.getParameter("nombre_permiso");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    permiso.setNombre_permiso(nombre_permiso);
    
    if (PermisosControlador.agregar(permiso)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
