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
import main.interfaces.IEspecificaciones;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.modelos.Especificaciones;
import main.modelos.Proyectos;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class MostrarEspecificaciones {

	private JPanel contentPane;
	private PrincipalFrame pf;
	private IUser iuser;
	private IProject iproject;
	private IEspecificaciones iesp;
	private ArrayList<Especificaciones> esp;
	private JPanel panel;

	public MostrarEspecificaciones(IUser iuser, PrincipalFrame pf, Proyectos proy) {
		this.iuser = iuser;
		this.pf = pf;

		if (this.iuser.getTitleConnection().equals(" (Online)")) {
			iproject = new ProyectoSQLRemota();
			iesp = new EspecificacionesSQLRemota();
		} else {
			iproject = new ProyectoSQLLocal();
			iesp = new EspecificacionesSQLLocal();
		}

		pf.getInternalFrame().setVisible(true);
		pf.getInternalFrame().setTitle("Especificaciones del proyecto: " + iproject.getProjectName(proy));
		pf.getInternalFrame().setBounds(63, 11, 550, 470);
		pf.getInternalFrame().setClosable(true);
		pf.getInternalFrame().setDefaultCloseOperation(pf.getInternalFrame().HIDE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnGuardar = new JButton("Guardar");

		JButton btnAnadir = new JButton("A\u00F1adir");

		JButton btnBorrar = new JButton("Borrar");

		panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		GroupLayout gl_contentPane = new GroupLayout(pf.getInternalFrame().getContentPane());
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(107)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnAnadir)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnBorrar, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE).addGap(114))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnGuardar)
								.addComponent(btnAnadir).addComponent(btnBorrar))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		mostrarEsp(proy.getProjectID());

		pf.getInternalFrame().getContentPane().setLayout(gl_contentPane);

		btnAnadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pf.getInternalFrame().getContentPane().removeAll();
				pf.getInternalFrame().repaint();
				new NuevaTarea(iuser, pf, proy);
			}
		});
	}

	private void mostrarEsp(int id) {
		esp = iesp.getEspecificacionesByProjectID(id);
		for (Especificaciones especificaciones : esp) {
			DetallesEspec de = new DetallesEspec();
			de.txtSprint.setText(de.txtSprint.getText() + " " + especificaciones.getSprintID());
			de.textArea.setText(especificaciones.getDescription());
			panel.add(de);
		}
	}
}
