<%-- 
    Document   : cerrarSesion
    Created on : 21-Jul-2016, 09:54:08
    Author     : Administrator
--%>

<%@page import="org.json.simple.JSONObject"%>
<%
    HttpSession sesion = request.getSession();
    sesion.invalidate();
    JSONObject obj = new JSONObject();
    
    obj.put("mensaje", "Sesión de usuario cerrada.");
    
    out.print(obj);
    out.flush();
%>

   