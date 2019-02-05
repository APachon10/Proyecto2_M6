package main.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.implementaciones.EspecificacionesSQLLocal;
import main.implementaciones.EspecificacionesSQLRemota;
import main.implementaciones.ProyectoSQLLocal;
import main.implementaciones.ProyectoSQLRemota;
import main.implementaciones.SprintsSQLLocal;
import main.implementaciones.SprintsSQLRemota;
import main.interfaces.IEspecificaciones;
import main.interfaces.IProject;
import main.interfaces.ISprint;
import main.interfaces.IUser;
import main.modelos.Especificaciones;
import main.modelos.Proyectos;
import main.modelos.Sprints;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class NuevaTarea extends JFrame {

	private JPanel contentPane;
	private PrincipalFrame pf;
	private IUser iuser;
	private IProject iproject;
	private IEspecificaciones iesp;
	private ISprint isp;
	private ArrayList<Sprints> sp;
	private JComboBox comboBoxSpr;
	private Proyectos proy;
	private JSpinner spinner;
	private JTextArea textAreaD;

	public NuevaTarea(IUser iuser, PrincipalFrame pf, Proyectos proy) {
		this.iuser = iuser;
		this.pf = pf;
		this.proy = proy;

		if (this.iuser.getTitleConnection().equals(" (Online)")) {
			iproject = new ProyectoSQLRemota();
			iesp = new EspecificacionesSQLRemota();
			isp = new SprintsSQLRemota();
		} else {
			iproject = new ProyectoSQLLocal();
			iesp = new EspecificacionesSQLLocal();
			isp = new SprintsSQLLocal();
		}

		pf.getInternalFrame().setVisible(true);
		pf.getInternalFrame().setTitle("Nueva Tarea");
		pf.getInternalFrame().setBounds(63, 11, 550, 200);
		pf.getInternalFrame().setClosable(true);
		pf.getInternalFrame().setDefaultCloseOperation(pf.getInternalFrame().HIDE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		textAreaD = new JTextArea();

		JLabel lblDescripcion = new JLabel("Descripcion:");

		JLabel lblSprint = new JLabel("Sprint:");

		comboBoxSpr = new JComboBox();
		comboBoxSpr.setModel(new DefaultComboBoxModel(new String[] { "Seleccionar Sprint" }));

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 25, 1));

		JLabel lblHoras = new JLabel("Horas:");

		JButton btnAadir = new JButton("A\u00F1adir");
		GroupLayout gl_contentPane = new GroupLayout(pf.getInternalFrame().getContentPane());
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING).addComponent(lblDescripcion).addComponent(lblSprint))
				.addGap(10)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(comboBoxSpr, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblHoras)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnAadir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addComponent(textAreaD, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(35, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(38)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(textAreaD, GroupLayout.PREFERRED_SIZE, 56,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblDescripcion))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSprint)
												.addComponent(comboBoxSpr, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAadir)
												.addComponent(spinner, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHoras))
										.addContainerGap(125, Short.MAX_VALUE)));
		pf.getInternalFrame().getContentPane().setLayout(gl_contentPane);

		cargarSprints(proy);
		btnAadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertarEspecificacion();
			}
		});
	}

	private void insertarEspecificacion() {
		Especificaciones esp = new Especificaciones();
		esp.setSprintID((int) comboBoxSpr.getSelectedIndex());
		esp.setProjectID(proy.getProjectID());
		esp.setHoras((Integer) spinner.getValue());
		esp.setDescription(textAreaD.getText());
		if (esp.getSprintID()==0 || esp.getDescription()==null || esp.getDescription().equals("")) {
			JOptionPane.showMessageDialog(null, "Error al introducir especificación", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		} else {
			iesp.insertarEspecificacion(esp, true);
			JOptionPane.showMessageDialog(null, "Se ha insertado la especificacion");
			pf.getInternalFrame().getContentPane().removeAll();
			pf.getInternalFrame().repaint();
			new MostrarEspecificaciones(iuser, pf, proy);
		}
	}

	private void cargarSprints(Proyectos proy) {
		sp = isp.getSprintsByProjectD(proy.getProjectID());
		for (Sprints spr : sp) {
			comboBoxSpr.addItem(spr.getSprintID());
		}
	}
}
