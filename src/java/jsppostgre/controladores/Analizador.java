/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

//import com.google.gson.Gson;

//import java.util.Iterator;
import java.util.List;
import java.util.Set;
import jsppostgre.modelos.Gramaticas;
import jsppostgre.modelos.Producciones;
//import org.json.simple.JSONArray;

/**
 *
 * @author user
 */
public class Analizador {
    
    
    /*public static boolean primero (Gramaticas g){
        
        System.out.println("entro en la funcion");
        List<Producciones> P;
        P = g.getProducciones();
        
        
        for(int i=0; i<=P.size()-1;i++)
        {
            Producciones p;
            p = P.get(i);
            String lado_d = p.getLado_der();
            for(int j=0; j<=lado_d.length();j++)
            {
                char c = lado_d.charAt(j);
                if(esTerminal(c))
                {
                    String s = "" + c;
                    
                    List<String> pri;
                    pri = p.getPrimero();
                    pri.add(s);
                    
                    if(lado_d.indexOf("|")>0) //si el lado derecho no contiene OR
                        break;

                }
                else if(c == '|')
                    continue;
                else
                {
                    String terminal = ""+c;
                    for(int k=0; k<P.size(); k++)
                    {
                        Producciones p2;
                        p2 = P.get(k);
                        String lado_i = p2.getLado_izq();
                        
                        //String lado_derecho = ""+c; 
                        if(lado_i == terminal)
                        {
                            lado_d = p2.getLado_der();
                            //j=0;
                        }
                      
                      
                    }
                    
                }
                //break;
            }
        }
        
        for(int h=0; h<P.size();h++)
        {
            Producciones prueba;
            prueba = P.get(h);
            System.out.println(prueba.getPrimero());
        }
        return true;
        
    }*/
    
    public static boolean esTerminal(char letra){
        String MAYUSCULAS = "ABCDEFGHYJKLMNÑOPQRSTUVWXYZ";
        for(int i=0; i<MAYUSCULAS.length();i++)
        {
            if(letra == MAYUSCULAS.charAt(i))
            {
                return false;
            }
        }
        return true;
    }
    
