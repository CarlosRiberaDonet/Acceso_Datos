/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import excepciones.NoMuertoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Carlos Ribera
 */
public class Cientifico {
    
    protected String nombre;
    protected Date nacimiento;
    protected Date muerte;
    protected int diasVividos;
    
    // CONSTRUCTOR

    public Cientifico(String nombre, String nacimiento, String muerte) throws ParseException, NoMuertoException {
        this.nombre = nombre;
        
        // Convertimos el String a Date usando SimpleDateFormat
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.nacimiento = formato.parse(nacimiento);
        
        if(muerte.equals("99/99/9999")){
            // Está vivo, le asignamos la fecha de hoy
            Date hoy = new Date();
            this.muerte = formato.parse(formato.format(hoy));   
        } else{
            throw new NoMuertoException(nombre);
        }
    }
    
    public int getDiasVividos(){
        return diasVividos;
    }
   
    public void calcularDiasVividos(){
        
    }
    
    @Override
    public String toString(){
        return "Nombre: " + nombre + " " +
                "Nacimiento: " + nacimiento + " " +
                "Muerte: " + muerte;
    }
}
