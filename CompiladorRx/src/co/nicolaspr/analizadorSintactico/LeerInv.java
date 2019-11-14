package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;
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

	@Override
	protected void crearTablaSimbolo(TablaSimbolos tablaSimbolos, ArrayList<String> errores, Simbolo ambito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void analizarSemantica(TablaSimbolos tablaSimbolos, ArrayList<String> errores, Simbolo ambito) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
