<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>
<%@page import = "jsppostgre.controladores.EstadosCivilesControlador"%>
<%@page import = "jsppostgre.modelos.EstadosCiviles"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_estadocivil = request.getParameter("nombre_estadocivil");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);
    
    if (EstadosCivilesControlador.agregar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
