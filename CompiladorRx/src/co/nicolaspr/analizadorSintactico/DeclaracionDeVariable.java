package co.nicolaspr.analizadorSintactico;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
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

}
