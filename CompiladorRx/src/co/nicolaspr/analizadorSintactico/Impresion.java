package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;
/**
 * Esta clase nos ayuda para crear una impresion, en este caso la forma con la que se le dice al compilador
 * que esto se quiere mostrar en pantalla
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class Impresion extends Sentencia {
	private Token palabrareser, parIzq;
	private Expresion expresion;
	private Token parDer, finSentencia;

	public Impresion(Token palabrareser, Token parIzq, Expresion expresion, Token parDer, Token finSentencia) {
		super();
		this.palabrareser = palabrareser;
		this.parIzq = parIzq;
		this.expresion = expresion;
		this.parDer = parDer;
		this.finSentencia = finSentencia;
	}

	@Override
	public String toString() {
		return "Impresion [palabrareser=" + palabrareser + ", parIzq=" + parIzq + ", expresion=" + expresion
				+ ", parDer=" + parDer + ", finSentencia=" + finSentencia + "]";
	}

	@Override
	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Impresion");
		
		if(expresion != null) {
		nodo.add(expresion.getArbolVisual());
		}else {
			nodo.add(new DefaultMutableTreeNode("Expresion: sin expresion"));
		}

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

}
