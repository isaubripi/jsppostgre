<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Localidades"%>
<%@page import = "jsppostgre.controladores.LocalidadesControlador"%>

<%
    int id_localidad = Integer.parseInt(request.getParameter("id_localidad"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Localidades localidad = LocalidadesControlador.buscarId(id_localidad);
    
    if (localidad != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        localidad = new Localidades();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_localidad", localidad.getId_localidad());
    obj.put("nombre_localidad", localidad.getNombre_localidad());
    
    out.print(obj);
    out.flush();
%>