    public static String[][] matriz(Gramaticas g)
    {
        List<Producciones> P;
        P = g.getProducciones();
        
        String[][] m;
        int cant_simbolos=0;
        ArrayList<String> lista_simbolos = new ArrayList<String>();
        String sim="";
        ArrayList<String> lista_ordenada = new ArrayList<String>();
        
        //recorremos los lados derechos para saber cuantos simbolos tenemos
        
        for(int i=0; i<P.size();i++)
        {
            Producciones p;
            p = P.get(i);
            List<String> lado_d = p.getLado_der();
            
            for(int j=0; j<lado_d.size();j++)
            {
                String cadena = lado_d.get(j);
                for(int h=0; h<cadena.length(); h++)
                {
                    char c = cadena.charAt(h);
                    if(esTerminal(c) && c!='#' && c!='\'')
                    {
                        sim=""+c;
                        lista_simbolos.add(sim);
                        cant_simbolos = cant_simbolos + 1;
                        lista_ordenada = uniqueOrdenado(lista_simbolos);
                    }
                }
            }
            
        }
        lista_ordenada.remove("");
        lista_ordenada.add("$");
        
        m = new String[P.size()+1][lista_ordenada.size()+1];
        
         for(int i=0; i<m.length;i++)
        {
            //System.out.println("|");
            for(int j=0; j<m[i].length;j++)
            {
                m[i][j]="";
                //System.out.println("|"+ M[i][j] + "|");
                //if (j!=M[i].length-1) System.out.println("\t");
            }
            //System.out.println("|");
            //System.out.println("\n");
                
        }
        
        
        //cargar columa 0, que contendrá los lados izquierdos
        for(int k=0; k<P.size();k++)
        {
            Producciones p;
            p = P.get(k);
            String lado_i = p.getLado_izq();
            m[k+1][0] = lado_i;
        }
        
        //cargar fila 0, que contendrá los simbolos
        for(int k=0; k<lista_ordenada.size();k++)
        {
            m[0][k+1] = lista_ordenada.get(k);
        }
        
        
        //carga de acuerdo al conjunto primero
        
        for(int x=0; x<P.size(); x++)
        {
            Producciones p;
            p = P.get(x);
            List<String> c_primero = new ArrayList<String>();
            c_primero = p.getPrimero();            
            for(int y=0; y <c_primero.size(); y++)
            {
                
                for(int z=0; z<lista_ordenada.size(); z++)
                {
                    String w = c_primero.get(y);
                    if(w.equals(m[0][z+1]))
                    {

                        m[x+1][z+1]= m[x+1][z+1] + p.getLado_der()+"||";
                    }
                }
                
                
                
            }
            
        }
        
        //se calcula para siguiente
        
        //solo se procesa si el conjunto primero posee el caracter #(vacio).
        
        List<Producciones> pro_con_vacio = new ArrayList<Producciones>();
        for(int x=0; x<P.size(); x++)
        {
            Producciones p;
            p = P.get(x);
            List<String> c_primero = p.getPrimero();
            if(c_primero.contains("#"))
            {
                pro_con_vacio.add(p);
            }
        }
        
        for(int x=0; x<pro_con_vacio.size(); x++)
        {
            Producciones p;
            p = pro_con_vacio.get(x);
            int posicion = P.indexOf(p);
            //p.getLado_izq();
            System.out.println("para sgte:"+p.getLado_izq());
            //List<String> c_primero = p.getPrimero();
            List<String> c_siguiente = p.getSiguiente();
            
            for(int y=0; y<c_siguiente.size();y++)
            {
                
                for(int z=0; z<lista_ordenada.size(); z++)
                {
                    String w = c_siguiente.get(y);
                    if(w.equals(m[0][z+1]))
                    {
                        m[posicion+1][z+1]= m[posicion+1][z+1]+ "#"+" || ";
                    }
                }
                
                
                
            }
            
        }
        
        
        
        
        
        return m;
    }
        
