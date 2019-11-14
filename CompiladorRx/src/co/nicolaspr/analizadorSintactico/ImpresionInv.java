package co.nicolaspr.analizadorSintactico;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

public class ImpresionInv extends Sentencia {
	private Token palabrareser, parIzq;
	private Expresion expresion;
	private Token parDer, finSentencia;

	public ImpresionInv(Token palabrareser, Token parIzq, Expresion expresion, Token parDer, Token finSentencia) {
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
		DefaultMutableTreeNode nodo = new DefaultMutableTreeNode("Impresion Inversa");
		
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

	@Override
	public String getJavaCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
