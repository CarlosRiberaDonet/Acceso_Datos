/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tema_5_carlos_ribera;

import controllers.GestorXML;
import java.util.List;
import modelos.Empleado;
import org.xmldb.api.base.Collection;


/**
 *
 * @author Carlos Ribera
 */
public class Tema_5_Carlos_Ribera {

    public static void main(String[] args) throws Exception {
        
        GestorXML gestor = new GestorXML();
        
        Collection col = gestor.conectarBD();
        
        if(col != null){
            System.out.println("Conexion establecida"); 
        } else {
            System.out.println("No se pudo conectar");
        }
        
       
        GestorXML xml = new GestorXML();
        List<Empleado> empleadosList =  xml.obtenerEmpleados();
        for(Empleado e : empleadosList){
            System.out.println("------ EMPLEADO ------");
            System.out.println(e.toString());
        }
    } 
}

