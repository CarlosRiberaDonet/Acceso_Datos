/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import entidades.Empleado;


/**
 *
 * @author Carlos Ribera
 */
public class DAO {
    
    // Atributo para la persistencia en de db4o
    private static ObjectContainer db = null; 
    
    public DAO(){

        if (db == null) { // Solo abre la base de datos si aún no está abierta
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "archivo.db");
        }
    }
    
    public void closeDB(){
        if(db != null){
            db.close();
            db = null;
        }
    }
    
    // Método para guardar un objeto
    public void SaveObject(Object o){
        db.store(o);
        System.out.println("Objeto guardado ");
    }
    
    // Método para buscar nombre de usuario
    public boolean BuscarNombreUsuario(String nombreUsuario){
        
        // Obtengo la conexión a db4o
        
        Empleado empleado = new Empleado(nombreUsuario); // Creo un objeto Empleado
        ObjectSet<Empleado> found = db.queryByExample(empleado);  //Busco en db4o
        
        return !found.isEmpty(); // Si encuentra el nombreUsuario, devuelve true
    }
}
