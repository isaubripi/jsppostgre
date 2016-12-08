<%-- 
    Document   : buscarId
    Created on : 06-jul-2016, 8:51:10
    Author     : Administrator
--%>

<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "jsppostgre.modelos.Rubros"%>
<%@page import = "jsppostgre.controladores.RubrosControlador"%>

<%
    int id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
    

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    
    Rubros rubro = RubrosControlador.buscarId(id_rubro);
    
    if (rubro != null) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        rubro = new Rubros();
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_rubro", rubro.getId_rubro());
    obj.put("nombre_rubro", rubro.getNombre_rubro());
    
    out.print(obj);
    out.flush();
%>