/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_Controller;
import Modelos.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
/**
 *
 * @author Carlos
 */
public class DAO 
{
    
    //Variables de conexión a la base de datos MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_basedatos";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    
    //Especificamos la base de Datos
    private final String JDBC_DDBB = "escuela";
    private final String JDBC_TABLE = "estudiantes";
    private final String JDBC_DDBB_TABLE = JDBC_DDBB + "." + JDBC_TABLE;
    
    //Variables para las consultas SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM " + JDBC_DDBB_TABLE + ";";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE id = ?";
    private final String SQL_INSERT = "INSERT INTO " + JDBC_DDBB_TABLE + " (nombre, edad, curso, media) VALUES (?, ?, ?, ?);";
    private final String SQL_UPDATE = "UPDATE " + JDBC_DDBB_TABLE + " SET nombre = ?, edad = ?, curso = ?, media = ? WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM " + JDBC_DDBB_TABLE + " WHERE id = ?";
    
    public Connection conexion() throws SQLException
    {
        Connection conn = null;
        try 
        {
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            createDB(conn);
            createTable(conn);
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace(System.out);
            throw new SQLException("Can not connect or create database with tables: " + JDBC_DDBB);
        }
        return conn;
    }
     
    private void createDB(Connection conn) throws SQLException 
    {
        //Sentencia SQL que crea la BBDD si no existe en el servidor
        String instruction = "create database if not exists " + JDBC_DDBB + ";";     
        Statement stmt = null;
        stmt = conn.createStatement();
        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt.executeUpdate(instruction);
        //Liberamos los recursos de la comunicación   
        stmt.close();
    }
      
    private void createTable(Connection conn) throws SQLException 
    {
        String query = "CREATE TABLE IF NOT EXISTS " + JDBC_DDBB_TABLE + " ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(50), "
                + "edad INT, "
                + "curso VARCHAR(255), " // Cambiado de STRING a VARCHAR
                + "media DOUBLE"
                + ");";
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
        // Liberamos los recursos de la comunicación   
        stmt.close();
    }

