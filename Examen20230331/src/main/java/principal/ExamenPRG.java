package principal;

import dominio.BaseDeDatos;
import dominio.Fisico;
import dominio.Informatico;
import excepciones.NoMuertoException;
import java.text.ParseException;

public class ExamenPRG {

	public static void main(String[] args) throws ParseException, NoMuertoException {

		//1.- Crear cientificos
		BaseDeDatos bdd = new BaseDeDatos("src/informaticos.txt");

		//2.- Cargar cientificos desde fichero
		bdd.cargarFichero();
		bdd.cargarFichero("src/fisicos.txt");
                
                // 3.a- Calcular los dias que vivieron los informaticos
		for (Informatico i : bdd.listaInformaticos) {
			i.calcular();
		}
		
		//3.b- Calcular los dias que vivieron los fisicos
		for (Fisico f : bdd.listaFisicos) {
			f.calcular();
		}
		
		//4.- Ordenar las listas de informaticos y f�sicos por dias vividos (de menos a m�s)
		bdd.ordenarListas();
		
		//5.a- Imprimir nombre del informatico junto con los dias vividos
		System.out.println("Informaticos:");
		for (Informatico i : bdd.listaInformaticos) {
			System.out.println(i);
		}	
		
		//5.b- Imprimir nombre del fisico junto con los dias vividos
		System.out.println("Fisicos:");
		for (Fisico f : bdd.listaFisicos) {
			System.out.println(f);
		}

		//6.- Guardar en fichero, primero los inform�ticos y luego los f�sicos
		bdd.guardarFichero();		
	}
}