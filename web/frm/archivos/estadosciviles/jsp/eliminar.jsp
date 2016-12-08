<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.EstadosCivilesControlador"%>
<%@page import = "jsppostgre.modelos.EstadosCiviles"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    //String nombre_estadocivil = request.getParameter("nombre_estadocivil");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    //estadocivil.setNombre_estadocivil(nombre_estadocivil);
    
    if (EstadosCivilesControlador.eliminar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>