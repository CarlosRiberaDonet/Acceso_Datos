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
     * Configura y construye la SessionFactory de Hibernate.
     */
    
//    private static SessionFactory ConstruirSession() {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure("hibernate.cfg.xml"); // Cargar la configuración
//
//            // Agregar las clases manualmente para evitar problemas con el mapeo
//            configuration.addAnnotatedClass(Modelos.Empleados.class);
//            configuration.addAnnotatedClass(Modelos.Incidencias.class);
//
//            // Construir el servicio de Hibernate
//            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties())
//                    .build();
//
//            // Construir la SessionFactory
//            return configuration.buildSessionFactory(serviceRegistry);
//        } catch (Exception ex) {
//            System.err.println("Error al iniciar Hibernate:");
//            ex.printStackTrace();
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

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