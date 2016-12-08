<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.EstadosCiviles"%>
<%@page import = "jsppostgre.controladores.EstadosCivilesControlador"%>

<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    EstadosCiviles estadocivil = EstadosCivilesControlador.buscarId(id_estadocivil);
    
    if (estadocivil != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        estadocivil = new EstadosCiviles();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_estadocivil", estadocivil.getId_estadocivil());
    obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());
    
    out.print(obj);
    out.flush();
%>