/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.controladores;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import static jsppostgre.controladores.Analizador.esTerminal;
import jsppostgre.modelos.Gramaticas;
import jsppostgre.modelos.Producciones;

/**
 *
 * @author user
 */
public class PreAnalizador {
    
    public static void factorear(Gramaticas g)
    {
        List<Producciones> P;
        P = g.getProducciones();
        int match = 0;
        String factor_comun="";
        ArrayList<Producciones> producciones_eliminar = new ArrayList<Producciones>();
        int tamaño = P.size();
        int minima_longitud=100;
        String opcion="";
        
        for( int i=0; i<tamaño; i++)
        {
            Producciones p;
            p = P.get(i);
            
            //obtengo la lista de lado derecho
            List<String> lado_derecho = p.getLado_der();
            
            //Aqui analizaremos cual es la cadena mas larga de todas las opciones menos vacio,
            //para asignar una longitud maxima a recorrer
            for(int pr=0 ; pr<lado_derecho.size();pr++)
            {
                opcion = lado_derecho.get(pr);
                if(!"#".equals(lado_derecho.get(pr)))
                {
                    if(opcion.length()<minima_longitud)
                    {
                        minima_longitud = opcion.length();
                    }
                }
            }
            System.out.println("longitud "+ minima_longitud);
            int longitud = minima_longitud;
            minima_longitud = 100;
            
            //System.out.println(lado_derecho);
            //solo si hay OR en la produccion hay posibilidad de factor comun
            if(lado_derecho.size() > 1)
            {
                String first = lado_derecho.get(0);
                for(int j=0; j<longitud ; j++)
                {
                    
                        char f = first.charAt(j);
                        //System.out.println(f);
                        for( int k=1; k<lado_derecho.size();k++)
                        {
                            String next = lado_derecho.get(k);
                            if(!"#".equals(next))
                            {    
                                char n = next.charAt(j);
                                //System.out.println(n);
                                if(f==n)
                                {
                                    match = match + 1;
                                }
                            }
                        }
                        //if(match == lado_derecho.size()-1)
                        System.out.println(match);
                        if(match > 0)
                        {
                            factor_comun = factor_comun + f;
                            //System.out.println(factor_comun);
                            match = 0;
                        }
                    
                }
                    
                if(!"".equals(factor_comun))
                {
                    
                    //Aqui se van a generar 2 producciones nuevas.
                    Producciones P1 = new Producciones();
                    P1.setLado_izq(p.getLado_izq());

                    //configurar la lista
                    ArrayList<String> new_derecho = new ArrayList<String>();
                    String nuevo_derecho = factor_comun + p.getLado_izq()+ "'"; 
                    new_derecho.add(nuevo_derecho);
                    P1.setLado_der(new_derecho);

                    //Configurar Produccion R
                    Producciones P2 = new Producciones();
                    P2.setLado_izq(p.getLado_izq()+"'");
                    ArrayList<String> new_derecho2 = new ArrayList<String>();

                    for(int l=0; l<lado_derecho.size(); l++)
                    {
                        String cadena = lado_derecho.get(l);
                        String factoreado = cadena.replaceFirst(factor_comun,"");
                        System.out.println(factoreado);
                        if ("".equals(factoreado))
                        {
                            factoreado = factoreado + "#";
                        }
                        if(cadena == factoreado)
                        {
                            new_derecho.add(cadena);
                        }
                        else
                            new_derecho2.add(factoreado);
                    }
                    P1.setLado_der(new_derecho);

                    P2.setLado_der(new_derecho2);
                    //System.out.println(new_derecho2);

                    //P.remove(i);
                    //List<Producciones> nuevas = new ArrayList<>();
                    P.add(P1);
                    P.add(P2);
                    //P.addAll(P.size(),nuevas);
                    //P.remove(i);
                    
                    System.out.println("Producciones agregadas:"+ P1.getLado_izq()+ P2.getLado_izq());
                    
                    //Integer intObj = new Integer(i);
                    System.out.println("factorcomun:"+factor_comun);
                    producciones_eliminar.add(p);
                    System.out.println(producciones_eliminar);
                    factor_comun = "";

                }
            }
        
        
        
        
        }
        //remover aca
        for(int m=0;m<producciones_eliminar.size();m++)
        {
            for(int ma=0; ma<P.size(); ma++)
            {
                if(producciones_eliminar.get(m)==P.get(ma))
                {
                    P.remove(ma);
                }
            }
            //int remover = producciones_eliminar.get(m);
            //System.out.println("se removio luego de factorear:"+P.get(remover).getLado_izq());
            //P.remove(remover);
        }
        
    }
    
