<%-- 
    Document   : modificar
    Created on : 07-jul-2016, 10:45:02
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
    String mensaje = "Datos no modificados.";
    
    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);
    
    if (EstadosCivilesControlador.modificar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
