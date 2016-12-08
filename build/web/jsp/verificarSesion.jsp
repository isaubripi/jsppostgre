<%-- 
    Document   : verificarSesion
    Created on : 21/07/2016, 09:35:46 AM
    Author     : Administrator
--%>

<%@page import="jsppostgre.modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>

<% 
    HttpSession sesion = request.getSession();
    int id_usuario = 0;
    String usuario_usuario = "";
    String nombre_usuario = "";
    
    String activo="false";
    String mensaje= "La sesión está cerrada";
    
    Usuarios usuarioLogueado = (Usuarios)sesion.getAttribute("usuarioLogueado");
    //System.out.println("-->" + usuarioLogueado.getId_usuario());
    //System.out.println("---> " + activo);
    if (usuarioLogueado != null){
        
        id_usuario = usuarioLogueado.getId_usuario();
        usuario_usuario = usuarioLogueado.getUsuario_usuario();
        nombre_usuario = usuarioLogueado.getNombre_usuario();
        
        activo = "true";
        mensaje = "La sesión está abierta";
    }
    
    //System.out.println(activo);
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("activo", activo);
    obj.put("id_usuario", id_usuario);
    obj.put("usuario_usuario", usuario_usuario);
    obj.put("nombre_usuario", nombre_usuario);
    
    out.print(obj);
    out.flush();

%>