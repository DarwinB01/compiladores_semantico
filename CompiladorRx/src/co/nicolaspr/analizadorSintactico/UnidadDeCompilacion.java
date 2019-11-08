package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

/**
 * Esta clase es la principal del analizador sintactico, es decir la raiz para
 * el analisis
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class UnidadDeCompilacion {
	private ArrayList<Funcion> listaFunciones;

	/**
	 * Metodo construtor que debe recibir como parametro una lista de funciones
	 * 
	 * @param listaFunciones
	 */
	public UnidadDeCompilacion(ArrayList<Funcion> listaFunciones) {
		super();
		this.listaFunciones = listaFunciones;
	}

	@Override
	public String toString() {
		return "UnidadDeCompilacion [listaFunciones=" + listaFunciones + "]";
	}

	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Unidad de Compilacion");

		for (Funcion funcion : listaFunciones) {
			raiz.add(funcion.getArbolVisual());
		}

		return raiz;

	}

	public void analizarSemantica(TablaSimbolos tablaSimbolos, ArrayList<String> erroresSemanticos) {

		for (Funcion f : listaFunciones) {
			f.crearTablaSimbolos(tablaSimbolos, erroresSemanticos);
		}

	}

	public void llenarTablaSimbolos(TablaSimbolos tablaSimbolos, ArrayList<String> erroresSemanticos) {

		for (Funcion f : listaFunciones) {
			f.analizarSemantica(tablaSimbolos, erroresSemanticos);
		}

	}

}
