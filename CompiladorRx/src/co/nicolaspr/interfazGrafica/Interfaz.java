package co.nicolaspr.interfazGrafica;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import co.nicolaspr.analizadorLexico.AnalizadorLexico;
import co.nicolaspr.analizadorLexico.ErrorLexico;
import co.nicolaspr.analizadorLexico.Token;
import co.nicolaspr.analizadorSemantico.AnalizadorSemantico;
import co.nicolaspr.analizadorSintactico.AnalizadorSintactico;
import co.nicolaspr.analizadorSintactico.ErrorSintactico;
import co.nicolaspr.analizadorSintactico.UnidadDeCompilacion;

/**
 * Clase de la interfaz principal del programa
 * 
 * @author Darwin Bonilla, Nicolas Rios y Santiago Vargas
 * @version 1.0.0
 */
public class Interfaz extends JFrame implements ActionListener {

	private JTextArea jArea;
	private JButton btnAnalizar, btnLimpiar;
	private AnalizadorLexico analizador;
	private JTable tablaSimbolos, tablaDesconocidos, tablaErrores;
	private JScrollPane scrollTSimbolos, scrollTDesconocidos, scrollTErrores, scrollSintactico, scrollPestanias,
			scrollPestanias2, scrollSemantico;
	private DefaultTableModel modelo, modeloDesconocido, modeloError;
	private ArrayList<Token> lista;
	private ArrayList<ErrorLexico> listaError;
	private ArrayList<ErrorSintactico> listaErrorS;
	private JPanel contentPane;
	private JTree arbolVisual;
	private AnalizadorSintactico analizadorSintactico;
	private JTabbedPane pestanias, pestanias2;
	private AnalizadorSemantico analizadorSemantico;

