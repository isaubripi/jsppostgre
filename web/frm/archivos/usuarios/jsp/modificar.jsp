<%-- 
    Document   : modificar
    Created on : 07-jul-2016, 10:45:02
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.UsuariosControlador"%>
<%@page import = "jsppostgre.modelos.Usuarios"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String usuario_usuario = request.getParameter("usuario_usuario");
    String clave_usuario = request.getParameter("clave_usuario");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    usuario.setNombre_usuario(nombre_usuario);
    usuario.setUsuario_usuario(usuario_usuario);
    usuario.setClave_usuario(clave_usuario);
    
    if (UsuariosControlador.modificar(usuario)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
