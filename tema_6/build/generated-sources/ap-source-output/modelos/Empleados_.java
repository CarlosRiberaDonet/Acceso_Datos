package modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.Incidencias;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-02T21:29:24")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile SingularAttribute<Empleados, String> contrasena;
    public static volatile SingularAttribute<Empleados, Integer> id;
    public static volatile SingularAttribute<Empleados, String> nombreUsuario;
    public static volatile SingularAttribute<Empleados, String> nombreCompleto;
    public static volatile SingularAttribute<Empleados, String> telefono;
    public static volatile CollectionAttribute<Empleados, Incidencias> incidenciasCollection;
    public static volatile CollectionAttribute<Empleados, Incidencias> incidenciasCollection1;

}