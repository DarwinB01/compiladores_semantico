package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;

public class InvocacionFuncion extends Sentencia {
	private Token punto, identifi, parIzq;
	private ArrayList<Argumento> argumentos;
	private Token parDer, finSentencia;

	public InvocacionFuncion(Token punto, Token identifi, Token parIzq, ArrayList<Argumento> argumentos, Token parDer,
			Token finSentencia) {
		super();
		this.punto = punto;
		this.identifi = identifi;
		this.parIzq = parIzq;
		this.argumentos = argumentos;
		this.parDer = parDer;
		this.finSentencia = finSentencia;
	}

	@Override
	public String toString() {
		return "InvocacionFuncion [punto=" + punto + ", identifi=" + identifi + ", parIzq=" + parIzq + ", argumentos="
				+ argumentos + ", parDer=" + parDer + ", finSentencia=" + finSentencia + "]";
	}

	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Invocaci�n");
		nodo.add(new DefaultMutableTreeNode("Nombre funci�n: " + identifi.getLexema()));

		if (argumentos != null) {
			DefaultMutableTreeNode argu = new DefaultMutableTreeNode("Argumentos");

			for (Argumento argumento : argumentos) {
				argu.add(argumento.getArbolVisual());
			}
			nodo.add(argu);
		} else {

			nodo.add(new DefaultMutableTreeNode("Argumentos: Sin argumentos "));
		}

		return nodo;
	}

	/**
	 * @return the argumentos
	 */
	public ArrayList<Argumento> getArgumentos() {
		return argumentos;
	}

	/**
	 * @param argumentos the argumentos to set
	 */
	public void setArgumentos(ArrayList<Argumento> argumentos) {
		this.argumentos = argumentos;
	}

}
