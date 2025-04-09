/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema5;

import controllers.GestorXML;
import java.lang.reflect.InvocationTargetException;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
/**
 *
 * @author Carlos Ribera
 */
public class Tema5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        GestorXML gestor = new GestorXML();
        
        Collection col = gestor.conectarBD();
        
        if(col != null){
            System.out.println("Conexion establecida"); 
        } else {
            System.out.println("No se pudo conectar");
        }
    } 
}
