<%-- 
    Document   : eliminar
    Created on : 07-jul-2016, 11:28:29
    Author     : Administrator
--%>

<%@page import = "jsppostgre.controladores.RubrosControlador"%>
<%@page import = "jsppostgre.modelos.Rubros"%>
<%@page import = "org.json.simple.JSONObject"%>
<%@page import = "java.sql.ResultSet"%>

<%
    int id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
    //String nombre_rubro = request.getParameter("nombre_rubro");
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Rubros rubro = new Rubros();
    rubro.setId_rubro(id_rubro);
    //rubro.setNombre_rubro(nombre_rubro);
    
    if (RubrosControlador.eliminar(rubro)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>