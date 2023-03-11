
package model;

import javax.swing.JOptionPane;

public class Funciones {
    public static int leerNumero(String msg){
        int x;
        x= Integer.parseInt(JOptionPane.showInputDialog(msg));
        return x;
    }
    
    public static String leerCadena(String msg){
        String cad;
        cad= JOptionPane.showInputDialog(msg);
        return cad;
    }
    
    public static float leerRealPeque (String msg){
        float num;
       num= Float.parseFloat(JOptionPane.showInputDialog(msg));
       return num;
    }
    
    public static double leerRealGrande (String msg){
        double num;
        num= Double.parseDouble(JOptionPane.showInputDialog(msg));
        return num;
    }  
    
    public static char leerCaracter(String msg){
        char caracter;
        caracter= JOptionPane.showInputDialog(msg).charAt(0);
        return caracter;
    }
    
    public static boolean leerLogico(String msg){
        boolean variable= true;
        String respuesta= "Si".toLowerCase();
        respuesta= JOptionPane.showInputDialog(msg);
        if(respuesta.equals("No".toLowerCase())){
            variable= false;
        }
        return variable;
    }
    
}
