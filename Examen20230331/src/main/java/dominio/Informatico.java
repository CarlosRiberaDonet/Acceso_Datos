/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import excepciones.NoMuertoException;
import java.text.ParseException;

/**
 *
 * @author Carlos Ribera
 */
public class Informatico extends Cientifico{

    public Informatico(String nombre, String nacimiento, String muerte) throws ParseException, NoMuertoException{
        super(nombre, nacimiento, muerte);
    }
    
    public void calcular(){
        calcularDiasVividos();
    }
}
