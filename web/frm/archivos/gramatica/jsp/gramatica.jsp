<%-- 
    Document   : agregar
    Created on : 01-jul-2016, 9:50:21
    Author     : Administrator
--%>
<%@page import="jsppostgre.controladores.Analizador"%>
<%@page import="jsppostgre.controladores.Analizador2"%>
<%@page import="jsppostgre.controladores.PreAnalizador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="jsppostgre.modelos.Gramaticas"%>
<%@page import="jsppostgre.modelos.Producciones"%>
<%@page import ="org.json.simple.JSONObject"%>
<%@page import ="java.sql.ResultSet"%>

<%
    String gramatica = request.getParameter("gramatica");
    //System.out.println("hola");
    
    List<String> prueba=new ArrayList<>();
    String ele_prueba = "";
    List<String> prueba2=new ArrayList<>();
    String ele_prueba2 = "";
    String lista_primero="";
    String lista_siguiente="";
    //out.println(prueba);

    Gramaticas G = new Gramaticas();
    G = PreAnalizador.construir_gramatica(gramatica);
    PreAnalizador.factorear(G);
    
    //se imprime la gramatica luego de factorear
    List<Producciones> test_f = new ArrayList<Producciones>();
        test_f = G.getProducciones();
        //System.out.println(prueba);
        
        for( int i=0; i<test_f.size();i++)
        {
            ele_prueba2 = ele_prueba2 + "<tr>";
            System.out.println("produccion  "+i+ ":"+test_f.get(i).getLado_izq()+ "->"+test_f.get(i).getLado_der());
            ele_prueba2 = ele_prueba2+"<td>"+test_f.get(i).getLado_izq()+ " -> "+test_f.get(i).getLado_der();
            //prueba2.add(ele_prueba2);
        }
    
    System.out.println(ele_prueba2);
    
    PreAnalizador.suprimir_recursividad(G);
    //System.out.println(G);
    List<String> primero = new ArrayList<>();
    primero.add("a");
    primero.add("c");
    
    List<String> siguiente = new ArrayList<>();
    siguiente.add("$");
    siguiente.add("c");
    
    List<Producciones> test = new ArrayList<Producciones>();
        test = G.getProducciones();
        //System.out.println(prueba);
        for( int i=0; i<test.size();i++)
        {
            ele_prueba = ele_prueba + "<tr>";
            System.out.println("produccion  "+i+ ":"+test.get(i).getLado_izq()+ "->"+test.get(i).getLado_der());
            ele_prueba = ele_prueba+"<td>"+ test.get(i).getLado_izq()+ " -> "+test.get(i).getLado_der();
            //prueba.add(ele_prueba);
            //solo para introducir primero y sgte
            //Producciones p = new Producciones();
            //p = test.get(i);
            //p.setPrimero(primero);
            //p.setSiguiente(siguiente);
        }
    
    Gramaticas H = new Gramaticas();
    H = Analizador2.primero(G);
    
    Analizador.siguiente(H);
        
        
    for(int i=0 ; i<test.size();i++)
    {
        lista_primero = lista_primero + "<tr>";
        lista_siguiente = lista_siguiente + "<tr>";
        lista_primero = lista_primero+ "<td>"+test.get(i).getLado_izq()+ " : "+test.get(i).getPrimero();
        lista_siguiente = lista_siguiente+ "<td>"+test.get(i).getLado_izq()+ " : "+test.get(i).getSiguiente();
    }
        
    String mensaje= "Listo";
    System.out.println(ele_prueba);
    
    //Gramaticas H = new Gramaticas();
    //H= Analizador2.primero(G);
    
    //Gson gson = new Gson();
        
    //String json = gson.toJson(test_f);
    
    //generar tabla de análisis sintactico
    
    String[][] mat;
   
    mat = PreAnalizador.matriz(G);
    
    //Gson gson = new Gson();
        
    String matriz_cadena="";
    String casilla="";
    int veces=0;
    String ll1;
    ll1 = "El lenguaje de entrada es LL(1)";
    for(int i=0; i<mat.length;i++)
        {
            //System.out.println("|");
            matriz_cadena = matriz_cadena + "<tr>";
            for(int j=0; j<mat[i].length;j++)
            {
                if(mat[i][j]==null)
                    mat[i][j]=" ";
                System.out.println("|"+ mat[i][j] + "|");
                casilla = mat[i][j];
                for(int k=0;k<casilla.length();k++)
                {
                    char kk = casilla.charAt(k);
                    if(kk=='|')
                    {
                        veces = veces + 1;
                    }
                }
                if(veces >= 4)
                {
                    ll1 = "El lenguaje de entrada no es LL(1)";
                    veces=0;
                }
                veces=0;
                //if (j!=M[i].length-1) System.out.println("\t");
                matriz_cadena = matriz_cadena+"<td>"+mat[i][j];
            }
            //System.out.println("|");
            System.out.println("\n");
            //matriz_cadena = matriz_cadena;
                
        }
    
    System.out.println(matriz_cadena);
    //String json = gson.toJson(mat);
    //System.out.println("Matriz = " + json);
    
    JSONObject obj = new JSONObject();
    obj.put("ele_prueba", ele_prueba);
    obj.put("ele_prueba2", ele_prueba2);
    obj.put("lista_primero", lista_primero);
    obj.put("lista_siguiente", lista_siguiente);
    obj.put("mensaje", mensaje);
    obj.put("matriz_cadena",matriz_cadena);
    obj.put("ll1",ll1);
    out.print(obj);
    out.flush();
 
    
    //PreAnalizador.suprimir_recursividad(G);
    
    /*GRAMATICAS DE ENTRADA
    S>AaBb|BbAa;A>AAaa|AAbb|#;B>BBbb|BBaa|#;
    S>Sc|cA|#;A>aA|a;
    S>AA;A>AS|AB0|AB1|0;B>01|10|#;
    A>ATa|ATb;T>BTa|BTb|#;B>aa|ab|ba|bb;
    E>TH;H>+TH|#;T>FS;S>*FS|#;F>(E)|i;
    P>iEtPN|a;N>eP|#;E>b;
    
    
    */
%>
