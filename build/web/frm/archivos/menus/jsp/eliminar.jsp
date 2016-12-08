<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.MenusControlador"%>
<%@page import = "jsppostgre.modelos.Menus"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));
    //String nombre_menu = request.getParameter("nombre_menu");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Menus menu = new Menus();
    menu.setId_menu(id_menu);
    //menu.setNombre_menu(nombre_menu);
    
    if (MenusControlador.eliminar(menu)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>