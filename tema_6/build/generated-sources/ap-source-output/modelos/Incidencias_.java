package modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Empleados;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-02T17:54:58")
@StaticMetamodel(Incidencias.class)
public class Incidencias_ { 

    public static volatile SingularAttribute<Incidencias, Date> fecha;
    public static volatile SingularAttribute<Incidencias, Character> tipo;
    public static volatile SingularAttribute<Incidencias, Empleados> idEmpleadoDestino;
    public static volatile SingularAttribute<Incidencias, Empleados> idEmpleadoOrigen;
    public static volatile SingularAttribute<Incidencias, Integer> id;
    public static volatile SingularAttribute<Incidencias, String> detalle;

}