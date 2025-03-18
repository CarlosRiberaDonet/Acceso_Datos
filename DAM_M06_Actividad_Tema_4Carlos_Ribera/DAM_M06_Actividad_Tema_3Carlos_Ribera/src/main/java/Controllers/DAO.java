/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controllers;

import Modelos.Empleados;
import Modelos.Incidencias;
import Utils.HibernateUtil;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Carlos Ribera
 */
public class DAO {
    
    // Método para guardar un objeto en la base de datos
    public void Guardar(Object objeto) {
        
        Transaction ts = null;
        Session session = HibernateUtil.AbrirSesion(); // Abro la sesión de hibernate
        try{            
            ts = session.beginTransaction(); // Inicio la transacción
            
            session.persist(objeto); // Guardo el objeto
            ts.commit(); // Guardo los cambios
            
        } catch (Exception e) {
            if (ts != null) {
                ts.rollback(); // Revertir cambios si hay error
                e.printStackTrace();
            }         
        } finally{
            HibernateUtil.CerrarSesion(session);
        }
    }
    
    // Método para validar el nombre de usuario y contraseña
    public Empleados ValidarCredenciales(String nombreUsuario, String contrasena) {
        
        Session session = HibernateUtil.AbrirSesion(); // Abro la sesión de hibernate
        try {
            return session.createQuery("FROM Empleados e WHERE e.nombreUsuario = :nombre AND e.contrasena = :contrasena", Empleados.class)
                          .setParameter("nombre", nombreUsuario)
                          .setParameter("contrasena", contrasena)
                          .uniqueResult(); // Retorna el empleado si existe o null si no coincide
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            HibernateUtil.CerrarSesion(session);
        }
    }
    
    // Método para buscar empleado por nombre de usuario
    public Empleados BuscarNombreUsuario(String nombreUsuario){
        
        Session session = HibernateUtil.AbrirSesion(); // Abro la sesión de hibernate
        
        try {
            return session.createQuery("FROM Empleados e WHERE e.nombreUsuario = :nombre", Empleados.class)
                          .setParameter("nombre", nombreUsuario)
                          .uniqueResult(); // Retorna el empleado si existe o null si no coincide
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally{
            HibernateUtil.CerrarSesion(session);
        }
    }
    
    public Empleados BuscarNombreEmpleado(String nombreEmpleado){
        
        Session session = HibernateUtil.AbrirSesion();

        try {
            Empleados empleado = session.createQuery(
                    "FROM Empleados e WHERE e.nombreCompleto = :nombreEmpleado", Empleados.class)
                    .setParameter("nombreEmpleado", nombreEmpleado)
                    .uniqueResult();

            //Si el empleado existe, forzar la carga de incidencias antes de cerrar la sesión
            if (empleado != null) {
                Hibernate.initialize(empleado.getIncidenciasDestinadas());
            }

            return empleado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.CerrarSesion(session);
        }
    }

     
    // Método que actualiza los datos del empleado
    public Empleados ActualizarEmpleado(Empleados empleado) {
        
        Transaction ts = null;
        Session session = HibernateUtil.AbrirSesion(); // Abro la sesión de hibernate
        
        try {
            ts = session.beginTransaction();
            session.update(empleado); // Actualizo el objeto en la base de datos
            ts.commit();// Guardo los cambios
            
        } catch(Exception e){
            if(ts != null) {
                ts.rollback(); // Revierto los cambios si hay algún error
            }
            e.printStackTrace();
        } finally{
            HibernateUtil.CerrarSesion(session);
        }   
        return null;
    }
    
    // Método para actualizar únicamente la contraseña del usuario
    public boolean ActualizarContraseña(String nombreUsuario, String nuevaContrasena){
        
        Session session = HibernateUtil.AbrirSesion(); // Abro la sesión de hibernate
        
        try{
            Transaction ts = session.beginTransaction();
            
            // Consulta para actualizar solo la contraseña
            Query query = session.createQuery("UPDATE Empleados e SET e.contrasena = :nuevaContrasena WHERE e.nombreUsuario = :nombreUsuario");
            query.setParameter("nuevaContrasena", nuevaContrasena);
            query.setParameter("nombreUsuario", nombreUsuario);
            
            int filasActualizadas = query.executeUpdate();
            ts.commit();
            
            if(filasActualizadas > 0){
                return true; // Si se ha actualizado correcamente en la BD, devuelvo true           
            }
      
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            HibernateUtil.CerrarSesion(session);
        }   
        return false; // Si no se ha actualizado la contraseña en la BD devuelvo false
    }
       
    // Método para eliminar empleado mediante su nombre de usuario
    public boolean EliminarEmpleado(String nombreUsuario) {
        
        Transaction ts = null;
        Session session = HibernateUtil.AbrirSesion(); // Abro la sesión de hibernate
        
        try{
            ts = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Empleados e WHERE e.nombreUsuario = :nombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuario);

            int filasAfectadas = query.executeUpdate();
            
            ts.commit(); // Guardo los cambios en la BD
            
            // Compruebo si han habido filas afectadas en la tabla
            if(filasAfectadas > 0){
                return true; // Si se ha actualizado correcamente en la BD, devuelvo true   
            }
        } catch(Exception e){
            if(ts != null){ // Hago rollback si la transacción fue iniciada pero hubo algún fallo
                ts.rollback();
            }
            e.printStackTrace(); // Si se ha actualizado correcamente en la BD, devuelvo false   
        } finally{
            HibernateUtil.CerrarSesion(session);
        }   
        return false;
    }
      
    // Método para buscar incidencia mediante id
    public Incidencias GetIncidencia(int id){
     
        Session session = HibernateUtil.AbrirSesion();
        
        try{
            return session.createQuery("FROM Incidencias i WHERE i.id = :id", Incidencias.class)
                          .setParameter("id", id)
                          .uniqueResult(); // Retorna la incidencia si existe o null si no coincide
            
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally{
            HibernateUtil.CerrarSesion(session);
        }
    }

    public List<Incidencias> GetIncidenciasList(){
        
        Session session = HibernateUtil.AbrirSesion();
        
        try{
            org.hibernate.query.Query<Incidencias> query = session.createQuery("FROM Incidencias", Incidencias.class);
            return query.getResultList(); // Ejecuto la consulta y devuelvo la lista
                          
            
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally{
            HibernateUtil.CerrarSesion(session);
        }
    }
    
    public List<Incidencias> IncidenciasList(String empleadoNombreCompleto){
        
        Session session = HibernateUtil.AbrirSesion();
        
        try{
            List<Incidencias> incidenciasList = session.createQuery(
                "FROM Incidencias i WHERE i.empleadoOrigen.nombreCompleto = :empleadoNombre", Incidencias.class)
                 .setParameter("empleadoNombre", empleadoNombreCompleto)
                 .getResultList();
            
            return incidenciasList;
            
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally{
            HibernateUtil.CerrarSesion(session);
        }
    }
    
     /*public boolean BuscarIdEmpleado(int id){
        
        Session session = HibernateUtil.AbrirSesion();
        
        try{
            return session.get(Empleados.class, id) != null; // Si encuentra el id en la tabla empleado, retornta true, si no, false
            
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            HibernateUtil.CerrarSesion(session);
        }
        return false;
    }*/

}
