/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import excepciones.NoMuertoException;
import interfaces.Almacenable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Ribera
 */
public class BaseDeDatos implements Almacenable{
    
     public String fichero;
     public ArrayList<Informatico> listaInformaticos;
     public ArrayList<Fisico> listaFisicos;
     
     // Constructor que se le pasa como parámetro la ruta del fichero
     public BaseDeDatos(String fichero){
         this.fichero = fichero;
         this.listaInformaticos = new ArrayList<>();
         this.listaFisicos = new ArrayList<>();
     }

     @Override
     public void cargarFichero(){
         cargarFichero(fichero);
     }                                                                                                                                                                                                                                                                                                  
     
     @Override
     public void cargarFichero(String ruta){
         
        File archivo = new File(ruta);
         
         if(archivo.exists()){
             try{
             BufferedReader br = new BufferedReader(new FileReader(ruta));
             
             String linea;
             
             // Leemos el archivo línea por línea
             while((linea = br.readLine()) != null){
                    String[] partes = linea.split(",");
                    if(partes.length == 3){
                        String nombre = partes[0].trim();
                        String nacimiento = partes[1].trim();
                        String muerte = partes[2].trim();
                        
                        try{
                            Cientifico c = new Cientifico(nombre, nacimiento, muerte);
                            System.out.println("DATOS: " + c);
                        } catch(ParseException e){
                            System.out.println("Error al intentar cargar el archivo");
                        } catch (NoMuertoException ex) {
                            System.out.println("El cientifico esta vivo");
                        }
                    }
                }
            } catch(IOException e){
                System.out.println("Error al leer el fichero" + ruta);
                e.printStackTrace();
            }
         }  
     }
     
    @Override
    public void guardarFichero() {
       
        File path = new File("src/fisicos.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.newLine(); // Salto de línea
            bw.write("fichero actualizado");
            bw.close();
            System.out.println("Texto guardado correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero " + path);
            e.printStackTrace();
        }
    }
    
    public void ordenarListas(){
        
        Comparator<Cientifico> comparadorPorDias = Comparator.comparingInt(Cientifico::getDiasVividos);
        
        Collections.sort(listaInformaticos, comparadorPorDias);
        Collections.sort(listaFisicos, comparadorPorDias);
    }
}