    /**
     *
     * @param lista
     * @return
     */
    public static ArrayList<String> uniqueOrdenado(ArrayList<String> lista)
    {

        Set<String> s = new LinkedHashSet<String>(lista);
        lista.clear();
        lista.addAll(s);
        return lista;
    }
    
    
    public static void siguiente (Gramaticas g)
    {
        List<Producciones> P;
        P = g.getProducciones();
        String si = g.getSimbolo_inicial();
        int posicion_izq =0;
        String opcion="";
        for( int i=0; i<P.size(); i++)
        {
            Producciones p;
            p = P.get(i);
            
            String lado_izquierdo;
            lado_izquierdo = p.getLado_izq();
            
            List<String> c_siguiente = new ArrayList<String>();
            p.setSiguiente(c_siguiente);
            System.out.println("simbolo inicial:"+g.getSimbolo_inicial());
            
            System.out.println("simbolo inicial en G:"+si);
            if(lado_izquierdo.equals(si))
            {
                c_siguiente.add("$");
            }
            
            //obtner el lado derecho, izquierdo y primero 
            //List<String> lado_derecho = new ArrayList<String>();
            
            //List<String> c_primero = new ArrayList<String>();
            
            //lado_derecho = p.getLado_der();
            
            //c_primero = p.getPrimero();
            String elemento="";
            
            for(int j=0;j<P.size();j++)
            {
                Producciones q;
                q = P.get(j);
                
                List<String> lado_derecho = new ArrayList<String>();
            
                //List<String> c_primero = new ArrayList<String>();
                String lado_izquierdo2;
                lado_derecho = q.getLado_der();
                lado_izquierdo2 = q.getLado_izq();
                //System.out.println(lado_izquierdo2);
                //System.out.println(lado_derecho);
                
                for(int k=0;k<lado_derecho.size();k++)
                {
                    opcion = lado_derecho.get(k);
                    if(opcion.length()<2)
                        continue;
                    else
                    {
                        posicion_izq = opcion.indexOf(lado_izquierdo);
                        //char B = opcion.charAt(posicion_izq);
                        System.out.println(posicion_izq);
                        if(posicion_izq < 0)
                            continue;
                        else
                        {
                            if(posicion_izq>0 && posicion_izq<opcion.length()-1) //significa que la produccion cumple la forma 
                            {
                                char c = opcion.charAt(posicion_izq+1);
                                if(esTerminal(c) && c!='\'')
                                {
                                    elemento=""+c;
                                    c_siguiente.add(elemento);
                                    System.out.println(c_siguiente);
                                    elemento="";
                                }
                                else
                                {
                                    String beta = ""+c;
                                    for(int l=0;l<P.size();l++)
                                    {
                                        Producciones m;
                                        m = P.get(l);
                                        String no_terminal = m.getLado_izq();
                                        if(no_terminal.equals(beta))
                                        {
                                            c_siguiente.addAll(m.getPrimero());
                                            if(c_siguiente.contains("#"))
                                            {
                                                c_siguiente.remove("#");
                                                c_siguiente.add(beta);
                                            }
                                            System.out.println("siguiente: "+ c_siguiente);
                                        }
                                    }
                                }
                            }
                            else if(posicion_izq==opcion.length()-1) //si tiene la forma A -> aB 
                            {
                                char B = opcion.charAt(posicion_izq);
                                c_siguiente.add(lado_izquierdo2);
                                System.out.println(c_siguiente);
                            }
                        }
                    }
                }
                
                
            }
        
        
        
        
        }
        
        //cargar los conjuntos siguientes con sus correspondientes elementos
        for(int i=0;i<P.size();i++)
        {
            Producciones w;
            w=P.get(i);
            String li=w.getLado_izq();
            List<String> ld = new ArrayList<String>();
            ld = w.getLado_der();
            List<String> cs = new ArrayList<String>();
            cs = w.getSiguiente();
            for(int j=0;j<cs.size();j++)
            {
                String op = cs.get(j);
                char y = op.charAt(0);
                if(!li.equals(op) && !esTerminal(y))
                {
                    for(int k=0;k<P.size();k++)
                    {
                        Producciones z;
                        z=P.get(k);
                        String li2=z.getLado_izq();
                        
                        //conjunto siguiente del referenciado 
                        List<String> cs2 = new ArrayList<String>();
                        cs2 = z.getSiguiente();
                        if(li2.equals(op))
                        {
                            cs.addAll(cs2);
                            cs.remove(op);
                            for(int l=0;l<cs.size();l++)
                            {
                                String xx = cs.get(l);
                                char yy = xx.charAt(0);
                                if(!esTerminal(yy))
                                    cs.remove(xx);
                            }
                        }
                        
                    }
                }
                
                        
            
                       
            }
        System.out.println("Nuevo siguiente: "+li + ">>" + cs);     
        }
        
    }
    
