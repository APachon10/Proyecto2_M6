package main.vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.implementaciones.ProyectoSQLLocal;
import main.implementaciones.ProyectoSQLRemota;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.modelos.Proyectos;
import main.modelos.Users;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CrearProyecto {

	private JTextField textField_pn;
	private JTextField textField_d;
	private PrincipalFrame pf;
	private IUser iuser;
	private IProject iproject;

	public CrearProyecto(IUser iuser, PrincipalFrame pf) {
		
		this.iuser = iuser;
		this.pf = pf;
		
		if(this.iuser.getTitleConnection().equals(" (Online)")) {
			iproject=new ProyectoSQLRemota();
		}else {
			iproject=new ProyectoSQLLocal();
		}
		
		ArrayList<Users> smUsers=iuser.getScrumMasterUsers();
		ArrayList<Users> poUsers=iuser.getProductOwnerUsers();
		ArrayList<String> smUsersName=new ArrayList<>();
		ArrayList<String> poUsersName=new ArrayList<>();
		
		for (Users user : smUsers) {
			smUsersName.add(user.getUserID()+"."+user.getComplete_name());
		}
		
		for (Users user : poUsers) {
			poUsersName.add(user.getUserID()+"."+user.getComplete_name());
		}
		
		pf.getInternalFrame().setTitle("Nuevo Proyecto");
		pf.getInternalFrame().setBounds(63, 11, 493, 355);
		pf.getInternalFrame().setClosable(true);
		
		JLabel lblNombreProyecto = new JLabel("Nombre Proyecto:");
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		
		JLabel lblScrumMaster = new JLabel("Scrum Master:");
		
		JLabel lblProductOwner = new JLabel("Product Owner:");
		
		textField_pn = new JTextField();
		textField_pn.setColumns(10);
		
		textField_d = new JTextField();
		textField_d.setColumns(10);
		
		JComboBox comboBox_sm = new JComboBox();
		comboBox_sm.setModel(new DefaultComboBoxModel(smUsersName.toArray()));
		
		JComboBox comboBox_po = new JComboBox();
		comboBox_po.setModel(new DefaultComboBoxModel(poUsersName.toArray()));
		
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Proyectos proy=new Proyectos();
				proy.setProject_name(textField_pn.getText());
				proy.setDescripcion(textField_d.getText());
				proy.setScrumMasterID(Integer.parseInt(comboBox_sm.getSelectedItem().toString().substring(0,1)));
				proy.setProductOwnerID(Integer.parseInt(comboBox_po.getSelectedItem().toString().substring(0,1)));
				iproject.insertarProyecto(proy);
			}
		});
		GroupLayout groupLayout = new GroupLayout(pf.getInternalFrame().getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombreProyecto)
								.addComponent(lblDescripcion)
								.addComponent(lblScrumMaster))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(comboBox_po, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_sm, Alignment.TRAILING, 0, 123, Short.MAX_VALUE)
								.addComponent(btnAadir)
								.addComponent(textField_d, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
								.addComponent(textField_pn)))
						.addComponent(lblProductOwner))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreProyecto)
						.addComponent(textField_pn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion)
						.addComponent(textField_d, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(lblScrumMaster))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(comboBox_sm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductOwner)
						.addComponent(comboBox_po, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAadir)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		pf.getInternalFrame().getContentPane().setLayout(groupLayout);
	}
}
