<%-- 
    Document   : validarAcceso
    Created on : 21-Jul-2016, 09:35:37
    Author     : Administrator
--%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="jsppostgre.modelos.Usuarios"%>
<%@page import="jsppostgre.modelos.Roles"%>
<%@page import="jsppostgre.controladores.UsuariosControlador"%>
<%
    String usuario_usuario = request.getParameter("usuario_usuario");
    String clave_usuario = request.getParameter("clave_usuario");
    
    String acceso = "false";
    
    Usuarios usuario = new Usuarios(0, "", usuario_usuario, clave_usuario, new Roles());
    if (UsuariosControlador.validarAcceso(usuario, request) != null) {
        acceso = "true";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>