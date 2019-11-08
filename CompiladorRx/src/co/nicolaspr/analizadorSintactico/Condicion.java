package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;
/**
 * Esta clase nos ayuda a crear una condicion
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class Condicion extends Sentencia {
	private Token palabraReservada, parIzq;
	private ExpresionLogica expresionLogica;
	private Token parDer, llaIzq;
	private ArrayList<Sentencia> sentencias;
	private Token llaDer;

	public Condicion(Token palabraReservada, Token parIzq, ExpresionLogica expresionLogica, Token parDer, Token llaIzq,
			ArrayList<Sentencia> sentencias, Token llaDer) {
		super();
		this.palabraReservada = palabraReservada;
		this.parIzq = parIzq;
		this.expresionLogica = expresionLogica;
		this.parDer = parDer;
		this.llaIzq = llaIzq;
		this.sentencias = sentencias;
		this.llaDer = llaDer;
	}

	@Override
	public String toString() {
		return "Condicion [palabraReservada=" + palabraReservada + ", parIzq=" + parIzq + ", expresionLogica="
				+ expresionLogica + ", parDer=" + parDer + ", llaIzq=" + llaIzq + ", sentencias=" + sentencias
				+ ", llaDer=" + llaDer + "]";
	}

	@Override
	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Condicion");

		nodo.add(expresionLogica.getArbolVisual());

		if (sentencias != null) {
			DefaultMutableTreeNode senten = new DefaultMutableTreeNode("Sentencias");
			for (Sentencia sentencia : sentencias) {
				senten.add(sentencia.getArbolVisual());
			}
			nodo.add(senten);
		}else {
			nodo.add(new DefaultMutableTreeNode("Sentencias : Sin sentencias"));
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
