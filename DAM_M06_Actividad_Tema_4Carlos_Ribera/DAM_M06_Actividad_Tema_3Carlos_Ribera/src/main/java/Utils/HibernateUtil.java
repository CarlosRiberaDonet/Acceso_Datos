/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Carlos Ribera
 */

public class HibernateUtil {
    private static final SessionFactory sessionFactory = ConstruirSession();

    /**
     * Configura y construye la SessionFactory de Hibernate.
     */
    private static SessionFactory ConstruirSession() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // Cargar la configuración

            // Agregar las clases manualmente para evitar problemas con el mapeo
            configuration.addAnnotatedClass(Modelos.Empleados.class);
            configuration.addAnnotatedClass(Modelos.Incidencias.class);

            // Construir el servicio de Hibernate
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            // Construir la SessionFactory
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            System.err.println("Error al iniciar Hibernate:");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Obtiene una nueva sesión de Hibernate.
     */
    public static Session AbrirSesion() {
        return sessionFactory.openSession();
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