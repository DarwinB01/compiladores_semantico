package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

/**
 * Esta clase nos ayuda a crear un ciclo
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class Ciclo extends Sentencia {
	private Token palabraReservada, parIzq;
	private ExpresionLogica expresionLogica;
	private Token parDer, llaveIzq;
	private ArrayList<Sentencia> sentencias;
	private Token llaveDer;

	public Ciclo(Token palabraReservada, Token parIzq, ExpresionLogica expresionLogica, Token parDer, Token llaveIzq,
			ArrayList<Sentencia> sentencias, Token llaveDer) {
		super();
		this.palabraReservada = palabraReservada;
		this.parIzq = parIzq;
		this.expresionLogica = expresionLogica;
		this.parDer = parDer;
		this.llaveIzq = llaveIzq;
		this.sentencias = sentencias;
		this.llaveDer = llaveDer;
	}

	@Override
	public String toString() {
		return "Ciclo [palabraReservada=" + palabraReservada + ", parIzq=" + parIzq + ", expresionLogica="
				+ expresionLogica + ", parDer=" + parDer + ", llaveIzq=" + llaveIzq + ", sentencias=" + sentencias
				+ ", llaveDer=" + llaveDer + "]";
	}

	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("ciclo");

		DefaultMutableTreeNode condicion = new DefaultMutableTreeNode("condicion");

		condicion.add(expresionLogica.getArbolVisual());
		nodo.add(condicion);

		if (sentencias != null) {
			DefaultMutableTreeNode sentenci = new DefaultMutableTreeNode("sentencias");
			for (Sentencia sentencia : sentencias) {
				sentenci.add(sentencia.getArbolVisual());
			}
			nodo.add(sentenci);
		} else {
			nodo.add(new DefaultMutableTreeNode("Sentencias: Sin sentencias"));
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

	@Override
	public String getJavaCode() {
		String codigo = "while (" + expresionLogica.getJavaCode() + "){";

		for (Sentencia sentencia : sentencias) {
			codigo += sentencia.getJavaCode();
		}
		codigo += ")";
		return codigo;
	}

}
