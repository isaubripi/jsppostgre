<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Formularios"%>
<%@page import = "jsppostgre.controladores.FormulariosControlador"%>

<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Formularios formulario = FormulariosControlador.buscarId(id_formulario);
    
    if (formulario != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        formulario = new Formularios();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_formulario", formulario.getId_formulario());
    obj.put("nombre_formulario", formulario.getNombre_formulario());
    obj.put("codigo_formulario", formulario.getCodigo_formulario());
    obj.put("id_menu", formulario.getMenu().getId_menu());
    obj.put("nombre_menu", formulario.getMenu().getNombre_menu());
    
    out.print(obj);
    out.flush();
%>