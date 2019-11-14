package co.nicolaspr.analizadorSemantico;

import java.util.ArrayList;

import co.nicolaspr.analizadorSintactico.Expresion;

/**
 * Estructura de datos que almacena la informacion de los simbolos usados en el
 * programa
 * 
 * @author darwi
 *
 */
public class TablaSimbolos {

	private ArrayList<Simbolo> listaSimbolos;
	private ArrayList<String> listaErrores;

	public TablaSimbolos(ArrayList<String> listaErrores) {
		this.listaSimbolos = new ArrayList<Simbolo>();
		this.listaErrores = listaErrores;
	}

	/**
	 * Permite guardar un simbolo de tipo variable en la tabla de simbolos
	 */
	public Simbolo guardarSimboloVariable(String nombre, String tipo, int fila, int columna, Simbolo ambito,
			Expresion expresion) {

		Simbolo s = buscarSimboloVariable(nombre, ambito, fila, columna);

		if (s == null) {
			Simbolo nuevo = new Simbolo(nombre, tipo, fila, columna, ambito, expresion);
			listaSimbolos.add(nuevo);

			return nuevo;
		} else {
			listaErrores.add("La variable " + nombre + " ya existe en el ambito " + ambito);
		}

		return null;
	}

	/**
	 * Permite guardar un simbolo de tipo funcion en la tabla de simbolos
	 */
	public Simbolo guardarSimboloFuncion(String nombre, String tipo, ArrayList<String> tipoParametros) {

		Simbolo s = buscarSimboloFuncion(nombre, tipoParametros);

		if (s == null) {
			Simbolo nuevo = new Simbolo(nombre, tipo, tipoParametros);
			listaSimbolos.add(nuevo);

			return nuevo;
		} else {
			listaErrores.add("La función " + nombre + " ya existe");
		}

		return null;
	}

	public Simbolo buscarSimboloVariable(String nombre, Simbolo ambito, int fila, int columna) {

		for (Simbolo simbolo : listaSimbolos) {
			if (simbolo.getAmbito() != null) {
				if (nombre.equals(simbolo.getNombre()) && ambito.equals(simbolo.getAmbito())) {
					return simbolo;
				}
			}
		}

		return null;
	}

	public Simbolo buscarSimboloFuncion(String nombre, ArrayList<String> tiposParametros) {

		for (Simbolo simbolo : listaSimbolos) {
			if (simbolo.getTipoParametros() != null) {
				if (nombre.equals(simbolo.getNombre()) && tiposParametros.equals(simbolo.getTipoParametros())) {
					return simbolo;
				}
			}
		}

		return null;
	}

	public ArrayList<Simbolo> getListaSimbolos() {
		return listaSimbolos;
	}

	public void setListaSimbolos(ArrayList<Simbolo> listaSimbolos) {
		this.listaSimbolos = listaSimbolos;
	}

}