    public static void suprimir_recursividad (Gramaticas g)
    {
        List<Producciones> P;
        P = g.getProducciones();
        ArrayList<Producciones> producciones_eliminar = new ArrayList<Producciones>();
        int tamaño = P.size();
        int ok=0;
        
        for( int i=0; i<tamaño; i++)
        {
            Producciones p;
            p = P.get(i);
            List<String> beta = new ArrayList<String>();
            List<String> alfa = new ArrayList<String>();
            List<String> lado_derecho = p.getLado_der();
            
            String izquierdo = p.getLado_izq();
            for(int j=0; j<lado_derecho.size();j++)
            {
                String first = lado_derecho.get(j);
                char f = first.charAt(0);
                System.out.println("primer caracter "+f);
                String f2 = ""+f;
                
                //if(izquierdo.equals(f2))
                //{
                    for(int u=0; u<first.length();u++)
                    {
                        char cadenita = first.charAt(u);
                        if(cadenita == '\'')
                        {
                            f2 = f2 + cadenita;
                        }
                        else
                            break;
                    }
                //}
                System.out.println("Lado derecho: "+f2);
                if(izquierdo.equals(f2))
                {
                    ok = 1;
                    //if(lado_derecho.size()==1)
                    //    first = "#";
                    System.out.println(ok);
                    String cadena = first;
                    String reducido = cadena.replaceFirst(f2,"");
                    alfa.add(reducido);
                    
                    
                }
                else
                {
                    
                    beta.add(first);
                }
                //first="";
            }
            System.out.println(alfa);
            System.out.println(beta);
            //se generan las dos producciones nuevas
            if(ok == 1)
            {
                Producciones P1 = new Producciones();
                P1.setLado_izq(p.getLado_izq());

                ArrayList<String> new_derecho = new ArrayList<String>();
                for(int k=0;k<beta.size();k++)
                {
                    String nuevo_derecho = beta.get(k) + p.getLado_izq()+ "''";
                    new_derecho.add(nuevo_derecho);
                }
                P1.setLado_der(new_derecho);

                //segunda produccion de la forma R -> alfaR | vacio
                Producciones P2 = new Producciones();
                P2.setLado_izq(p.getLado_izq()+"''");
                ArrayList<String> new_derecho2 = new ArrayList<String>();
                for(int k=0;k<alfa.size();k++)
                {
                    String nuevo_derecho2 = alfa.get(k) + p.getLado_izq()+ "''";
                    new_derecho2.add(nuevo_derecho2);
                }
                //agregar el caracter vacio, en este caso usamos #
                new_derecho2.add("#");
                P2.setLado_der(new_derecho2);

                producciones_eliminar.add(p);
                P.add(P1);
                P.add(P2);
                
                ok=0;
            
            }
        }
        for(int m=0;m<producciones_eliminar.size();m++)
        {
            for(int ma=0; ma<P.size(); ma++)
            {
                if(producciones_eliminar.get(m)==P.get(ma))
                {
                    P.remove(ma);
                }
            }
            //int remover = producciones_eliminar.get(m);
            //System.out.println("se removio luego de factorear:"+P.get(remover).getLado_izq());
            //P.remove(remover);
        }
    }
    
