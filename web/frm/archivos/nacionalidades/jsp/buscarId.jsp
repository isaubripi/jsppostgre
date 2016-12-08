<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Nacionalidades"%>
<%@page import = "jsppostgre.controladores.NacionalidadesControlador"%>

<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Nacionalidades nacionalidad = NacionalidadesControlador.buscarId(id_nacionalidad);
    
    if (nacionalidad != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        nacionalidad = new Nacionalidades();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_nacionalidad", nacionalidad.getId_nacionalidad());
    obj.put("nombre_nacionalidad", nacionalidad.getNombre_nacionalidad());
    
    out.print(obj);
    out.flush();
%>