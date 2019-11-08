package co.nicolaspr.analizadorSemantico;

import java.util.ArrayList;

import co.nicolaspr.analizadorSintactico.UnidadDeCompilacion;

/**
 * Clase que representa un Analizador Semantico del compilador
 * 
 * @author darwi
 *
 */
public class AnalizadorSemantico {

	private TablaSimbolos tablaSimbolos;
	private ArrayList<String> erroresSemanticos;
	private UnidadDeCompilacion uc;

	public AnalizadorSemantico(UnidadDeCompilacion uc) {
		this.erroresSemanticos = new ArrayList<String>();
		this.tablaSimbolos = new TablaSimbolos(erroresSemanticos);
		this.uc = uc;
	}

	public void llenarTablaSimbolos() {
		uc.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos);
	}

	public void analizarSemantica() {
		uc.analizarSemantica(tablaSimbolos, erroresSemanticos);
	}

	public TablaSimbolos getTablaSimbolos() {
		return tablaSimbolos;
	}

	public void setTablaSimbolos(TablaSimbolos tablaSimbolos) {
		this.tablaSimbolos = tablaSimbolos;
	}

	public ArrayList<String> getErroresSemanticos() {
		return erroresSemanticos;
	}

	public void setErroresSemanticos(ArrayList<String> erroresSemanticos) {
		this.erroresSemanticos = erroresSemanticos;
	}

}
