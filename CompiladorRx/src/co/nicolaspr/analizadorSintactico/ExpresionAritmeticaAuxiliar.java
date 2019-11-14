package co.nicolaspr.analizadorSintactico;

import co.nicolaspr.analizadorLexico.Token;
/**
 * Esta clase nos sirve para la creacion de Una expresion aritmetica, de distinta forma a la aritmrtica
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class ExpresionAritmeticaAuxiliar extends Expresion {

	private Token operador;
	private ExpresionAritmetica eA;
	private ExpresionAritmeticaAuxiliar eAux;

	public ExpresionAritmeticaAuxiliar(Token operador, ExpresionAritmetica eA, ExpresionAritmeticaAuxiliar eAux) {
		super();
		this.operador = operador;
		this.eA = eA;
		this.eAux = eAux;
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
	 * @return the eA
	 */
	public ExpresionAritmetica geteA() {
		return eA;
	}

	/**
	 * @param eA the eA to set
	 */
	public void seteA(ExpresionAritmetica eA) {
		this.eA = eA;
	}

	/**
	 * @return the eAux
	 */
	public ExpresionAritmeticaAuxiliar geteAux() {
		return eAux;
	}

	/**
	 * @param eAux the eAux to set
	 */
	public void seteAux(ExpresionAritmeticaAuxiliar eAux) {
		this.eAux = eAux;
	}

	@Override
	public String toString() {
		return "ExpresionAritmeticaAuxiliar [operador=" + operador + ", eA=" + eA + ", eAux=" + eAux + "]";
	}

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
