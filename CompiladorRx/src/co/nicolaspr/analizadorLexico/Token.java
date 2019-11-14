package co.nicolaspr.analizadorLexico;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * clase token, que me define un token en si, constando de su lexema,categoria,
 * y fila y columna donde se encontro
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 *
 */
public class Token {
	private String lexema;
	private int fila, columna;
	private Categoria categoria;

	public Token(String lexema, Categoria categoria, int fila, int columna) {
		this.lexema = lexema;
		this.categoria = categoria;
		this.fila = fila;
		this.columna = columna;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Token [lexema=" + lexema + ", fila=" + fila + ", columna=" + columna + ", categoria=" + categoria + "]";
	}

	public DefaultMutableTreeNode getArbolVisual() {
		return null;
	}

	public String getJavaCode() {

		if (categoria == Categoria.PALABRA_RESERVADA) {
			if (lexema.equals("entero")) {
				return "int";
			}
			if (lexema.equals("decimal")) {
				return "double";
			}
			if (lexema.equals("vacio")) {
				return "void";
			}
			if (lexema.equals("logico")) {
				return "boolean";
			}
		} else if (categoria == Categoria.CADENA_CARACTERES) {
			return lexema.replaceAll("¡", "\"");
		}
		return lexema;
	}
}
