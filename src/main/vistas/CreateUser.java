package main.vistas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.interfaces.IUser;
import main.modelos.Users;

import javax.swing.JComboBox;

public class CreateUser {

	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private JPasswordField textField_3;
	private JTextField textField_4;
	private PrincipalFrame pf;
	private IUser iuser;
	
	
	public CreateUser(IUser iuser, PrincipalFrame pf) {
		this.iuser = iuser;
		this.pf = pf;
		pf.getInternalFrame().setTitle("Nuevo Usuario");
		pf.getInternalFrame().setBounds(63, 11, 493, 355);
		pf.getInternalFrame().setClosable(true);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(pf.getInternalFrame().getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblLoguinGenerado = new JLabel("Loguin generado:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		JLabel lblRepitaPassword = new JLabel("Repita password:");
		
		JLabel lblMail = new JLabel("Mail:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setColumns(10);
		
		textField_3 = new JPasswordField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JButton btnGenerarPassword = new JButton("Generar Password");
		btnGenerarPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un perfil de usuario", "Product Owner", "Scrum Master", "Developer", "Administrator"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Users user=new Users();
				user.setComplete_name(textField.getText());
				user.setNickname("frita");//textField_1.getText()
				user.setPassword(String.valueOf(textField_2.getPassword()));
				user.setMail(textField_4.getText());
				user.setPermiso_id(idPermiso(comboBox));
				iuser.añadirUsuario(user);
			}

			private Integer idPermiso(JComboBox comboBox) {
				int id=0;
				if (comboBox.getSelectedItem().toString().equals("Product Owner")) {
					id=4;
				}else if (comboBox.getSelectedItem().toString().equals("Scrum Master")) {
					id=3;
				}else if (comboBox.getSelectedItem().toString().equals("Developer")) {
					id=1;
				}else if (comboBox.getSelectedItem().toString().equals("Administrator")) {
					id=2;
				}
				return id;
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblLoguinGenerado)
								.addComponent(lblPassword)
								.addComponent(lblRepitaPassword)
								.addComponent(lblMail))
							.addGap(39)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
								.addComponent(textField)
								.addComponent(textField_2)
								.addComponent(textField_3)
								.addComponent(textField_4)))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGenerarPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLoguinGenerado)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGenerarPassword))
					.addGap(38)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepitaPassword)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMail)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGuardar))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		pf.getInternalFrame().getContentPane().setLayout(groupLayout);
	}
}
