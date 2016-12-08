<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Profesiones"%>
<%@page import = "jsppostgre.controladores.ProfesionesControlador"%>

<%
    int id_profesion = Integer.parseInt(request.getParameter("id_profesion"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Profesiones profesion = ProfesionesControlador.buscarId(id_profesion);
    
    if (profesion != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        profesion = new Profesiones();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_profesion", profesion.getId_profesion());
    obj.put("nombre_profesion", profesion.getNombre_profesion());
    
    out.print(obj);
    out.flush();
%>