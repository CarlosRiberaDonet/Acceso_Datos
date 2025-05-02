/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
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
    
    public List<Incidencias> getIncidenciasList(){
        
        try{
            return em.createNamedQuery("Incidencias.findAll", Incidencias.class)
                    .getResultList();
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public void crearIncidencia(Incidencias i){
        em.persist(i);
    }
}