    public static Gramaticas construir_gramatica(String g)
    {
        String cadena = "";
        int inicio=0;
        int fin=0;
        String lado_izquierdo="";
        ArrayList<String> lado_derecho= new ArrayList<>();
        ArrayList<Producciones> PRO = new ArrayList<>();
        String lado_der="";
        String reducido="";
        String sim_inicial="";
        String inicial ="";
        
        for (int i=0 ; i<g.length() ; i++)
        {
            char c = g.charAt(i);
            cadena = cadena + c;
            if(i==0)
                sim_inicial=cadena;

            if (c == ';')
            {
                System.out.println(cadena);
                inicio = i - cadena.length()+1;
                fin = i-1;
                //System.out.println(inicio);
                //System.out.println(fin);

                for(int j=inicio ; j<=fin; j++)
                {
                    char iz = g.charAt(j);
                    if(iz != '>')
                    {
                        lado_izquierdo = lado_izquierdo + iz;
                        //if(inicial.equals("ok"))
                        //{
                            //sim_inicial = lado_izquierdo;
                        //} 
                    }
                    if(iz == '>')
                    {
                        for(int k=j+1; k<=fin; k++)
                        {
                            char d = g.charAt(k);
                            lado_der = lado_der + d;
                            if(d == '|' || k==fin)
                            {
                                reducido = lado_der.replace("|", "");
                                lado_derecho.add(reducido);
                                lado_der = "";
                            }
                        }
                        break;
                    }
                }

                System.out.println(lado_izquierdo);
                System.out.println(lado_derecho);

                Producciones P = new Producciones();
                
                P.setLado_der(lado_derecho);
                P.setLado_izq(lado_izquierdo);
                cadena = "";
                lado_derecho = new ArrayList<>();
                lado_izquierdo = ""; 

                PRO.add(P);
            }
        }    //System.out.println("hola");
    
        Gramaticas G = new Gramaticas();
        G.setProducciones(PRO);
        G.setSimbolo_inicial(sim_inicial);
        System.out.println("sim:"+G.getSimbolo_inicial());
        
        return G;
    
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
                        m[x+1][z+1]= m[x+1][z+1]+ p.getLado_der()+ " || ";
                    }
                }
                
                
                
            }
            
        }
        
        //se calcula para siguiente
        
        //solo se procesa si la lista posee el caracter #(vacio).
        
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
            //List<String> c_primero = p.getPrimero();
            List<String> c_siguiente = p.getSiguiente();
            int posicion = P.indexOf(p);
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
    
    
    public static void main(String []args){
        
        Producciones P1 = new Producciones();
        P1.setLado_izq("S");
        String op11 = "AaBb";
        String op21 = "BbAa";
        //String op31 = "#";
        
        ArrayList<String> lado_derecho1 = new ArrayList<>();
        lado_derecho1.add(op11);
        lado_derecho1.add(op21);
        //lado_derecho1.add(op31);
        P1.setLado_der(lado_derecho1);
        
        
        
        Producciones P2 = new Producciones();
        P2.setLado_izq("A");
        String op1="AAaa";
        String op2="AAbb";
        String op3="#";
        //String op4="0";
        
        ArrayList<String> lado_derecho2 = new ArrayList<>();
        lado_derecho2.add(op1);
        lado_derecho2.add(op2);
        lado_derecho2.add(op3);
        //lado_derecho2.add(op4);
        
                
        P2.setLado_der(lado_derecho2);
        
        Producciones P3 = new Producciones();
        P3.setLado_izq("B");
        String op13="BBbb";
        String op23="BBaa";
        String op33="#";
        //String op4="0";
        
        ArrayList<String> lado_derecho3 = new ArrayList<>();
        lado_derecho3.add(op13);
        lado_derecho3.add(op23);
        lado_derecho3.add(op33);
        //lado_derecho3.add(op4);
        P3.setLado_der(lado_derecho3);
        
        
        ArrayList<Producciones> lista = new ArrayList<Producciones>();
        lista.add(P1);
        lista.add(P2);
        lista.add(P3);
        
        
        Gramaticas g = new Gramaticas();
        g.setProducciones(lista);
        
        //factorear(g);
        suprimir_recursividad(g);
        
        List<Producciones> prueba = new ArrayList<Producciones>();
        prueba = g.getProducciones();
        //System.out.println(prueba);
        for( int i=0; i<prueba.size();i++)
        {
            System.out.println("produccion  "+i+ ":"+prueba.get(i).getLado_izq()+ "->"+prueba.get(i).getLado_der());
        }
        
    }
    
    

}



