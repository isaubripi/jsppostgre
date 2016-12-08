<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Usuarios"%>
<%@page import = "jsppostgre.controladores.UsuariosControlador"%>

<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String usuario_usuario = request.getParameter("usuario_usuario");
    String clave_usuario = request.getParameter("clave_usuario");

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Usuarios usuario = UsuariosControlador.buscarId(id_usuario);
    
    if (usuario != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        usuario = new Usuarios();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_usuario", usuario.getId_usuario());
    obj.put("nombre_usuario", usuario.getNombre_usuario());
    obj.put("usuario_usuario", usuario.getUsuario_usuario());
    obj.put("clave_usuario", usuario.getClave_usuario());
    
    out.print(obj);
    out.flush();
%>