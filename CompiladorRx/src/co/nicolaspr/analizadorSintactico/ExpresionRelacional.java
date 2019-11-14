package co.nicolaspr.analizadorSintactico;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;

/**
 * Esta clase nos ayuda a una expresion relacional
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class ExpresionRelacional extends Expresion {

	private ExpresionAritmetica termino1, termino2;
	private Token operador, termino;

	public ExpresionRelacional(ExpresionAritmetica termino1, Token operador, ExpresionAritmetica termino2) {
		super();
		this.termino1 = termino1;
		this.termino2 = termino2;
		this.operador = operador;

	}

	public ExpresionRelacional(Token termino) {
		this.termino = termino;
	}

	@Override
	public String toString() {
		return "ExpresionRelacional [termino1=" + termino1 + ", operador=" + operador + ", termino2=" + termino2 + "]";
	}

	/**
	 * @return the termino1
	 */
	public ExpresionAritmetica getTermino1() {
		return termino1;
	}

	/**
	 * @param termino1 the termino1 to set
	 */
	public void setTermino1(ExpresionAritmetica termino1) {
		this.termino1 = termino1;
	}

	/**
	 * @return the termino2
	 */
	public ExpresionAritmetica getTermino2() {
		return termino2;
	}

	/**
	 * @param termino2 the termino2 to set
	 */
	public void setTermino2(ExpresionAritmetica termino2) {
		this.termino2 = termino2;
	}

	/**
	 * @return the operador
	 */
	public Token getOperador() {
		return operador;
	}

	/**
	 * @param operador the operador to set
	 */
	public void setOperador(Token operador) {
		this.operador = operador;
	}

	/**
	 * @return the termino
	 */
	public Token getTermino() {
		return termino;
	}

	/**
	 * @param termino the termino to set
	 */
	public void setTermino(Token termino) {
		this.termino = termino;
	}

	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Expresión Relacional");

		return nodo;
	}

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
