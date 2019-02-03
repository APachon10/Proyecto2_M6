package main.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import main.implementaciones.ProyectoSQLLocal;
import main.implementaciones.ProyectoSQLRemota;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.modelos.Proyectos;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class MostrarProyectos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldPN;
	private JTextField textFieldPO;
	private JTextField textFieldSM;
	private JTextArea textFieldD;
	private PrincipalFrame pf;
	private IUser iuser;
	private IProject iproject;
	private DefaultTableModel dtm;
	private ArrayList<Proyectos> proyectos;

	public MostrarProyectos(IUser iuser, PrincipalFrame pf) {
		this.iuser = iuser;
		this.pf = pf;

		if (this.iuser.getTitleConnection().equals(" (Online)")) {
			iproject = new ProyectoSQLRemota();
		} else {
			iproject = new ProyectoSQLLocal();
		}

		pf.getInternalFrame().setVisible(true);
		pf.getInternalFrame().setTitle("Proyectos");
		pf.getInternalFrame().setBounds(63, 11, 550, 470);
		pf.getInternalFrame().setClosable(true);
		pf.getInternalFrame().setDefaultCloseOperation(pf.getInternalFrame().HIDE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNombreProyecto = new JLabel("Nombre Proyecto:");

		JLabel lblProductOwner = new JLabel("Product Owner:");

		JLabel lblScrumMaster = new JLabel("Scrum Master:");

		textFieldPN = new JTextField();
		textFieldPN.setColumns(10);
		textFieldPN.setEditable(false);

		textFieldPO = new JTextField();
		textFieldPO.setColumns(10);
		textFieldPO.setEditable(false);

		textFieldSM = new JTextField();
		textFieldSM.setColumns(10);
		textFieldSM.setEditable(false);

		textFieldD = new JTextArea();
		textFieldD.setColumns(10);
		textFieldD.setEditable(false);
		textFieldD.setLineWrap(true);

		JButton btnMostrarEspecificaciones = new JButton("Mostrar Especificaciones");
		GroupLayout gl_contentPane = new GroupLayout(pf.getInternalFrame().getContentPane());
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(
								btnMostrarEspecificaciones, GroupLayout.PREFERRED_SIZE, 249,
								GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
										.createSequentialGroup().addGap(26)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblScrumMaster, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblProductOwner, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNombreProyecto, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldPO, GroupLayout.DEFAULT_SIZE, 133,
														Short.MAX_VALUE)
												.addComponent(textFieldPN, GroupLayout.DEFAULT_SIZE, 133,
														Short.MAX_VALUE)
												.addComponent(textFieldSM, GroupLayout.DEFAULT_SIZE, 147,
														Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(
												textFieldD, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(34)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNombreProyecto)
												.addComponent(textFieldPN, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(50)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblProductOwner).addComponent(textFieldPO,
														GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
										.addGap(38)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblScrumMaster).addComponent(textFieldSM,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addGap(18).addComponent(textFieldD, GroupLayout.PREFERRED_SIZE, 175,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(18).addComponent(btnMostrarEspecificaciones).addContainerGap(12, Short.MAX_VALUE)));

		table = new JTable();
		this.dtm = new DefaultTableModel(new String[] { "Proyectos" }, 0);
		table.setModel(dtm);
		scrollPane.setViewportView(table);
		pf.getInternalFrame().getContentPane().setLayout(gl_contentPane);
		mostrarProyectos();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				textFieldPN.setText(proyectos.get(table.getSelectedRow()).getProject_name());
				textFieldPO.setText(iuser.getUserNameByID(proyectos.get(table.getSelectedRow()).getProductOwnerID()));
				textFieldSM.setText(iuser.getUserNameByID(proyectos.get(table.getSelectedRow()).getScrumMasterID()));
				textFieldD.setText(proyectos.get(table.getSelectedRow()).getDescripcion());
				
			}
		});
	}

	private void mostrarProyectos() {
		proyectos = iproject.getProjectsByID(iuser);
		for (Proyectos proy : proyectos) {
			Object[] name = { proy.getProject_name() };
			dtm.addRow(name);
		}
	}
}
