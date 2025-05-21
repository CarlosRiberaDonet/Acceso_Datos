/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Carlos Ribera
 */

public class HibernateUtil {
    private static final SessionFactory sessionFactory; 
    static{
        try{
            sessionFactory = new Configuration()
                            .configure("hibernate.cfg.xml")
                            .addAnnotatedClass(Modelos.Empleados.class)
                            .addAnnotatedClass(Modelos.Incidencias.class)
                            .buildSessionFactory();
        }catch(Throwable e){
            System.out.println(e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /**
     * Obtiene una nueva sesión de Hibernate.
     */
    public static Session AbrirSesion() {
        Session session = getSessionFactory().openSession();
        return session;
    }

    /**
     * Cierra la sesión de Hibernate si está abierta.
     */
    public static void CerrarSesion(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}