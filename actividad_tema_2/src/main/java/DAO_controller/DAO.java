/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO_controller;

import Modelos.Empleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Carlos Ribera
 */
public class DAO {
    
    // Variables de conexión para la base de datos MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/empleado";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "1234";
    
    // Variables para especificar la base de datos
    private final String JDBC_DDBB = "empleado"; // Nombre de la base de datos
    private final String JDBC_TABLE = "empleados"; // Nombre de la tabla
    private final String JDBC_DDBB_TABLE = JDBC_DDBB + "." + JDBC_TABLE; // bbdd.empleados
    
    // Variables para las consultas SQL
    private final String SQL_INSERT = "INSERT INTO " + JDBC_DDBB_TABLE + "(nombre_usuario, contrasena, nombre_completo, telefono) VALUES (?, ?, ?, ?);"; // Insertar usuario
    private final String SQL_BUSCAR_USUARIO = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE nombre_usuario = ?"; // Buscar usuario
    private final String SQL_MODIFICAR_USUARIO = "UPDATE " + JDBC_DDBB_TABLE + " SET nombre_usuario = ?, nombre_completo = ?, telefono = ?  WHERE id = ?"; // Modificar usuario
    private final String SQL_MODIFICAR_CONTRASEÑA = "UPDATE " + JDBC_DDBB_TABLE + " SET contrasena = ? WHERE id = ?"; // Modificar contraseña
    private final String SQL_ELIMINAR_EMPLEADO = "DELETE FROM " + JDBC_DDBB_TABLE + " WHERE id = ?";
    private final String SQL_BUSCAR_ID = " SELECT COUNT(*) FROM " + JDBC_DDBB_TABLE + " WHERE id = ?"; // Buscar empleado por id

    // Método para la conexión con la base de datos
    public Connection Conexion() throws SQLException{
        
        Connection conn = null;
        try{
            
            conn = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);     
            
        } catch(SQLException e){
            e.printStackTrace();
            throw new SQLException("Can not connect or create database with tables: " + JDBC_DDBB);
        }
               
