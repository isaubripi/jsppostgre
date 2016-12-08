

package jsppostgre.utiles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utiles {
    public static final int REGISTROS_PAGINA=10;
    //private static Date date;
    
    public static String quitarGuiones(String texto){
        return texto.replace("--","");
    }

    public static String md5(String palabra){
        String palabraMD5="";
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] b=md.digest(palabra.getBytes());
            int size=b.length;
            StringBuffer sb=new StringBuffer(size);
            for(int i=0;i<size;i++){
                int u=b[i] & 255;
                if(u<16){
                    sb.append("0"+Integer.toHexString(u));
                }
                else{
                    sb.append(Integer.toHexString(u));
                }
            }
            palabraMD5=sb.toString();
        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return palabraMD5;
    }
}
    
/*    public static Date convertir(String palabra) {
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
        
        try {
		Date date = (Date) formatter.parse(palabra);
		System.out.println(date);
		System.out.println(formatter.format(date));

	} catch (ParseException e) {
		e.printStackTrace();
	}
        
        return date;
    }

    
}*/
