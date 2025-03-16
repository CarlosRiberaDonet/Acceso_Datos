/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package OwnAPIs;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Ribera
 */
public class DataValidation {

    // Método para validar la longitud del nombre de usuario
    public boolean SolicitaNombreUsuario(String nombreUsuario){
        
        
        if(nombreUsuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(nombreUsuario.length() > 30){
            JOptionPane.showMessageDialog(null, "El nombre de usuario no puede tener más de 50 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true; // Nombre válido
    }
    
    public boolean SolicitaNombreCompleto(String nombreCompleto){
        
        if(nombreCompleto.isEmpty()){
            JOptionPane.showMessageDialog(null, "El nombre completo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(nombreCompleto.length() > 50){
            JOptionPane.showMessageDialog(null, "El nombre completo no puede tener más de 50 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true; // Nombre válido
    }
    
    // Método para validar la longitud de la contraseña
    public boolean SolicitaContraseña(String pass){
        
        if(pass == null || pass.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(pass.length() < 8 || pass.length() > 50){
            JOptionPane.showMessageDialog(null, "La contraseña tiene que contener entre 8 y 50 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    // Método para validar la longitud del teléfono
    public boolean SolicitaTelefono(String telefono){
        
        try{
            // Verifico si tiene exactamente 9 caracteres
            if(telefono.length() != 9){
                JOptionPane.showMessageDialog(null, "El teléfono debe contener 9 números", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
        }
            
            int numero = Integer.parseInt(telefono); // Covierto el String a int para verificar que sean solo números
            
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El campo teléfono debe contener exactamente 9 números", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }   
        
        return true; // Si todo esta bien, devuelve true
    }
}