        return conn;
    }
    
    // Método para cerrar la conexión con la base de datos
    public static void CerrarConexion(Connection conn){
        
        try{
            if(conn != null){
            conn.close();
                System.out.println("Conexion cerrada correctamente.");
            }
        }catch(SQLException e){
            System.out.println("Error al intentar cerrar la conexion con la base de datos");
            e.printStackTrace();
        }  
    }
    
    public static void CerrarRecursos(ResultSet rs, PreparedStatement stmt){
        
        try{
            if(rs != null){
                rs.close(); // Cierro ResultSet
            }
            if(stmt != null){
                stmt.close(); // Cierro PreparedStatement
            }           
        } catch(SQLException e){
            System.out.println("Error al cerrar recursos");      
        }
    }       
    
    // Método para insertar un empleado en la base de datos
    public void Insertar(Empleado empleado) throws SQLException{
        
        // Inicializo la conexión con la base de datos
        Connection conn = null;
        // Inicializo la consulta a la base de datos
        PreparedStatement stmt = null;
        
        try{
            
        // Establezco la conexión con la base de datos
        conn = Conexion();
        // Preparo la sentencia SQL para la inserción indicando que quiero obtener la clavez generadas
        stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        
        // Añado los campos del empleado creado a la base de datos
        stmt.setString(1, empleado.getNombreUsuario());
        stmt.setString(2, empleado.getContraseña());
        stmt.setString(3, empleado.getNombreCompleto());
        stmt.setString(4, empleado.getTelefono());
        
        stmt.executeUpdate();
        System.out.println("Empleado creado correctamente");
            
        } catch(SQLException e){
            throw new SQLException("Error al insertar empleado: " + e.getMessage());
        }
        
        CerrarRecursos(null, stmt);
        CerrarConexion(conn);
    }
    
    // Método para buscar un usuario en la tabla empledados mediante el campo "nombre_usuario" 
    public Empleado BuscarEmpleado(String nombre) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empleado empleado = null; // Variable para guardar el empleado encontrado
        try{
            conn = Conexion(); // Establezo la conexión
            stmt = conn.prepareStatement(SQL_BUSCAR_USUARIO); // Preparo la consulta
            stmt.setString(1, nombre); // Reemplaza el "?" de la consulta por el nombre recibido
            rs = stmt.executeQuery(); // Ejecuta la consulta y guarda el resultado
            
            // Si encuentra al empleado, creo un objeto Empleado con los datod obtenidos
            if(rs.next()){
                empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombreUsuario(rs.getString("nombre_usuario"));
                empleado.setContraseña(rs.getString("contrasena"));
                empleado.setNombreCompleto(rs.getString("nombre_completo"));
                empleado.setTelefono(rs.getString("telefono"));
                
                System.out.println("Usuario encontrado: " + rs.getString("nombre_usuario")); // Mensaje de depuración
                
            } else {
                System.out.println("Usuario no encontrado");
                return null;
            }
                       
        } catch(SQLException e){
            throw new SQLException("Error al buscar el empleado: " + e.getMessage());
        } finally{
            CerrarRecursos(rs, stmt); // Cierro los recursos
            CerrarConexion(conn); // Cierro la conexión
        }
       
        return empleado; // Retorno el objeto empleado o null si no lo encuentra
    }
    
    // Método para modificar los datos del empleado
    public void ModificarEmpleado(Empleado empleado) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{            
            conn = Conexion(); // Establezo la conexión
            stmt = conn.prepareStatement(SQL_MODIFICAR_USUARIO); // Preparo la consulta
            // Asigno los valores del objeto Empleado 
            stmt.setString(1, empleado.getNombreUsuario());
            stmt.setString(2, empleado.getNombreCompleto());
            stmt.setString(3, empleado.getTelefono());
            stmt.setInt(4, empleado.getId());
            
            // Verifico si se ha modificado el empleado
            int filasActualizadas = stmt.executeUpdate();
            if(filasActualizadas > 0){
                System.out.println("Empleado modificado exitosamente");
            } else{
                System.out.println("No se ha encontrado al empleado con id: " + empleado.getId());
            }
            
        } catch(SQLException e){
            throw new SQLException("Error al modificar el empleado: " + e.getMessage());
        } finally{
            CerrarRecursos(null, stmt);
            CerrarConexion(conn);
        }   
    }
    
    // Método para modificar la contraseña del empleado
    public void ModificarContraseña(int id, String contraseña) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = Conexion(); // Establezo la conexión
            stmt = conn.prepareStatement(SQL_MODIFICAR_CONTRASEÑA); // Preparo la consulta
            stmt.setString(1, contraseña); // Reemplaza el "?" de la consulta por la contraseña recibiba
            stmt.setInt(2, id); 
            
        // Ejecuto la consulta UPDATE y guardo el resultado en la variable filasAfectadas
        int filasAfectadas = stmt.executeUpdate(); 
        // Verifico si se ha modificado la contraseña
        if(filasAfectadas > 0){
            System.out.println("Contraseña modificada exitosamente");
        }
        } catch(SQLException e){
            System.out.println("Error al intentar modificar la contraseña" + e.getMessage());           
        } finally{
            CerrarRecursos(null, stmt);
            CerrarConexion(conn);
        }
    }
    
    public void EliminarEmpleado(int id) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try{
            conn = Conexion(); // Establezo la conexion 
            stmt = conn.prepareStatement(SQL_ELIMINAR_EMPLEADO); // Preparo la consulta SQL
            stmt.setInt(1, id); // Sustituyo "?" por el id 
            
            int filasAfectadas = stmt.executeUpdate(); // Ejecuto la consulta DELETE
            
            if(filasAfectadas > 0){
                System.out.println("Empleado eliminado exitosamente");
            }
            
        } catch(SQLException e){
            System.out.println("Error al intentar eliminar al empleado" + e.getMessage());
        } finally{
            CerrarRecursos(null, stmt);
            CerrarConexion(conn);
        }
    }
    
    // Método para comprobar si existe el id en la tabla empleados
    public boolean ExisteEmpleado(int id){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = Conexion(); // Establezo la conexión
            stmt = conn.prepareStatement(SQL_BUSCAR_ID); // Preparo la consulta
            stmt.setInt(1, id); // Paso el id proporcionado por el USR a la consulta
            rs = stmt.executeQuery(); // Ejecuto la consulta SELECT
            
            // Compruebo si hay un resultado en rs
            if(rs.next()){
                int resultado = rs.getInt(1); // Obtengo el valor de COUNT(*)
                if(resultado > 0){ // Si resultado > 0, significa que el id existe
                    return true;
                }
            }
        } catch(SQLException e){
            System.out.println("Error al intentar buscar el id del empleado" + e.getMessage());
        } finally{
            CerrarRecursos(rs, stmt);
            CerrarConexion(conn);
        }
        return false;
    }
}
