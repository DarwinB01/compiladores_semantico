package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

public class Arreglo extends Sentencia {

	private Token tipo, identificador;
	private ArrayList<Argumento> listaArgumentos;

	public Arreglo(Token tipo, Token identificador, ArrayList<Argumento> listaArgumentos) {
		super();
		this.tipo = tipo;
		this.identificador = identificador;
		this.listaArgumentos = listaArgumentos;
	}

	/**
	 * @return the tipo
	 */
	public Token getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Token tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the identificador
	 */
	public Token getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(Token identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the listaArgumentos
	 */
	public ArrayList<Argumento> getListaArgumentos() {
		return listaArgumentos;
	}

	/**
	 * @param listaArgumentos the listaArgumentos to set
	 */
	public void setListaArgumentos(ArrayList<Argumento> listaArgumentos) {
		this.listaArgumentos = listaArgumentos;
	}

	public DefaultMutableTreeNode getArbolVisual() {
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Arreglo");

		raiz.add(new DefaultMutableTreeNode("Tipo: " + tipo.getLexema()));
		raiz.add(new DefaultMutableTreeNode("Identificador: " + identificador.getLexema()));

		DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode("Lista Argumentos");

		for (Argumento argumento : listaArgumentos) {

			nodoHijo.add(argumento.getArbolVisual());
		}
		raiz.add(nodoHijo);
		return raiz;
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
