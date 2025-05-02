/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelos.Incidencias;

/**
 *
 * @author Carlos Ribera
 */
@Stateless
public class IncidenciaEJB {
    
    @PersistenceContext(unitName ="tema6PU")
    private EntityManager em;
    
    public Incidencias getIncidenciaById(int id){
        
        try{
            return em.createNamedQuery("Incidencias.findById", Incidencias.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e){
            e.printStackTrace();
        }  
        return null;  
    }
}
