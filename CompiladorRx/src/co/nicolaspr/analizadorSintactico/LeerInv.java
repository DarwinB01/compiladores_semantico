package co.nicolaspr.analizadorSintactico;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
/**
 * Esta clase nos ayuda a crear una lectura inversa, en este caso es lo que decimos para que el compilador identifique
 * que eso se quiere ingresar de forma invertida
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */

public class LeerInv extends Sentencia {
	private Token palabraReservada;
	private Token id;
	private Token finSentencia;

	public LeerInv(Token palabraReservada, Token id, Token finSentencia) {
		super();
		this.palabraReservada = palabraReservada;
		this.id = id;
		this.finSentencia = finSentencia;
	}

	@Override
	public String toString() {
		return "Leer [palabraReservada=" + palabraReservada + ", id=" + id + ", finSentencia=" + finSentencia + "]";
	}


	@Override
	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Lectura inversa");

		nodo.add(new DefaultMutableTreeNode("Identificador: " + id.getLexema()));
		return nodo;
	}

}
