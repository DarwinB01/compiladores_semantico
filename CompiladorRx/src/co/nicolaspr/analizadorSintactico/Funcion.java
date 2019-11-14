package co.nicolaspr.analizadorSintactico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.Simbolo;
import co.nicolaspr.analizadorSemantico.TablaSimbolos;

/**
 * Esta clase nos ayuda a crear funciones
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 *
 *          <Funcion>::=fun<tipoRetorno>identificador"("[<listaParametro>]")""{"[<listaDeSentencia>]"}"
 */
public class Funcion {

	private Token palabraFun, tipoRetorno, identificador, parIzq;
	private ArrayList<Parametro> parametros;
	private Token parDer, llaveIzq;
	private ArrayList<Sentencia> sentencias;
	private Token llaveDer;
	private Simbolo ambito;
	private Expresion expresion;

	public Funcion(Token palabraFun, Token tipoRetorno, Token identificador, Token parIzq,
			ArrayList<Parametro> parametros, Token parDer, Token llaveIzq, ArrayList<Sentencia> sentencias,
			Token llaveDer) {
		super();
		this.palabraFun = palabraFun;
		this.tipoRetorno = tipoRetorno;
		this.identificador = identificador;
		this.parIzq = parIzq;
		this.parametros = parametros;
		this.parDer = parDer;
		this.llaveIzq = llaveIzq;
		this.sentencias = sentencias;
		this.llaveDer = llaveDer;
	}

	@Override
	public String toString() {
		return "Funcion [palabraFun=" + palabraFun + ", tipoRetorno=" + tipoRetorno + ", identificador=" + identificador
				+ ", parIzq=" + parIzq + ", parametros=" + parametros + ", parDre=" + parDer + ", llaveIzq=" + llaveIzq
				+ ", sentencias=" + sentencias + ", llaveDer=" + llaveDer + "]";
	}

	public DefaultMutableTreeNode getArbolVisual() {

		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Funcion");
		raiz.add(new DefaultMutableTreeNode(identificador.getLexema()));

		if (tipoRetorno != null) {
			raiz.add(new DefaultMutableTreeNode("Tipo de retorno: " + tipoRetorno.getLexema()));
		}

		if (parametros != null) {
			DefaultMutableTreeNode parametrosNodo = new DefaultMutableTreeNode("Parametros");
			for (Parametro p : parametros) {
				parametrosNodo.add(p.getArbolVisual());
			}

			raiz.add(parametrosNodo);
		}

		if (sentencias != null) {
			DefaultMutableTreeNode sentenciasNodo = new DefaultMutableTreeNode("Sentencias");

			for (Sentencia se : sentencias) {
				sentenciasNodo.add(se.getArbolVisual());
			}
			raiz.add(sentenciasNodo);
		}

		return raiz;
	}

	public ArrayList<String> getTipoParams() {

		ArrayList<String> lista = new ArrayList<>();

		for (Parametro parametro : parametros) {
			lista.add(parametro.getTipoDato().getLexema());
		}

		return lista;

	}

	public void crearTablaSimbolos(TablaSimbolos tablaSimbolos, ArrayList<String> errores) {
		ambito = tablaSimbolos.guardarSimboloFuncion(palabraFun.getLexema(), tipoRetorno.getLexema(), getTipoParams());

		for (Parametro parametro : parametros) {
			tablaSimbolos.guardarSimboloVariable(parametro.getNombre().getLexema(), parametro.getTipoDato().getLexema(),
					parametro.getNombre().getFila(), parametro.getNombre().getColumna(), ambito, expresion);
		}
		for (Sentencia sentencia : sentencias) {
			sentencia.crearTablaSimbolo(tablaSimbolos, errores, ambito);
		}
	}

	public void analizarSemantica(TablaSimbolos tablaSimbolos, ArrayList<String> errores) {
		for (Sentencia sent : sentencias) {
			sent.analizarSemantica(tablaSimbolos, errores, ambito);
		}

	}

	public String getJavaCode() {

		String tipoR = "void";
		if (tipoRetorno != null) {

			tipoR = tipoRetorno.getJavaCode();
		}

		String codigo = "public  static " + tipoRetorno.getJavaCode() + " " + identificador.getJavaCode() + "(";

		if (parametros.size() > 0) {
			for (Parametro parametro : parametros) {
				codigo += parametro.getJavaCode() + ", ";
			}
			codigo = codigo.substring(0, codigo.length() - 2);
		}
		codigo += "){";

		for (Sentencia sentencia : sentencias) {
			codigo += sentencia.getJavaCode();

		}
		codigo += "}";

		return codigo;
	}

}
