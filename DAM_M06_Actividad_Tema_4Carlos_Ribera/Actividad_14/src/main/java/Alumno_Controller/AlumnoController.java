/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumno_Controller;
import DAO_Controller.DAO;
import Modelos.Alumno;
import static OwnAPIs.DataValidation.solicitaDouble;
import static OwnAPIs.DataValidation.solicitaId;
import static OwnAPIs.DataValidation.solicitaInt;
import static OwnAPIs.DataValidation.wordNotEmpty;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class AlumnoController
{
    private DAO dao = new DAO();
  
     public void ingrearAlumno() throws SQLException
     {
        Scanner sc = new Scanner(System.in);
        try
        {
            //Obtengo el "id" que le asignare al alumno
            int id = dao.obtenerUltimoID();
            
            String nombre = wordNotEmpty("Ingresa el nombre del alumno: ");
            int edad = solicitaInt(0, 120, "Ingresa la edad del alumno: ");
            String curso = wordNotEmpty("Ingresa el nombre del curso: ");
            double media = solicitaDouble(0, 10, "Ingresa la nota media del alumno");

            //Creo un nuevo objeto Alumno para añadir a la base de datos
            Alumno nuevoAlumno = new Alumno(id, nombre, edad, curso, media);

            //Resgistro el nuevo alumno a la base de datos
            int registrarAlumno = dao.añadir(nuevoAlumno); 
            
            //Si el alumno se registra correctamente
             if (registrarAlumno == 1) 
             {
                 //Le asigno el "id" que he obtenido anteriormente en el metodo "dao.obtenerUltimoId"
                 int idAsignado = nuevoAlumno.getId(); 
                 
                 System.out.println( "El alumno [" + nombre + "] tiene asignado el id: [" + idAsignado + "]");
                 System.out.println("[" + nuevoAlumno.getNombre() + "]" + " ha sido registrado correctamente en la base de datos.");                    
             }          
             else
             {
                 System.out.println("No se ha podido registrar el alumno en la base de datos.");
             }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
       
    }
     
    public void actualizarAlumno() throws SQLException            
    {
        Scanner sc = new Scanner(System.in);
         //Llamo al metodo que me devuelve true si la lista esta vacia, y false si la lista contiene algun alumno
        comprobarLista();
         //Si "comprobarLista" es false, la lista contiene alumnos
        if (!comprobarLista())
        {
            //Obtengo los registros almacenados en la base de datos y los almaceno en una lista
            List<Alumno> alumnos = dao.listarTodos();
            try
            {
                System.out.println("Ingrese el número de ID del alumno: ");
                int idAlumno = sc.nextInt();

                //Busco al alumno en la base de datos mediante el "id" introducido por el USR
                Alumno a = dao.buscarId(idAlumno);

                //Si el "id" introducido por el USR pertenece a un alumno, solicito los datos al USR, si no, "dao.buscarId" me devuelve "null"
                if ( a != null)
                {
                    //Solicito al USR que introduzca los nuevos datos del alumno
                    String nuevoNombre = wordNotEmpty("Ingresa el nuevo nombre del alumno: ");
                    int nuevaEdad = solicitaInt(0, 120, "Ingresa la nueva edad del alumno: ");
                    String nuevoCurso = wordNotEmpty("Ingresa el nombre del nuevo curso: ");
                    double nuevaMedia = solicitaInt(0, 10, "Ingresa la nueva nota media del alumno");

                    //Actualizo los datos del alumno con los nuevos valores
                    a.setNombre(nuevoNombre);
                    a.setEdad(nuevaEdad);
                    a.setCurso(nuevoCurso);
                    a.setMedia(nuevaMedia);

                    //Introduzco los nuevos valores del alumno en la base de datos
                    dao.actualizar(a);
                    System.out.println("Los datos del alumno han sido actualizados correctamente.");
                }   
                else
                {
                    System.out.println("No se encontró ningún alumno con el ID proporcionado.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Los caracteres no están permitidos.");
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }  
        }
        else
        {
            System.out.println("No existen alumnos registrados en la base de datos.");
        }
    }

    public void borrarAlumno() throws SQLException            
    {     
        Scanner sc = new Scanner(System.in);
         //Llamo al metodo que me devuelve true si la lista esta vacia, y false si la lista contiene algun alumno
        comprobarLista();

        //Si "comprobarLista" es false, la lista contiene alumnos
        if (!comprobarLista())
        {
            try
            {           
                //Pido al USR que ingrese el "Id" del alumno que quiere borrar de la base de datos
                int idAlumno = solicitaId(1, "Introduzca el ID del alumno que desea eliminar:");
                Alumno a = dao.buscarId(idAlumno);
                if (a !=null)
                {
                    dao.eliminar(idAlumno);
                    System.out.println("El alumno con el ID ["  + idAlumno + "] ha sido eliminado.");
                }
                else
                {
                    System.out.println("El ID proporcionado no es válido.");
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }    
        else 
        {
            System.out.println("No existen alumnos registrados en al base de datos.");
        }     
    }
        
    
    public void listarAlumnos() throws SQLException
    {
        //Llamo al metodo que me devuelve true si la lista esta vacia, y false si la lista contiene algun alumno
        comprobarLista();
        //Si "comprobarLista" es false, la lista contiene alumnos
        if (!comprobarLista())
        {
            //Obtengo la lista de todos los alumnos
            List<Alumno> alumnos = dao.listarTodos();
            //Itero sobre alumnos y muestro los datos
            for ( Alumno a : alumnos)
            {
                System.out.println("**********ALUMNO**********");
                System.out.println("Id: " + a.getId());
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Edad: " + a.getEdad());
                System.out.println("Curso: " + a.getCurso());
                System.out.println("Media: " + a.getMedia());
            }
        }
        else
        {
            System.out.println("La base de datos no contiene alumnos registrados.");
        }   
    }
    
    public boolean comprobarLista()
    {
        
        boolean listaVacia = true;
        //Obtengo los registros almacenados en la base de datos y los almaceno en una lista
        List<Alumno> a = dao.listarTodos();
        //Si la lista no esta vacia
        if (!a.isEmpty())
        {
            listaVacia = false;
        }
        return listaVacia;
    }
}