	/**
	 * Costructor de la clase, se inicializan todos los componentes
	 */
	public Interfaz() {

		setLayout(null);
		setSize(800, 650);
		setLocationRelativeTo(null);
		setTitle("Analizador Lexico");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jArea = new JTextArea();
		jArea.setBounds(20, 90, 300, 300);
		contentPane.add(jArea);
		jArea.setText("fun vacio prueba(){ \n }");

		modelo = new DefaultTableModel();
		modeloDesconocido = new DefaultTableModel();
		modeloError = new DefaultTableModel();
		tablaSimbolos = new JTable(modelo);
		tablaDesconocidos = new JTable(modeloDesconocido);
		tablaErrores = new JTable(modeloError);

		modelo.addColumn("LEXEMA");
		modelo.addColumn("CATEGORIA");
		modelo.addColumn("FILA");
		modelo.addColumn("COLUMNA");

		modeloDesconocido.addColumn("LEXEMA");
		modeloDesconocido.addColumn("CATEGORIA");
		modeloDesconocido.addColumn("FILA");
		modeloDesconocido.addColumn("COLUMNA");

		modeloError.addColumn("MENSAJE");
		modeloError.addColumn("FILA");
		modeloError.addColumn("COLUMNA");

		JLabel lblValidos = new JLabel("TABLA DE SIMBOLOS VALIDOS");
		lblValidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidos.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblValidos.setBounds(277, 15, 524, 32);

		/**
		 * Componentes tabla de simbolos
		 */
		tablaSimbolos.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		tablaSimbolos.setPreferredScrollableViewportSize(new Dimension(450, 63));
		tablaSimbolos.setFillsViewportHeight(true);

		scrollTSimbolos = new JScrollPane(tablaSimbolos);
		scrollTSimbolos.setBounds(350, 60, 400, 100);
		scrollTSimbolos.setVisible(true);
		contentPane.add(scrollTSimbolos);

		JLabel lblDesconocidos = new JLabel("TABLA DE SIMBOLOS DESCONOCIDOS");
		lblDesconocidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesconocidos.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDesconocidos.setBounds(277, 160, 524, 32);

		/**
		 * componentes tabla de desconocidos
		 */
		tablaDesconocidos.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		tablaDesconocidos.setPreferredScrollableViewportSize(new Dimension(450, 63));
		tablaDesconocidos.setFillsViewportHeight(true);

		scrollTDesconocidos = new JScrollPane(tablaDesconocidos);
		scrollTDesconocidos.setBounds(350, 200, 400, 100);
		scrollTDesconocidos.setVisible(true);
		contentPane.add(scrollTDesconocidos);

		JLabel lblErrores = new JLabel("TABLA DE ERRORES");
		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblErrores.setBounds(277, 305, 524, 32);

		/**
		 * componentes tabla de errores
		 */
		tablaErrores.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		tablaErrores.setPreferredScrollableViewportSize(new Dimension(450, 63));
		tablaErrores.setFillsViewportHeight(true);

		scrollTErrores = new JScrollPane(tablaErrores);
		scrollTErrores.setBounds(350, 345, 400, 100);
		scrollTErrores.setVisible(true);
		contentPane.add(scrollTErrores);

		/**
		 * Arbol visual
		 */
		arbolVisual = new JTree();
		scrollSintactico = new JScrollPane(arbolVisual);
		scrollSintactico.setBounds(400, 20, 300, 380);
		contentPane.add(scrollSintactico);

		scrollSemantico = new JScrollPane();
		scrollSemantico.setBounds(400, 20, 300, 380);
		contentPane.add(scrollSemantico);

		/**
		 * Pestañas
		 */

		pestanias = new JTabbedPane();
		pestanias.add("TABLA DE SIMBOLOS VALIDOS", scrollTSimbolos);
		pestanias.add("TABLA DE SIMBOLOS DESCONOCIDOS", scrollTDesconocidos);
		pestanias.add("TABLA DE ERRORES", scrollTErrores);

		scrollPestanias = new JScrollPane(pestanias);
		scrollPestanias.setBounds(20, 420, 600, 150);
		scrollPestanias.setVisible(true);
		contentPane.add(scrollPestanias);

		pestanias2 = new JTabbedPane();
		pestanias2.add("ARBOL SINTACTICO", scrollSintactico);
		pestanias2.add("ERRORES SEMANTICOS", scrollSemantico);

		scrollPestanias2 = new JScrollPane(pestanias2);
		scrollPestanias2.setBounds(400, 20, 340, 380);
		scrollPestanias2.setVisible(true);
		contentPane.add(scrollPestanias2);

		/**
		 * Botones
		 */
		btnAnalizar = new JButton("Analizar");
		btnAnalizar.setBounds(60, 30, 100, 30);
		btnAnalizar.addActionListener(this);
		contentPane.add(btnAnalizar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(190, 30, 100, 30);
		btnLimpiar.addActionListener(this);
		contentPane.add(btnLimpiar);

	}

	/**
	 * Metodo donde se le dan las acciones a los botones de la interfaz
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnLimpiar) {
			jArea.setText("");
			modelo.setRowCount(0);
			modeloDesconocido.setRowCount(0);
			modeloError.setRowCount(0);
		}
		if (e.getSource() == btnAnalizar) {
			if (!jArea.getText().trim().equals("")) {
				analizador = new AnalizadorLexico(jArea.getText());
				analizador.analizar();

				analizadorSintactico = new AnalizadorSintactico(analizador.getTablaSimbolos());
				analizadorSintactico.analizar();

				arbolVisual
						.setModel(new DefaultTreeModel(analizadorSintactico.getUnidadDeCompilacion().getArbolVisual()));

//				analizadorSemantico.llenarTablaSimbolos();
//				analizadorSemantico.analizarSemantica();

				agregarTokensATabla();

			} else {
				JOptionPane.showMessageDialog(null, "Escriba algo");
			}

		}
	}

	/**
	 * Metodo donde se agrega la informacion a las tablas
	 */
	private void agregarTokensATabla() {

		Object[] fila = new Object[4];
		Object[] fila1 = new Object[3];

		modelo.setRowCount(0);
		modeloDesconocido.setRowCount(0);
		modeloError.setRowCount(0);

		lista = analizador.getTablaSimbolos();
		for (int i = 0; i < lista.size(); i++) {
			fila[0] = lista.get(i).getLexema();
			fila[1] = lista.get(i).getCategoria();
			fila[2] = lista.get(i).getFila();
			fila[3] = lista.get(i).getColumna();

			modelo.addRow(fila);
		}

		lista = analizador.getTablaDesconocidos();
		for (int i = 0; i < lista.size(); i++) {
			fila[0] = lista.get(i).getLexema();
			fila[1] = lista.get(i).getCategoria();
			fila[2] = lista.get(i).getFila();
			fila[3] = lista.get(i).getColumna();

			modeloDesconocido.addRow(fila);
		}

		listaError = analizador.getTablaDeErrores();
		for (int i = 0; i < listaError.size(); i++) {
			fila1[0] = listaError.get(i).getMensaje();
			fila1[1] = listaError.get(i).getFila();
			fila1[2] = listaError.get(i).getColumna();

			modeloError.addRow(fila1);
		}

		listaErrorS = analizadorSintactico.getListaErrores();
		for (int i = 0; i < listaErrorS.size(); i++) {

			fila1[0] = listaErrorS.get(i).getMensaje();
			fila1[1] = listaErrorS.get(i).getFila();
			fila1[2] = listaErrorS.get(i).getColumna();

			modeloError.addRow(fila1);
		}

	}

}
