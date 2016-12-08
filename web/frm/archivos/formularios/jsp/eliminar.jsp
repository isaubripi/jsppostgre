<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.FormulariosControlador"%>
<%@page import = "jsppostgre.modelos.Formularios"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
    //String nombre_formulario = request.getParameter("nombre_formulario");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);
    //formulario.setNombre_formulario(nombre_formulario);
    
    if (FormulariosControlador.eliminar(formulario)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>