    public static void main(String []args){
        
        Producciones P1 = new Producciones();
        P1.setLado_izq("E");
        List <String> lista1 = new ArrayList<String>();
        lista1.add("TH");
        P1.setLado_der(lista1);
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("(");
        l1.add("i");
        P1.setPrimero(l1);
        //ArrayList<String> s1 = new ArrayList<>();
        //s1.add("$");
        //s1.add("c");
        //P1.setSiguiente(s1);

          
        Producciones P2 = new Producciones();
        P2.setLado_izq("H");
        List <String> lista2 = new ArrayList<String>();
        lista2.add("+TH");
        lista2.add("#");
        P2.setLado_der(lista2);
        //P2.setLado_der("b");
        ArrayList<String> l2 = new ArrayList<>();
        l2.add("+");
        l2.add("#");
        P2.setPrimero(l2);
        //ArrayList<String> s2 = new ArrayList<>();
        //s2.add("$");
        //l1.add("#");
        //P2.setSiguiente(s2);
        
        Producciones P3 = new Producciones();
        P3.setLado_izq("T");
        List <String> lista3 = new ArrayList<String>();
        lista3.add("FS");
        P3.setLado_der(lista3);
        //P3.setLado_der("c");
        ArrayList<String> l3 = new ArrayList<>();
        l3.add("(");
        l3.add("i");
        P3.setPrimero(l3);
        //ArrayList<String> s3 = new ArrayList<>();
        //s3.add("$");
        //l1.add("#");
        //P3.setSiguiente(s3);
        
        Producciones P4 = new Producciones();
        P4.setLado_izq("S");
        List <String> lista4 = new ArrayList<String>();
        lista4.add("*FS");
        lista4.add("#");
        P4.setLado_der(lista4);
        //P3.setLado_der("c");
        ArrayList<String> l4 = new ArrayList<>();
        l4.add("*");
        l4.add("#");
        P4.setPrimero(l4);
        //ArrayList<String> s3 = new ArrayList<>();
        //s3.add("$");
        //l1.add("#");
        //P3.setSiguiente(s3);
        
        Producciones P5 = new Producciones();
        P5.setLado_izq("F");
        List <String> lista5 = new ArrayList<String>();
        lista5.add("(E)");
        lista5.add("i");
        P5.setLado_der(lista5);
        //P3.setLado_der("c");
        ArrayList<String> l5 = new ArrayList<>();
        l5.add("(");
        l5.add("i");
        P5.setPrimero(l5);
        //ArrayList<String> s3 = new ArrayList<>();
        //s3.add("$");
        //l1.add("#");
        //P3.setSiguiente(s3);
        
        Producciones P6 = new Producciones();
        P6.setLado_izq("P");
        List <String> lista6 = new ArrayList<String>();
        lista6.add("iEtPN");
        lista6.add("a");
        P6.setLado_der(lista6);
        //P3.setLado_der("c");
        ArrayList<String> l6 = new ArrayList<>();
        l6.add("i");
        l6.add("a");
        P6.setPrimero(l6);
        //ArrayList<String> s3 = new ArrayList<>();
        //s3.add("$");
        //l1.add("#");
        //P3.setSiguiente(s3);
        
        Producciones P7 = new Producciones();
        P7.setLado_izq("N");
        List <String> lista7 = new ArrayList<String>();
        lista7.add("eP");
        lista7.add("#");
        P7.setLado_der(lista7);
        //P3.setLado_der("c");
        ArrayList<String> l7 = new ArrayList<>();
        l7.add("e");
        l7.add("#");
        P7.setPrimero(l7);
        //ArrayList<String> s3 = new ArrayList<>();
        //s3.add("$");
        //l1.add("#");
        //P3.setSiguiente(s3);
        
        Producciones P8 = new Producciones();
        P8.setLado_izq("E");
        List <String> lista8 = new ArrayList<String>();
        lista8.add("b");
        //lista8.add("i");
        P8.setLado_der(lista8);
        //P3.setLado_der("c");
        ArrayList<String> l8 = new ArrayList<>();
        l8.add("b");
        //l8.add("i");
        P8.setPrimero(l8);
        //ArrayList<String> s3 = new ArrayList<>();
        //s3.add("$");
        //l1.add("#");
        //P3.setSiguiente(s3);
        
        
        
        
        ArrayList<Producciones> lista = new ArrayList<Producciones>();
        
        //lista.add(P1);
        //lista.add(P2);
        //lista.add(P3);
        //lista.add(P4);
        //lista.add(P5);
        lista.add(P6);
        lista.add(P7);
        lista.add(P8);
        
        Gramaticas g = new Gramaticas();
        g.setProducciones(lista);
        siguiente(g);
        
        String[][] M;
        
        M = matriz(g);
        
        for(int i=0; i<M.length;i++)
        {
            //System.out.println("|");
            for(int j=0; j<M[i].length;j++)
            {
                System.out.println("|"+ M[i][j] + "|");
                //if (j!=M[i].length-1) System.out.println("\t");
            }
            //System.out.println("|");
            System.out.println("\n");
                
        }
        //Gson gson = new Gson();
        
        //String json = gson.toJson(M);
        //System.out.println("Matriz = " + json);
        
        
    }
    
}
