<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>
<%@page import = "jsppostgre.controladores.ProfesionesControlador"%>
<%@page import = "jsppostgre.modelos.Profesiones"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_profesion = Integer.parseInt(request.getParameter("id_profesion"));
    String nombre_profesion = request.getParameter("nombre_profesion");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Profesiones profesion = new Profesiones();
    profesion.setId_profesion(id_profesion);
    profesion.setNombre_profesion(nombre_profesion);
    
    if (ProfesionesControlador.agregar(profesion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
