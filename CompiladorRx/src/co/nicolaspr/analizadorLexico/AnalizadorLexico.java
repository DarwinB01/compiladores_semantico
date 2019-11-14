package co.nicolaspr.analizadorLexico;

import java.util.ArrayList;

/**
 * Aqui esta todo lo relacionado en cuanto al analizador lexico, desde sus
 * automatas programados, hasta su forma de hacer el analisis
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class AnalizadorLexico {

	private String codigoFuente;
	private int filaActual, columnaActual, posicionActual;
	private char caracterActual, caracterFinDeCodigo;
	private ArrayList<Token> tablaSimbolos, tablaDesconocidos;
	private ArrayList<ErrorLexico> tablaDeErrores;
	private ArrayList<String> palabrasReservadas;

	/**
	 * Contructor del analizador lexico
	 * 
	 * @param codigoFuente es el codigo que codifica la persona, el cual se va a
	 *                     clasificar y analizar
	 */
	public AnalizadorLexico(String codigoFuente) {
		super();
		this.codigoFuente = codigoFuente;
		this.filaActual = 1;
		this.columnaActual = 1;
		this.posicionActual = 0;
		this.caracterActual = this.codigoFuente.charAt(0);
		this.caracterFinDeCodigo = '¿';
		tablaSimbolos = new ArrayList<Token>();
		tablaDesconocidos = new ArrayList<Token>();
		tablaDeErrores = new ArrayList<ErrorLexico>();
		this.palabrasReservadas = new ArrayList<String>();
		llenarPalabrasReservadas();
	}

	/**
	 * Es el metodo principal que me ejecuta el analisis lexico
	 */
	public void analizar() {
		while (caracterActual != caracterFinDeCodigo) {
			if (caracterActual == ' ' || caracterActual == '\n' || caracterActual == '\t' || caracterActual == '\b'
					|| caracterActual == '\f' || caracterActual == '\r') {
				obtenerSiguienteCaracter();
				continue;
			}

			if (esEntero()) {
				continue;
			}
			if (esDecimal()) {
				continue;
			}
			if (esCaracter()) {
				continue;
			}
			if (esCadenaCaracter()) {
				continue;
			}
			if (esOperadorAritmetico()) {
				continue;
			}
			if (esOperadorAsignacion()) {
				continue;
			}
			if (esOperadorIncremento()) {
				continue;
			}
			if (esOperadorDecremento()) {
				continue;
			}
			if (esOperadorRelacional()) {
				continue;
			}
			if (esOperadorLogico()) {
				continue;
			}
			if (esLlaveIzq()) {
				continue;
			}
			if (esLlaveDer()) {
				continue;
			}
			if (esParentesisIzq()) {
				continue;
			}
			if (esParentesisDer()) {
				continue;
			}
			if (esCorcheteIzq()) {
				continue;
			}
			if (esCorcheteDer()) {
				continue;
			}
			if (esSeparador()) {
				continue;
			}
			if (esFinSentencia()) {
				continue;
			}
			if (esComentarioBloque()) {
				continue;
			}
			if (esComentarioLinea()) {
				continue;
			}
			if (esPunto()) {
				continue;
			}
			if (esDosPuntos()) {
				continue;
			}
			if (esIdentificador()) {
				continue;
			}

			tablaDesconocidos
					.add(new Token("" + caracterActual, Categoria.CARACTER_DESCONOCIDO, filaActual, columnaActual));
			obtenerSiguienteCaracter();

		}
	}

	/**
	 * Metodo que me clasifica si un token(lexema), si es un entero
	 * 
	 * @return True si es entero o si me reporta un error, y falso de no serlo o en
	 *         caso de hacer backtracking
	 */
	public boolean esEntero() {
		if (!Character.isDigit(caracterActual)) {
			return false;
		}
		int filaInicio = filaActual, columnaInicio = columnaActual, posicionInicio = posicionActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();

		while (Character.isDigit(caracterActual)) {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
		}
		if (caracterActual == '.') {
			hacerBT(posicionInicio, filaInicio, columnaInicio);
			return false;
		}
		tablaSimbolos.add(new Token(lexema, Categoria.ENTERO, filaActual, columnaInicio));
		return true;
	}

	/**
	 * Metodo que me clasifica si un token(Lexema), si es un numero decimal
	 * 
	 * @return True si es un numero decimal o si se reporta un error, false en caso
	 *         de no serlo o hacer backtracking
	 */
	public boolean esDecimal() {

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";

		if (Character.isDigit(caracterActual)) {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			while (Character.isDigit(caracterActual)) {
				lexema += caracterActual;
				obtenerSiguienteCaracter();
			}
			if (caracterActual != '.') {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			while (Character.isDigit(caracterActual)) {
				lexema += caracterActual;
				obtenerSiguienteCaracter();
			}
			tablaSimbolos.add(new Token(lexema, Categoria.DECIMALES, posInicio, columnaInicio));
			return true;
		}
		if (caracterActual == '.') {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			if (Character.isDigit(caracterActual)) {
				while (Character.isDigit(caracterActual)) {
					lexema += caracterActual;
					obtenerSiguienteCaracter();
				}
			} else {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}
			tablaSimbolos.add(new Token(lexema, Categoria.DECIMALES, filaInicio, columnaInicio));
			return true;
		}
		return false;

	}

	/**
	 * Metodo que me clasifica si un token(Lexema), si es un caracter en el lenguaje
	 * 
	 * @return true en caso de ser un caracter y false en caso de no serlo
	 */
	public boolean esCaracter() {
		if (caracterActual != '_') {
			return false;
		}
		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();

		if (caracterActual == '_') {
			tablaDeErrores.add(new ErrorLexico("No existe el caracter", filaActual, columnaActual));
			return true;
		}
		lexema += caracterActual;
		obtenerSiguienteCaracter();
		if (caracterActual == caracterFinDeCodigo) {
			tablaDeErrores.add(new ErrorLexico("No se cerro el caracter con un _", filaActual, columnaActual));
			return true;
		}

		if (caracterActual != '_') {
			tablaDeErrores.add(
					new ErrorLexico("Un caracter es un solo simbolo y no cerro con un_", filaActual, columnaActual));
			return true;
		}

		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.CARACTER, filaActual, columnaActual));
		obtenerSiguienteCaracter();
		return true;
	}

	/**
	 * Este metodo me clasifica un token(lexema), si es una cadena de caracteres en
	 * el lenguaje
	 * 
	 * @return true si es una cadena de caracteres y false en caso de no serlo
	 */
	public boolean esCadenaCaracter() {
		if (caracterActual != '¡') {
			return false;
		}
		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();
		if (caracterActual == '!') {
			lexema += caracterActual;
			tablaSimbolos.add(new Token(lexema, Categoria.CADENA_CARACTERES, filaActual, columnaActual));
			obtenerSiguienteCaracter();
			return true;
		}
		while (caracterActual != '¡') {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			if (caracterActual == caracterFinDeCodigo) {
				tablaDeErrores.add(new ErrorLexico("No cerro con _", filaActual, columnaActual));
				return true;
			}
		}
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.CADENA_CARACTERES, filaActual, columnaActual));
		obtenerSiguienteCaracter();
		return true;
	}

	/**
	 * Este metodo sirve para clasificar un token(lexema) como un operador
	 * aritmetico
	 * 
	 * @return True en caso de serlo y falso en caso de no serlo o de hacer
	 *         backtracking
	 */
	public boolean esOperadorAritmetico() {

		if (caracterActual != '+' && caracterActual != '-' && caracterActual != '/' && caracterActual != '*'
				&& caracterActual != '%') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;

		if (caracterActual == '+') {

			obtenerSiguienteCaracter();

			if (caracterActual == '+' || caracterActual == '=') {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}

			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_ARITMETICO, filaActual, columnaActual));
			return true;

		}
		if (caracterActual == '-') {

			obtenerSiguienteCaracter();

			if (caracterActual == '-' || caracterActual == '=') {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}

			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_ARITMETICO, filaActual, columnaActual));
			return true;

		}
		if (caracterActual == '/') {
			obtenerSiguienteCaracter();
			if (caracterActual == '*' || caracterActual == '=') {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_ARITMETICO, filaActual, columnaActual));
			return true;
		}

		obtenerSiguienteCaracter();

		if (caracterActual == '=') {
			hacerBT(posInicio, filaInicio, columnaInicio);
			return false;
		}
		tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_ARITMETICO, filaActual, columnaActual));
		return true;

	}

	/**
	 * Este metodo sirve para clasificar un token(lexema) en un operador de
	 * asignacion
	 * 
	 * @return true en caso de serlo y falso en caso de no serlo o de hacer
	 *         backtracking
	 */
	public boolean esOperadorAsignacion() {

		if (caracterActual != '+' && caracterActual != '-' && caracterActual != '/' && caracterActual != '*'
				&& caracterActual != '%' && caracterActual != '=') {
			return false;
		}
		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		if (caracterActual == '=') {

			obtenerSiguienteCaracter();
			if (caracterActual == '=') {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_ASIGNACION, filaActual, columnaActual));
			return true;
		}

		obtenerSiguienteCaracter();

		if (caracterActual != '=') {

			hacerBT(posInicio, filaInicio, columnaInicio);
			return false;

		}

		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_ASIGNACION, filaActual, columnaActual));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un operador de
	 * incremento
	 * 
	 * @return True si es un operador de incremento y falso de no serlo o en caso de
	 *         hacer backtracking
	 */
	public boolean esOperadorIncremento() {

		if (caracterActual != '+') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();

		if (caracterActual == '+') {
			lexema += caracterActual;
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_INCREMENTO, filaActual, columnaActual));
			obtenerSiguienteCaracter();
			return true;
		}

		hacerBT(posInicio, filaInicio, columnaInicio);
		return false;
	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un operador de
	 * incremento
	 * 
	 * @return True si es un operador de incremento y falso de no serlo o en caso de
	 *         hacer backtracking
	 */

	public boolean esOperadorDecremento() {

		if (caracterActual != '-') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();

		if (caracterActual == '-') {
			lexema += caracterActual;
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_DECREMENTO, filaActual, columnaActual));
			obtenerSiguienteCaracter();
			return true;
		}

		hacerBT(posInicio, filaInicio, columnaInicio);
		return false;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un operador
	 * relacional
	 * 
	 * @return True si es un operador relacional y falso de no serlo o en caso de
	 *         hacer backtracking
	 */

	public boolean esOperadorRelacional() {

		if (caracterActual != '!' && caracterActual != '=' && caracterActual != '<' && caracterActual != '>') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;

		if (caracterActual == '<' || caracterActual == '>') {
			obtenerSiguienteCaracter();
			if (caracterActual == '=') {
				lexema += caracterActual;
				tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_RELACIONAL, filaActual, columnaActual));
				obtenerSiguienteCaracter();
				return true;
			}
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_RELACIONAL, filaActual, columnaActual));
			return true;

		}

		obtenerSiguienteCaracter();
		if (caracterActual == '=') {

			lexema += caracterActual;
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_RELACIONAL, filaActual, columnaActual));
			obtenerSiguienteCaracter();
			return true;

		}
		hacerBT(posInicio, filaInicio, columnaInicio);
		return false;
	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un operador de
	 * incremento
	 * 
	 * @return True si es un operador de incremento y falso de no serlo o en caso de
	 *         hacer backtracking
	 */
	public boolean esOperadorLogico() {
		if (caracterActual != '!' && caracterActual != '&' && caracterActual != '|') {
			return false;
		}
		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		if (caracterActual == '!') {
			obtenerSiguienteCaracter();
			if (caracterActual == '=') {
				hacerBT(posInicio, filaInicio, columnaInicio);
				return false;
			}
			tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_LOGICO, filaActual, columnaActual));
			obtenerSiguienteCaracter();
			return true;
		}
		if (caracterActual == '&') {
			obtenerSiguienteCaracter();
			if (caracterActual == '&') {
				lexema += caracterActual;
				tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_LOGICO, filaActual, columnaActual));
				obtenerSiguienteCaracter();
				return true;
			}
			hacerBT(posInicio, filaInicio, columnaInicio);
			return false;
		}
		if (caracterActual == '|') {
			obtenerSiguienteCaracter();
			if (caracterActual == '|') {
				lexema += caracterActual;
				tablaSimbolos.add(new Token(lexema, Categoria.OPERADOR_LOGICO, filaActual, columnaActual));
				obtenerSiguienteCaracter();
				return true;

			}
			hacerBT(posInicio, filaInicio, columnaInicio);
		}
		return false;
	}

	/**
	 * Este metodo me ayuda a clasificar un caracter como una letra o no
	 * 
	 * @return True si es un operador una letra y falso de no serlo
	 */

	public boolean esLetra(char a) {
		if ((a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z')) {
			return true;
		}
		return false;
	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como una llave izquierda
	 * 
	 * @return True si es una llave izquierda y falso de no serlo
	 */

	public boolean esLlaveIzq() {

		if (caracterActual != '{') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.LLAVE_IZQ, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como una llave derecha
	 * 
	 * @return True si es una llave derecha y falso de no serlo
	 */

	public boolean esLlaveDer() {

		if (caracterActual != '}') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.LLAVE_DER, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un corchete
	 * izquierdo
	 * 
	 * @return True si es un corchete izquierdo y falso de no serlo
	 */

	public boolean esCorcheteIzq() {

		if (caracterActual != '[') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.CORCHETE_IZQ, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un corchete derecho
	 * 
	 * @return True si es un corchete derecho y falso de no serlo
	 */

	public boolean esCorcheteDer() {

		if (caracterActual != ']') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.CORCHETE_DER, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un parentesis
	 * izquierdo
	 * 
	 * @return True si es un parentesis izquierdo y falso de no serlo
	 */

	public boolean esParentesisIzq() {

		if (caracterActual != '(') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.PARENTESIS_IZQ, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un parentesis
	 * derecho
	 * 
	 * @return True si es un parentesis derecho y falso de no serlo
	 */

	public boolean esParentesisDer() {

		if (caracterActual != ')') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.PARENTESIS_DER, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un separador
	 * 
	 * @return True si es una coma y falso de no serlo
	 */

	public boolean esSeparador() {
		if (caracterActual != ',') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.SEPARADOR, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un fin de sentencia
	 * 
	 * @return True si es um punto y coma, y falso de no serlo
	 */

	public boolean esFinSentencia() {

		if (caracterActual != ';') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.FIN_SENTENCIA, filaInicio, columnaInicio));
		obtenerSiguienteCaracter();
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un cometario de
	 * linea
	 * 
	 * @return True si es un comentario de linea y falso de no serlo
	 */

	public boolean esComentarioLinea() {

		if (caracterActual != '$') {
			return false;
		}
		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();
		while (caracterActual != '\n' && caracterActual != caracterFinDeCodigo) {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
		}
		tablaSimbolos.add(new Token(lexema, Categoria.COMENTARIO_LINEA, filaActual, columnaActual));
		return true;
	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un cometariop de
	 * bloque
	 * 
	 * @return True si es un comentario de bloque o de registrar un error y falso de
	 *         no serlo o en caso de presentarse un caso de backtracking
	 */

	public boolean esComentarioBloque() {

		if (caracterActual != '/') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();

		if (caracterActual != '*') {
			hacerBT(posInicio, filaInicio, columnaInicio);
			return false;
		}

		lexema += caracterActual;
		obtenerSiguienteCaracter();
		while (caracterActual != '*') {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			if (caracterActual == caracterFinDeCodigo) {
				tablaDeErrores
						.add(new ErrorLexico("ERROR: DEBE CERRAR EL COMENTARIO '*/' ", filaActual, columnaActual));
				return true;
			}
		}
		lexema += caracterActual;
		obtenerSiguienteCaracter();
		while (caracterActual != '/') {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			if (caracterActual == caracterFinDeCodigo) {
				tablaDeErrores
						.add(new ErrorLexico("ERROR: DEBE CERRAR EL COMENTARIO '*/' ", filaActual, columnaActual));
				return true;
			}

		}
		lexema += caracterActual;
		tablaSimbolos.add(new Token(lexema, Categoria.COMENTARIO_BLOQUE, filaActual, columnaActual));
		obtenerSiguienteCaracter();
		return true;
	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un punto
	 * 
	 * @return True si es un punto y falso de no serlo o presentarse un caso de
	 *         backtracking
	 */

	public boolean esPunto() {

		if (caracterActual != '.') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();

		if (Character.isDigit(caracterActual)) {
			hacerBT(posInicio, filaInicio, columnaInicio);
			return false;
		}
		tablaSimbolos.add(new Token(lexema, Categoria.PUNTO, filaActual, columnaActual));
		return true;

	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un dos puntos
	 * 
	 * @return True si es un dos puntos y falso de no serlo
	 */

	public boolean esDosPuntos() {

		if (caracterActual != ':') {
			return false;
		}

		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();
		tablaSimbolos.add(new Token(lexema, Categoria.DOS_PUNTOS, filaActual, columnaActual));
		return true;

	}

	/**
	 * Metodo para llenar la lista de las palabras reservadas
	 * Agregar palabra impresion
	 */
	public void llenarPalabrasReservadas() {
		palabrasReservadas.add("si");// if
		palabrasReservadas.add("logico");// boolean
		palabrasReservadas.add("contrario");// else
		palabrasReservadas.add("entero");// int
		palabrasReservadas.add("cadena"); // String
		palabrasReservadas.add("decimal");// double
		palabrasReservadas.add("fun");// Funcion
		palabrasReservadas.add("retornar");// return
		palabrasReservadas.add("vacio");// Void
		palabrasReservadas.add("por");// for
		palabrasReservadas.add("mientras");// while
		palabrasReservadas.add("romper");// break
		palabrasReservadas.add("leer");// lectura
		palabrasReservadas.add("imprimir");// impresion
		palabrasReservadas.add("verdadero");
		palabrasReservadas.add("falso");
		//inventadas
		palabrasReservadas.add("leerInv");// lectura inversa
		palabrasReservadas.add("imprimirInv");// impresion inversa

	}

	/**
	 * Metodo para verificar si una palabra encontada es una palabra reservada
	 * 
	 * @param lexema la palabra a verificar si esta contenida o no en la lista de
	 *               palabras reservadas
	 * @return true si es una plabra reservada y false de no serlo
	 */
	public boolean esPalabraReservada(String lexema) {
		return palabrasReservadas.contains(lexema);
	}

	/**
	 * Este metodo me ayuda a clasificar un token(lexema), como un identificador o
	 * una palabra reservada
	 * 
	 * @return True si es un identificador o una palabra reservada y falso de no
	 *         serlo
	 */

	public boolean esIdentificador() {
		if (!esLetra(caracterActual)) {
			return false;
		}
		int posInicio = posicionActual, filaInicio = filaActual, columnaInicio = columnaActual;
		String lexema = "";
		lexema += caracterActual;
		obtenerSiguienteCaracter();
		while (esLetra(caracterActual)) {
			lexema += caracterActual;
			obtenerSiguienteCaracter();
			if (esPalabraReservada(lexema)) {
				tablaSimbolos.add(new Token(lexema, Categoria.PALABRA_RESERVADA, filaActual, columnaActual));
				return true;
			}

		}
		tablaSimbolos.add(new Token(lexema, Categoria.IDENTIFICADOR, filaActual, columnaActual));
		return true;

	}

	/**
	 * Metodo que me ayuda a regresar a una posicion inicial antes de arracar el
	 * analisis de cualquier automata
	 * 
	 * @param posicionInicio Posicion a la cual se va a regresar en el arreglo
	 * @param filaInicio     Fila a la cual se va a regresar
	 * @param columnaInicio  columna a la cual se va a regresar
	 */
	private void hacerBT(int posicionInicio, int filaInicio, int columnaInicio) {
		posicionActual = posicionInicio;
		filaActual = filaInicio;
		columnaActual = columnaInicio;
		caracterActual = codigoFuente.charAt(posicionInicio);

	}

	/**
	 * Metopdo que me ayuda a recorrer el codigo fuente de una forma ordenada
	 */
	private void obtenerSiguienteCaracter() {

		if (posicionActual == codigoFuente.length() - 1) {
			caracterActual = caracterFinDeCodigo;
		} else {
			if (caracterActual == '\n') {
				filaActual++;
				columnaActual = 1;
			} else {
				columnaActual++;
			}
			posicionActual++;
			caracterActual = codigoFuente.charAt(posicionActual);
		}
	}

	/**
	 * Metodo que me ayuda a acceder a la informacion de la lista de simbolos
	 * 
	 * @return la lista de simbolos
	 */
	public ArrayList<Token> getTablaSimbolos() {
		return tablaSimbolos;
	}

	/**
	 * metodo que me ayuda a modificar o actualizar la informacion de la lista de
	 * simbolos
	 * 
	 * @param tablaSimbolos tabla modificada o a actualizar
	 */
	public void setTablaSimbolos(ArrayList<Token> tablaSimbolos) {
		this.tablaSimbolos = tablaSimbolos;
	}

	/**
	 * metodo que me ayuda a acceder a la lista de simbolos desconocidos
	 * 
	 * @return lista de simbolos desconocidos
	 */
	public ArrayList<Token> getTablaDesconocidos() {
		return tablaDesconocidos;
	}

	/**
	 * metodo que me ayuda a modificar o actualizar la informacion de la lista de
	 * simbolos desconocidos
	 * 
	 * @param tablaErrores tabla modificada o a actualizar
	 */
	public void setTablaDesconocidos(ArrayList<Token> tablaErrores) {
		this.tablaDesconocidos = tablaErrores;
	}

	/**
	 * metodo que me ayuda a acceder a la lista de errores
	 * 
	 * @return lista de errores
	 */
	public ArrayList<ErrorLexico> getTablaDeErrores() {
		return tablaDeErrores;
	}

	/**
	 * Metodo para modificar la lista de errores desde fuera de la clase de
	 * analizador lexico
	 * 
	 * @param tablaDeErrores tabla modificada a ingresar o actualizar
	 */
	public void setTablaDeErrores(ArrayList<ErrorLexico> tablaDeErrores) {
		this.tablaDeErrores = tablaDeErrores;
	}

}
