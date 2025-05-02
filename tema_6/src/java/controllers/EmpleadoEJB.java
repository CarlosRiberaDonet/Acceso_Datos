/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import modelos.Empleados;


/**
 *
 * @author Carlos Ribera
 */
@Stateless
public class EmpleadoEJB {
    
    @PersistenceContext(unitName = "tema6PU")
    private EntityManager em;
    
    // Método para insertar un nuevo empleado en la BD
    public void insertarEmpleado(Empleados empleado){
        
        em.persist(empleado);
    }
    
    public boolean validarEmpleado(String nombreUsuario, String pass){
        
        try{
            em.createNamedQuery("Empleados.validarLogin", Empleados.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .setParameter("contrasena", pass)
                    .getSingleResult();
            return true;
            
        } catch(NoResultException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Empleados getEmpleadoById(int id){
        
        return em.find(Empleados.class, id);
    }
    
    public void modificarEmpleado(Empleados empleado){
        
        System.out.println("Entrando en modificarEmpleado con: " + empleado.getNombreUsuario());
        // Actualiza los datos si empleado ya existe en la BD
        em.merge(empleado);
    }
    
    public Empleados checkNombreUsuario(String nombreUsuario){
        
        try{
            return em.createNamedQuery("Empleados.findByNombreUsuario", Empleados.class)
                .setParameter("nombreUsuario", nombreUsuario)
                .getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    public Empleados checkNombreCompleto(String nombreCompleto){
        
        System.out.println("Buscando empleado: " + nombreCompleto);
        try{
            return em.createNamedQuery("Empleados.findByNombreCompleto", Empleados.class)
                    .setParameter("nombreCompleto", nombreCompleto)
                    .getSingleResult();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean cambiarPassword(String nombreUsuario, String contrasena){

        try{
            // Compruebo si el usuario existe
            Empleados empleado = checkNombreUsuario(nombreUsuario);

            if(empleado != null){
                em.createNamedQuery("Empleados.cambiarPassword", Empleados.class)
                        .setParameter("nombreUsuario", nombreUsuario)
                        .setParameter("contrasena", contrasena)
                        .executeUpdate();
               return true;
            }
        } catch(Exception e){
            e.printStackTrace();
        } 
        return false;
    }
    
    public boolean eliminarEmpleado(String nombreUsuario){
        
        try{
            // Compruebo si el nombre de usuario existe en la BD
            Empleados e = checkNombreUsuario(nombreUsuario);
            // Si el nombre de usuario existe, creo un objeto de tipo Empleados con los datos del mismo
            if(e != null){
                int idEmpleado = e.getId(); // Obtengo el id del empleado
                
                // Creo la consulta para eliminar el empleado de la BD
                em.createNamedQuery("Empleados.eliminarEmpleado")
                        .setParameter("id", idEmpleado)
                        .executeUpdate();
                return true;
            }
        } catch(Exception e){
            e.printStackTrace();      
        }
        return false;  
    }
}
