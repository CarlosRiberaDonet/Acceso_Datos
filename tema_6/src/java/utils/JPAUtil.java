/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos Ribera
 */
public class JPAUtil {
    
    // Creouna una única instancia de EntityManagerFactory para toda la aplicación (ya que he leído en el temario que es lo correcto y,
    //también me he informado de que gasta muchos recursos.
    /*private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tema6PU");
    
    // Método que devuelve un nuevo EntityManager
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }*/
}
