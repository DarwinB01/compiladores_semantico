package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

/**
 * Esta clase nos ayuda a crear una asignacion de variable
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class AsignacionDeVariable extends Sentencia {
	private Token identificador, opAsignacion;
	private Termino termino;
	private Token finSentencia;

	public AsignacionDeVariable(Token identificador, Token opAsignacion, Termino termino, Token finSentencia) {
		super();
		this.identificador = identificador;
		this.opAsignacion = opAsignacion;
		this.termino = termino;
		this.finSentencia = finSentencia;
	}

	@Override
	public String toString() {
		return "AsignacionDeVariable [identificador=" + identificador + ", opAsignacion=" + opAsignacion + ", termino="
				+ termino + ", finSentencia=" + finSentencia + "]";
	}

	@Override
	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Asignacion");
		nodo.add(new DefaultMutableTreeNode("Nombre Variable: " + identificador.getLexema()));
		nodo.add(termino.getArbolVisual());

		return nodo;
	}

	@Override
	protected void crearTablaSimbolo(TablaSimbolos tablaSimbolos, ArrayList<String> errores, Simbolo ambito) {
		tablaSimbolos.guardarSimboloVariable(identificador.getLexema(), null, identificador.getFila(),
				identificador.getColumna(), ambito, ambito.getExpresion());
	}

	@Override
	protected void analizarSemantica(TablaSimbolos tablaSimbolos, ArrayList<String> errores, Simbolo ambito) {

		Simbolo s = tablaSimbolos.buscarSimboloVariable(identificador.getLexema(), ambito, identificador.getFila(),
				identificador.getColumna());
		if (s == null) {
			errores.add("La variable " + identificador.getLexema() + " No ha sido declarado");
		}

	}

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
