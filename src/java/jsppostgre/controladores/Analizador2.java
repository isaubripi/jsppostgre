/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsppostgre.controladores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import jsppostgre.modelos.Gramaticas;
import jsppostgre.modelos.Producciones;

/**
 *
 * @author Noem y Rubn
 */
public class Analizador2 {

    public static List<Producciones> P;
    public static int rec = 0;

    public static Gramaticas primero(Gramaticas g) {

        Gramaticas retorno = new Gramaticas();
        P = g.getProducciones();
        String si = g.getSimbolo_inicial();
        System.out.println("si en primero:"+si);
        
        for (int i = 0; i <= P.size() - 1; i++) {
            Producciones p = P.get(i);
            //System.out.println("Indice = " + i);
            //p.setPrimero(primero(p));
            P.get(i).setPrimero(primero(p));
            for (String s: P.get(i).getPrimero())
                System.out.println("Primer simbolo:" + s + "  ");
        }
        
        for (int i = 0; i <= P.size() - 1; i++) {
            Producciones pro = P.get(i);
            if (pro.getPrimero().isEmpty()){
                for (int j = P.size() - 1; j >= 0; j--) {
                    Producciones prod = P.get(j);
                    if(!prod.getPrimero().isEmpty()){
                        pro.setPrimero(prod.getPrimero());
                        break;
                    }
                }
            }
        }

        retorno.setProducciones(P);
        retorno.setSimbolo_inicial(si);
        return retorno;
    }

    public static List<String> primero(Producciones pr) {
        rec++;
        List<String> retorno = new ArrayList<>(); //Conjunto primero de la produccin
        List<String> temporal = new ArrayList<>();
        for (int i = 0; i < pr.getLado_der().size(); i++) {
            String opc = pr.getLado_der().get(i);
            for (int j = 0; j < opc.length(); j++) {
                System.out.println("tamano: " + opc.length() + "indice: " + j);
                if (esTerminal(opc.charAt(j)) || opc.charAt(j) == '#') {
                    temporal.add("" + opc.charAt(j));
                    System.out.println("Esto debe agregarse a primero: " + "" + opc.charAt(j));
                    break;
                } else {
                    String simbolo = opc.substring(j, j);
                    if (j+1 < opc.length() && opc.substring(j+1, j+1) == "'"){
                        simbolo = simbolo + "'";
                        if (j+2 < opc.length() && opc.substring(j+2, j+2) == "'"){
                            simbolo = simbolo + "'";
                            j = j + 1;
                        }
                        j = j + 1;
                    }
                    if(!simbolo.equals(pr.getLado_izq())){
                    
                        int pos = 0;
                        for (int posicion = 0; posicion < P.size(); posicion++){
                            if (simbolo.equals(P.get(posicion).getLado_izq())){
                                pos = posicion;
                            }
                        }
                        List<String> lista = new ArrayList<>();
                        if(rec<=P.size()){
                            lista = primero(P.get(pos));
                            for (String a : lista) {
                                temporal.add(a);
                            }
                        }
                    }else{
                        
                    }

                }
            }    
            System.out.println(temporal);
            for (String a : temporal){
                if(!a.equals("'"))
                    retorno.add(a);
            }
            temporal.clear();
        }
        HashSet<String> conjunto = new HashSet<String>(retorno);
        retorno.clear();
        retorno.addAll(conjunto);
        return retorno;
    }

    public Gramaticas siguiente(Gramaticas g) {

        Gramaticas retorno = new Gramaticas();
        P = g.getProducciones();
        List<String> sig = new ArrayList<String>();
        sig.add("$");
        P.get(0).getSiguiente().add("$");
        for (int i = 0; i <= P.size() - 1; i++) {
            Producciones p = P.get(i);
            p.setSiguiente(siguiente(p));
        }
        retorno.setProducciones(P);
        return retorno;
    }

    private static List<String> siguiente(Producciones pr) {
        List<String> retorno = new ArrayList<>();
        
        return retorno;
    }

    public static boolean esTerminal(char letra) {
        String MAYUSCULAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for (int i = 0; i < MAYUSCULAS.length(); i++) {
            if (letra == MAYUSCULAS.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
