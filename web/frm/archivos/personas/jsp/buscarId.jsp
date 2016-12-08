<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Personas"%>
<%@page import = "jsppostgre.controladores.PersonasControlador"%>

<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Personas persona = PersonasControlador.buscarId(id_persona);
    
    if (persona != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        persona = new Personas();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_persona", persona.getId_persona());
    obj.put("nombre_persona", persona.getNombre_persona());
    
    out.print(obj);
    out.flush();
%>