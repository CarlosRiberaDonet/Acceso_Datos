/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entidades.Empleado;
import entidades.Incidencia;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Carlos Ribera
 */
public class DAO {
    
    
    private static ObjectContainer db = null; // Conexión estatica
    
    // Método para abrir la conexion
    public DAO(){

        if (db == null) {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "archivo.db");
        }
    }
    
    // Método para cerrar la conexión
    public void closeDB(){
        
        try{
            if(db != null){
                db.close();
                db = null;
                System.out.println("Conexion cerrada correctamente");
            }
        } catch(Exception e){
            System.out.println("Error al cerrar la conexion");
        }    
    }
    
    // Método para obtener la conexión
    public static ObjectContainer getDB(){
        return db;
    }
    
    // Método para guardar un objeto
    public void SaveObject(Object objeto){
        try{
            db.store(objeto);
            db.commit();
            System.out.println("Objeto guardado ");
            
        } catch(Exception e){
            System.out.println("El error al guardar el objeto " + objeto);
        }     
    }
     
    // Método para buscar empleado por nombre de usuario
    public boolean BuscarNombreUsuario(String nombreUsuario){
        
        // Obtengo la conexión a db4o       
        Empleado empleado = new Empleado(nombreUsuario); // Creo un objeto Empleado
        ObjectSet<Empleado> found = db.queryByExample(empleado);  //Busco en db4o
        
        return !found.isEmpty(); // Si encuentra el nombreUsuario, devuelve true
    }
    
    // Método para comprobar usuario y contraseña
    public boolean ComprobarCredenciales(String nombreUsuario, String contrasena){
          
        try{
            // Si el usuario existe
            if(BuscarNombreUsuario(nombreUsuario)){          
                Empleado objetoEmpleado = new Empleado(nombreUsuario); // Creo un objeto Empleado          
                ObjectSet<Empleado> found = db.queryByExample(objetoEmpleado); // Busco en db4o          

                Empleado empleado = found.next(); // Obtengo el objeto Empleado almacenado en archivo.db            
                return empleado.getContrasena().equals(contrasena); // Compruebo que la contraseña introducida por el USR
            } 
        }catch(Exception e){
            System.out.println("Error al intentar comprobar las credenciales");
        }
        System.out.println("Usuario no encontrado o password incorrecto");
        
        return false;
    }
    
    // Método para obtener un empleado mediante su nombre de usuario
    public Empleado getEmpleado(String nombreUsuario){
        
        Empleado empleado = new Empleado(nombreUsuario);
        ObjectSet<Empleado> result = db.queryByExample(empleado);
        
        if(!result.isEmpty()){
            return result.next();
        }
        
        return null;
    }
    
    // Método para eliminar un empleado mediante su nombre de usuario
    public boolean DeleteObject(String nombreUsuario){
              
        try{
            // Creo un objeto Empleado con el nombreUsuario que queremos eliminar
            Empleado empleado = new Empleado(nombreUsuario);
            empleado.setNombreUsuario(nombreUsuario);
            
            // Busco coincidencias en archivo.db
            ObjectSet<Empleado> found = db.queryByExample(empleado);
            
            // Recorro los resultado
            while(found.hasNext()){
               db.delete(found.next()); // Elimino el objeto Empleado
            }
            // Confirmo la eliminación
            db.commit();           
            return true;
            
        } catch(Exception e){
            System.out.println("Error al eliminar el usuario" + e.getMessage());
        }
        return false;
    }
    
    // Método para obtener incidencia mediante su id
    public Incidencia getIncidencia(int id){
        
        try{
            Incidencia incidencia = new Incidencia(); // Creo el objeto para la busqueda
            incidencia.setId(id); // Establezo el id a buscar
            
            ObjectSet<Incidencia> result = db.queryByExample(incidencia);
        
            if(!result.isEmpty()){
                return result.next(); // Retorno la incidencia encontrada
            } 
            
        } catch(Exception e){
            e.printStackTrace();
        } 
        return null; // Retorno null si no encuentra ninguna incidencia
    }
    
    public List<Incidencia> getIncidenciasList(){
        
        List<Incidencia> incidenciasList = new ArrayList<>();
        try{
             ObjectSet<Incidencia> result = db.query(Incidencia.class); // Obtengo todas las incidencias
             incidenciasList.addAll(result);// Añado todas las incidencias a incidenciasList
        } catch(Exception e){
            e.printStackTrace();
        }
        return incidenciasList; // Devuelvo la lista de incidencias
    }
}