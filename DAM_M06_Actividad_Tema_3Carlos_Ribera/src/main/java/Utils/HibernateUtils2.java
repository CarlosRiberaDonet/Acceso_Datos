/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Modelos.Empleados;
import static Utils.HibernateUtil.getSessionFactory;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Carlos Ribera
 */
public class HibernateUtils2 {
    
    private static final SessionFactory sessionFactory;
    static{
        try{
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Empleados.class)
                    .buildSessionFactory();
        }catch(Exception e){
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static Session abrirSession(){
        Session session = getSessionFactory().openSession();
        return session;
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static void cerrarSession(Session session){
        if(session != null){
            try{
                session.close();
            } catch(Exception e){
                 e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
        }
    }
    
    
    public static void eliminarEmpleado(String nombreCompleto){
        
        Session session = abrirSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Empleados e WHERE e.nombreCompleto = :nombreCompleto");
            query.setParameter("nombreCompleto", nombreCompleto);
            int filasAfectadas = query.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Empleado eliminado correctamente");
            }
            tx.commit();
        } catch(Exception ex){
            if(tx != null){
                tx.rollback();
            }
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        } finally{
            cerrarSession(session);
        }
    }
}
