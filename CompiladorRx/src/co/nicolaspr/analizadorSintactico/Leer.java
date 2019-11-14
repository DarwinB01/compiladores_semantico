package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;
/**
 * Esta clase nos ayuda a crear una lectura, en este caso es lo que decimos para que el compilador identifique
 * que eso se quiere ingresar
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class Leer extends Sentencia {
	private Token palabraReservada;
	private Token id;
	private Token finSentencia;

	public Leer(Token palabraReservada, Token id, Token finSentencia) {
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

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Lectura");

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
