<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Menus"%>
<%@page import = "jsppostgre.controladores.MenusControlador"%>

<%
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Menus menu = MenusControlador.buscarId(id_menu);
    
    if (menu != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        menu = new Menus();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_menu", menu.getId_menu());
    obj.put("nombre_menu", menu.getNombre_menu());
    obj.put("codigo_menu", menu.getCodigo_menu());
    out.print(obj);
    out.flush();
%>