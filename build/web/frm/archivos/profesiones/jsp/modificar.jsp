<%-- 
    Document   : modificar
    Created on : 07-jul-2016, 10:45:02
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
    String mensaje = "Datos no modificados.";
    
    Profesiones profesion = new Profesiones();
    profesion.setId_profesion(id_profesion);
    profesion.setNombre_profesion(nombre_profesion);
    
    if (ProfesionesControlador.modificar(profesion)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
