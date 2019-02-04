package main.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class añadirEspecificaciones extends JFrame {

	private JPanel contentPane;
	private JLabel lblDescripcion,lblSprint,lblNumHoras;
	private JComboBox num_Sprints;
	private JTextArea DescArea;
	private JSpinner num_Horas;
	private JButton btnAñadir;
	
	public añadirEspecificaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblDescripcion = new JLabel("Descripcion");
		
		lblSprint = new JLabel("Sprint");
		
		num_Sprints = new JComboBox();
		
		DescArea = new JTextArea();
		
		lblNumHoras = new JLabel("Num Horas");
		
		num_Horas = new JSpinner();
		
		btnAñadir = new JButton("A\u00F1adir");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblDescripcion, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(DescArea, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSprint)
							.addGap(18)
							.addComponent(num_Sprints, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNumHoras)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(num_Horas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAñadir, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescripcion)
						.addComponent(DescArea, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSprint)
						.addComponent(num_Sprints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumHoras)
						.addComponent(num_Horas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAñadir))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
		//Settins de los componentes de la ventana
		num_Sprints.setModel(new DefaultComboBoxModel<>());
		num_Sprints.addItem("Sprint 1");
		num_Sprints.addItem("Sprint 2");
		num_Sprints.addItem("Sprint 3");
		
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new añadirEspecificaciones();
	}
}
