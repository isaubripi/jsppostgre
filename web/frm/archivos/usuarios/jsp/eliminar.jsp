<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.UsuariosControlador"%>
<%@page import = "jsppostgre.modelos.Usuarios"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    //String nombre_usuario = request.getParameter("nombre_usuario");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    //usuario.setNombre_usuario(nombre_usuario);
    
    if (UsuariosControlador.eliminar(usuario)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>