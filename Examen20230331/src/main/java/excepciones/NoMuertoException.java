/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Carlos Ribera
 */
public class NoMuertoException extends Exception{
    
    public NoMuertoException(String NOMBRE_CIENTÍFICO){       
        super(error(NOMBRE_CIENTÍFICO));
    }
    
    public static String error(String NOMBRE_CIENTÍFICO){
       return "El científico " + NOMBRE_CIENTÍFICO + " no está muerto.";
    }
}
