<%-- 
    Document   : generarMenuPrincipal
    Created on : 21/07/2016, 09:58:58 AM
    Author     : Administrator
--%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="jsppostgre.modelos.Usuarios"%>
<%@page import="jsppostgre.utiles.Conexion"%>

<%
    String mensaje = "Menu no generado";
    String menu_principal = "";
    
    HttpSession sesion = request.getSession();
    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    //System.out.println("--->" + usuarioLogueado.getRol().getId_rol());
    
    if(Conexion.conectar()){
        String sql = "select * from permisos p "
                + "left join roles r on p.id_rol=r.id_rol "
                + "left join formularios f on p.id_formulario=f.id_formulario "
                + "left join menus m on f.id_menu=m.id_menu "
                + "where p.id_rol=" + usuarioLogueado.getRol().getId_rol()+ " "
                + "order by m.id_menu";
        System.out.println("--->" + sql);
        ResultSet rs = Conexion.getSt().executeQuery(sql);
        int gid_menu = 0;
        while(rs.next()){
            if(gid_menu != rs.getInt("id_menu")){
                if(gid_menu != 0){
                    menu_principal += "</ul>";
                    menu_principal += "</li>";
                }
                
                menu_principal += "<li class='dropdown'>";
                menu_principal += rs.getString("codigo_menu");
                menu_principal += "<ul class='dropdown-menu' role='menu'>";
                
                gid_menu = rs.getInt("id_menu");
            }
            menu_principal += rs.getString("codigo_formulario");
        }
        if (gid_menu != 0){
            menu_principal += "</ul>";
            menu_principal += "</li>";        
        }
        Conexion.cerrar();
        
    }
    System.out.println("--->" + menu_principal);
    mensaje = "Menu Generado";
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("menu_principal", menu_principal);
    //System.out.println("--->" + mensaje);
    
    out.print(obj);
    out.flush();
%>
    