    public int añadir(Alumno alumno) 
    {
        //Inicialización de la conexión a la base de datos
        Connection conn = null;
        //Inicialización de la instrucción SQL
        PreparedStatement instruction = null;
        //Inicialización del conjunto de resultados para las claves generadas
        ResultSet nuevoId = null;
        //Inicialización de la variable que almacenará la cantidad de registros afectados por la inserción
        int registros = 0;
        try
        {
            //Establecer la conexión con la base de datos
            conn = conexion();
            //Preparar la sentencia SQL para la inserción, indicando que queremos obtener las claves generadas
            instruction = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //Establecer el nombre del alumno en la sentencia SQL
            instruction.setString(1, alumno.getNombre());
            //Establecer la edad del alumno en la sentencia SQL
            instruction.setInt(2, alumno.getEdad());
            //Establecer el curso del alumno en la sentencia SQL
            instruction.setString(3, alumno.getCurso());
            //Establecer la media del alumno en la sentencia SQL
            instruction.setDouble(4, alumno.getMedia());
            //Ejecutar la inserción y obtener la cantidad de registros afectados
            registros  = instruction.executeUpdate();
            
            //Recupero el ID generado por la base de datos            
            nuevoId = instruction.getGeneratedKeys();
            //Compruebo si hay un "id" nuevo generado
            if ( nuevoId.next())
            {
                //Obtengo el primer valor generado, que será el "ID"
                int idGenerado = nuevoId.getInt(1);
                //Asigno el "idGenerado" al campo "id" del objeto Alumno creado
                alumno.setId(idGenerado);
                
            }
        } catch (SQLException ex) {
            System.out.println("No se ha podido añadir el alumno a la base de datos.");
        } 
        finally 
        {
            try 
            {
                instruction.close();
                disconnect(conn);
            }
            catch (SQLException ex) {
                System.out.println("No se ha podido cerrar la conexión con la base de datos.");
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registros;
    }
    
    public int obtenerUltimoID() throws SQLException
     {
         //Objeto de conexión a la base de datos
         Connection conn = null;
         //Objeto para ejecutar la consulta SQL
         Statement stmt = null;
         //Objeto para almacenar los resultados de la consulta
         ResultSet rs = null;
         //Variable para almacenar el último "id"
         int ultimoId = 0;
         
         try
         {
             //Establezco la conexion con la base de datos
             conn = conexion();
             //Crep un nuevo statment para ejecutar la consulta
             stmt = conn.createStatement();
             //Ejecuto la consulta SQL para obtener el maximo "id" de la tabla alumnos
             rs = stmt.executeQuery("SELECT MAX(id) FROM alumnos");
             //Si hay resultado en "ResultSet"
             if (rs.next())
             {
                 //Obtengo el valor del primer campo del primer registro
                 ultimoId = rs.getInt(1);
                 //incremento el ultimo id en +1
                 ultimoId ++;
             }          
         }
         catch (SQLException ex)
         {
             ex.printStackTrace();
         }
         finally
         {
             try
             {
                 if (rs != null) rs.close();
                 if ( stmt != null) stmt.close();
             }
             catch (SQLException ex) 
             {
                
                ex.printStackTrace();
             }
         }
         return ultimoId;
     }
    
    public Alumno buscarId(int id) throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alumno alumno = null;
        try
        {
            conn = conexion();
            //Preparo la consulta SQL para buscar al alumno por su "ID"
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            //Establezco el "ID" en la consulta preparada
            stmt.setInt(1, id);
            //Ejecuto la consulta y obtengo los resultados
            rs = stmt.executeQuery();
            //Si hay algun alumno con el id proporcionado, creo un objeto Alumno con los datos obtenidos
            if (rs.next())
            {
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String curso = rs.getString("curso");
                double media = rs.getDouble("media");               
                alumno = new Alumno(id, nombre, edad, curso, media);           
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                stmt.close();
                disconnect(conn);
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return alumno;
    }
  
    public int actualizar(Alumno alumno) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try
        {
            conn = conexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString( 1, alumno.getNombre());
            stmt.setInt(2, alumno.getEdad());
            stmt.setString(3, alumno.getCurso());
            stmt.setDouble(4, alumno.getMedia());
            stmt.setInt(5, alumno.getId());
            registros = stmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        } 
        finally
        {
            try
            {
                stmt.close();
                disconnect(conn); 
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return registros;
    }
    
    public void eliminar(int id) throws SQLException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            conn = conexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            int alumnoEliminado = stmt.executeUpdate();
            //Verifico si el alumno se elimino correcamente
            if ( alumnoEliminado > 0 )
                {
                    disconnect(conn);
                    stmt.close();
                }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
        
    public List<Alumno> listarTodos()
    {
        List<Alumno> alumnos = new ArrayList<>();
        Connection conn = null;
        Statement instruction = null;
        ResultSet rs = null;
        try 
        {
            conn = conexion();
            instruction = conn.createStatement();
            rs = instruction.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String curso = rs.getString("curso");
                double media = rs.getDouble("media");
                alumnos.add(new Alumno(id, nombre, edad, curso, media));
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("No se puede leer desde la base de datos - listarTodos");
        } 
        finally 
        {
            try 
            {
                if (rs != null) 
                {
                    rs.close();
                }
                if (instruction != null) 
                {
                    instruction.close();
                }
                disconnect(conn);
            } catch (SQLException ex) 
            {
                System.out.println("No se puede leer desde la base de datos - listarTodos");
            }
        }
            return alumnos;
    }
    
     public void disconnect(Connection conn) 
    {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                //throw new DAO_Excep("Can not disconnect from database " + JDBC_DDBB);
            }
        }
    }
}
