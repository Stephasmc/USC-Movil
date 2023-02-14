package smillan.principal;

import java.util.Scanner;

import smillan.principal.Implementaciones.Dividir;
import smillan.principal.Implementaciones.Multiplicar;
import smillan.principal.Implementaciones.Restar;
import smillan.principal.Implementaciones.Sumar;
import smillan.principal.interfac.OperacionInterface;

public class Menu {
	
	Scanner sc = new Scanner(System.in);
	
	public void mostrarMenu()
	{
		System.out.println("Digite el numero uno: ");
		double numUno = sc.nextDouble();
		
		System.out.println("Digite el numero dos: ");
		double numDos = sc.nextDouble();
		
		System.out.println("Que operacion desea hacer: ");
		int seleccion = sc.nextInt();
		
		
		Sumar sm = new Sumar();
		Restar rt = new Restar();
		Multiplicar mt = new Multiplicar();
		Dividir dv = new Dividir();
		
		switch (seleccion) {
		case 1: System.out.println(sm.sumar(numUno, numDos));
			break;
			
		case 2: System.out.println(rt.restar(numUno, numDos));
			break;
			
		case 3: System.out.println(mt.multiplicar(numUno, numDos));
			break;
		
		case 4: System.out.println(dv.dividir(numUno, numDos));
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + seleccion);
		}
	}

}
