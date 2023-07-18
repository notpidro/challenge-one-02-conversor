package ar.com.challenge.conversor.vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.com.challenge.conversor.moneda.Moneda;
import ar.com.challenge.conversor.moneda.MonedasData;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.swing.JButton;

public class ConversorMoneda extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidadIngresada;
	private JLabel imgFlecha;
	private JTextField lblCantidadResultante;
	private JComboBox<Moneda> seleccionUno;
	private JComboBox<Moneda> seleccionDos;

	private JTextField lblActualizacionDos;
	private JTextField lblInfo;

	public void mostrarResultado() throws IOException {
		if (!txtCantidadIngresada.getText().isEmpty()) {

			Moneda monedaUno = (Moneda) seleccionUno.getSelectedItem();
			Moneda monedaDos = (Moneda) seleccionDos.getSelectedItem();

			DecimalFormat formateo = new DecimalFormat("#,##0.00");

			if (monedaUno != monedaDos) {
				Double resultado = (Double) MonedasData.getValores(monedaUno, monedaDos).get(1);

				String comprobarFechaYHora = (String) MonedasData.getValores(monedaUno, monedaDos).get(0);

				String resultadoFormateado = formateo
						.format(resultado * Double.parseDouble(txtCantidadIngresada.getText().replace(",", ".")));
				lblCantidadResultante.setText(resultadoFormateado.toString());

				if (comprobarFechaYHora.contains(":") && !comprobarFechaYHora.contains("/")) {
					String actualizacionDos = comprobarFechaYHora + " - " + LocalDate.now();
					lblActualizacionDos.setText(actualizacionDos);
				} else if (!comprobarFechaYHora.contains(":") && comprobarFechaYHora.contains("/")) {
					String actualizacionDos = "Fecha: " + comprobarFechaYHora;
					lblActualizacionDos.setText(actualizacionDos);
				}

			} else if (monedaUno == monedaDos) {
				Double resultado = Double.parseDouble(txtCantidadIngresada.getText().replace(",", "."));
				String resultadoFormateado = formateo.format(resultado);
				lblCantidadResultante.setText(resultadoFormateado);
			}

		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorMoneda frame = new ConversorMoneda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConversorMoneda() {
		setResizable(false);
		setTitle("Conversor de monedas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 285);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(59, 54, 50));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Elije las monedas a convertir");
		lblTitulo.setBounds(118, 11, 312, 26);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitulo);

		seleccionUno = new JComboBox<Moneda>();
		seleccionUno.setBounds(59, 61, 175, 25);
		seleccionUno.setModel(new DefaultComboBoxModel<Moneda>(Moneda.values()));
		seleccionUno.setFont(new Font("Tahoma", Font.BOLD, 17));
		contentPane.add(seleccionUno);

		seleccionDos = new JComboBox<Moneda>();
		seleccionDos.setBounds(318, 61, 175, 25);
		seleccionDos.setFont(new Font("Tahoma", Font.BOLD, 17));
		seleccionDos.setModel(new DefaultComboBoxModel<Moneda>(Moneda.values()));
		contentPane.add(seleccionDos);

		txtCantidadIngresada = new JTextField();
		txtCantidadIngresada.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCantidadIngresada.setBounds(59, 108, 175, 26);
		contentPane.add(txtCantidadIngresada);
		txtCantidadIngresada.setColumns(10);

		txtCantidadIngresada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != ',') {
					e.consume();
				} else if (c == ',' && txtCantidadIngresada.getText().isEmpty()) {
					e.consume();
				}
			}
		});

		imgFlecha = new JLabel(
				new ImageIcon("C:\\Users\\Walter\\OneDrive\\ONE BackEnd\\Java\\challenge-one-conversor\\flecha.png"));
		imgFlecha.setEnabled(false);
		imgFlecha.setBounds(244, 103, 64, 41);
		contentPane.add(imgFlecha);

		lblCantidadResultante = new JTextField();
		lblCantidadResultante.setEditable(false);
		lblCantidadResultante.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCantidadResultante.setColumns(10);
		lblCantidadResultante.setBounds(318, 108, 175, 26);
		contentPane.add(lblCantidadResultante);

		lblActualizacionDos = new JTextField();
		lblActualizacionDos.setForeground(new Color(255, 255, 255));
		lblActualizacionDos.setCaretColor(new Color(255, 255, 255));
		lblActualizacionDos.setBorder(null);
		lblActualizacionDos.setBackground(new Color(59, 54, 50));
		lblActualizacionDos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblActualizacionDos.setEditable(false);
		lblActualizacionDos.setColumns(10);
		lblActualizacionDos.setBounds(228, 145, 175, 26);
		contentPane.add(lblActualizacionDos);

		JButton btnActualizar = new JButton("Convertir");
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mostrarResultado();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(376, 194, 117, 23);
		contentPane.add(btnActualizar);

		lblInfo = new JTextField();
		lblInfo.setText("Ultima actualizacion:");
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblInfo.setEditable(false);
		lblInfo.setColumns(10);
		lblInfo.setCaretColor(Color.WHITE);
		lblInfo.setBorder(null);
		lblInfo.setBackground(new Color(59, 54, 50));
		lblInfo.setBounds(59, 145, 175, 26);
		contentPane.add(lblInfo);

	}
}
