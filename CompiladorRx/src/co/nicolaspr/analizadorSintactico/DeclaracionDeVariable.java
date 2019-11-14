package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

/**
 * Esta clase nos ayuda a crear una declaracion de variable
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class DeclaracionDeVariable extends Sentencia {
	private Token tipoDato, identificador, finSentencia;

	public DeclaracionDeVariable(Token tipoDato, Token identificador, Token finSentencia) {
		super();
		this.tipoDato = tipoDato;
		this.identificador = identificador;
		this.finSentencia = finSentencia;
	}

	@Override
	public String toString() {
		return "DeclaracionDeVariable [tipoDato=" + tipoDato + ", identificador=" + identificador + ", finSentencia="
				+ finSentencia + "]";
	}

	@Override
	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Declaracion");
		nodo.add(new DefaultMutableTreeNode("Tipo Dato: " + tipoDato.getLexema()));
		nodo.add(new DefaultMutableTreeNode("Nombre Variable: " + identificador.getLexema()));

		return nodo;
	}

	@Override
	protected void crearTablaSimbolo(TablaSimbolos tablaSimbolos, ArrayList<String> errores, Simbolo ambito) {
		tablaSimbolos.guardarSimboloVariable(identificador.getLexema(), tipoDato.getLexema(), identificador.getFila(),
				identificador.getColumna(), ambito, ambito.getExpresion());
	}

	@Override
	protected void analizarSemantica(TablaSimbolos tablaSimbolos, ArrayList<String> errores, Simbolo ambito) {

		Simbolo s = tablaSimbolos.buscarSimboloVariable(identificador.getLexema(), ambito, identificador.getFila(),
				identificador.getColumna());

		if (s != null) {
			errores.add("La variable " + identificador.getLexema() + " ya existe");
		}

	}

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
