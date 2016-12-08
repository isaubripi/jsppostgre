/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.utiles;

/**
 *
 * @author Administrator
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    
    private final static String driver = "org.postgresql.Driver";
    private final static String servidor = "localhost";
    private static String baseDato = "jsppostgre";
    private final static String puerto = "5432";
    private final static String usuario = "postgres";
    private final static String clave = "admin";
    private static Connection conn;
    private static Statement st;
    
    public static boolean conectar(){
        boolean valor = false;
        try{
            Class.forName(driver);
            String url = "jdbc:postgresql://"+servidor+":"+puerto+"/"+baseDato;
            conn = DriverManager.getConnection(url, usuario,clave);
            st = conn.createStatement();
            conn.setAutoCommit(false);
            st.setFetchSize(100);
            valor = true;
            
        } catch (ClassNotFoundException | SQLException ex){
            System.out.println("error -->" + ex.getLocalizedMessage());
        }
        return valor;
    }
    
    public static boolean cerrar(){
        boolean valor=false;
        try {
            st.close();
            conn.close();
            valor = true;
            
        } catch (SQLException ex){
            System.out.println("-->" + ex.getLocalizedMessage());
        }
        
        return valor;
    }


    public static Connection getCon(){
        return conn;
        
    }

    public static Statement getSt(){
        return st;
    }
}
