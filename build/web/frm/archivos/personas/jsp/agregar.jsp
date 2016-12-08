<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>
<%@page import = "jsppostgre.controladores.PersonasControlador"%>
<%@page import = "jsppostgre.modelos.Personas"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    String nombre_persona = request.getParameter("nombre_persona");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    persona.setNombre_persona(nombre_persona);
    
    if (PersonasControlador.agregar(persona)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
