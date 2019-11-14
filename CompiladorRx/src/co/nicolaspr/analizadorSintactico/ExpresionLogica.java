package co.nicolaspr.analizadorSintactico;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
/**
 * Esta clase nos ayuda a crear una expresion logica
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class ExpresionLogica extends Expresion{

	private ExpresionRelacional exp1, exp2;
	private Token operador;

	public ExpresionLogica(ExpresionRelacional exp1, ExpresionRelacional exp2, Token operador) {
		super();
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.operador = operador;
	}

	public ExpresionLogica(Token operador, ExpresionRelacional exp1) {
		super();
		this.operador = operador;
		this.exp1 = exp1;
	}

	/**
	 * @return the exp1
	 */
	public ExpresionRelacional getExp1() {
		return exp1;
	}

	/**
	 * @param exp1 the exp1 to set
	 */
	public void setExp1(ExpresionRelacional exp1) {
		this.exp1 = exp1;
	}

	/**
	 * @return the exp2
	 */
	public ExpresionRelacional getExp2() {
		return exp2;
	}

	/**
	 * @param exp2 the exp2 to set
	 */
	public void setExp2(ExpresionRelacional exp2) {
		this.exp2 = exp2;
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

	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Operación Lógica");
		return nodo;
	}

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
