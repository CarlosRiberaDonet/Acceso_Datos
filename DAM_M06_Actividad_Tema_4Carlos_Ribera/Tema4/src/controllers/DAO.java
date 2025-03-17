/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entidades.Empleado;
import java.util.List;


/**
 *
 * @author Carlos Ribera
 */
public class DAO {
    
    
    private static ObjectContainer db = null; // Conexión estatica
    
    // Abrre la base de datos si aún no está abierta
    public DAO(){

        if (db == null) {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "archivo.db");
        }
    }
    
    // Método para cerrar la conexión
    public void closeDB(){
        if(db != null){
            db.close();
            db = null;
            System.out.println("Conexion cerrada correctamente");
        }
    }
    
    // Método para guardar un objeto
    public void SaveObject(Object o){
        db.store(o);
        System.out.println("Objeto guardado ");
    }
    
    public static ObjectContainer getDB(){
        return db;
    }
    
    // Método para buscar nombre de usuario
    public boolean BuscarNombreUsuario(String nombreUsuario){
        
        // Obtengo la conexión a db4o       
        Empleado empleado = new Empleado(nombreUsuario); // Creo un objeto Empleado
        ObjectSet<Empleado> found = db.queryByExample(empleado);  //Busco en db4o
        
        return !found.isEmpty(); // Si encuentra el nombreUsuario, devuelve true
    }
    
    public boolean ComprobarCredenciales(String nombreUsuario, String contrasena){
             
        // Si el usuario existe
        if(BuscarNombreUsuario(nombreUsuario)){          
            Empleado objetoEmpleado = new Empleado(nombreUsuario); // Creo un objeto Empleado          
            ObjectSet<Empleado> found = db.queryByExample(objetoEmpleado); // Busco en db4o
            
            
            Empleado empleado = found.next(); // Obtengo el objeto Empleado almacenado en archivo.db
            
            return empleado.getContrasena().equals(contrasena); // Compruebo que la contraseña introducida por el USR
        }
        System.out.println("Usuario no encontrado o contraseña incorrecta");
        
        return false;
    }
    
    public Empleado getEmpleado(String nombreUsuario){
        
        Empleado empleado = new Empleado(nombreUsuario);
        ObjectSet<Empleado> result = db.queryByExample(empleado);
        
        if(!result.isEmpty()){
            return result.next();
        }
        
        return null;
    }
}
