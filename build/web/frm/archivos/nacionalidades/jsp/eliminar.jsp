<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.NacionalidadesControlador"%>
<%@page import = "jsppostgre.modelos.Nacionalidades"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    //String nombre_nacionalidad = request.getParameter("nombre_nacionalidad");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    //nacionalidad.setNombre_nacionalidad(nombre_nacionalidad);
    
    if (NacionalidadesControlador.eliminar(nacionalidad)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>