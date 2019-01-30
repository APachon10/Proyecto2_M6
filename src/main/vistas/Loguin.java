package main.vistas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import main.interfaces.IUser;
import main.modelos.Users;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;

public class Loguin implements ActionListener {

	private JFrame frame;
	private IUser iuser;
	public static PrincipalFrame pf;
	private GroupLayout groupLayout;
	private JTextField textNick;
	private JPasswordField textPass;
	private JLabel lblError;
	
	public Loguin(IUser iuser) {
		this.iuser = iuser;
		this.pf = new PrincipalFrame(iuser);

		pf.getInternalFrame().setClosable(false);
		pf.getInternalFrame().setLocation(31, 0);

		JLabel lblLogin = new JLabel("Login:");

		textNick = new JTextField();
		textNick.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		textPass = new JPasswordField();
		textPass.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);

		lblError = new JLabel("Usuario y/o contrase\u00F1a erronea");
		lblError.setForeground(Color.RED);
		GroupLayout groupLayout_1 = new GroupLayout(pf.getInternalFrame().getContentPane());
		groupLayout_1.setHorizontalGroup(groupLayout_1.createParallelGroup(Alignment.LEADING).addGroup(groupLayout_1
				.createSequentialGroup().addGap(65)
				.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								groupLayout_1.createSequentialGroup()
										.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblPassword).addComponent(lblLogin,
														GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
										.addGap(28)
										.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
												.addComponent(textNick, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(textPass, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(105, Short.MAX_VALUE)));
		groupLayout_1.setVerticalGroup(groupLayout_1.createParallelGroup(Alignment.LEADING).addGroup(groupLayout_1
				.createSequentialGroup().addGap(55)
				.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE).addComponent(lblLogin).addComponent(
						textNick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(62)
				.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword).addComponent(
						textPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(lblError).addGap(18).addComponent(btnEnviar)
				.addContainerGap(95, Short.MAX_VALUE)));
		pf.getInternalFrame().getContentPane().setLayout(groupLayout_1);
		PrincipalFrame.menuBar.setVisible(false);
		pf.getLblUser().setVisible(false);
		pf.getLabelNickPermiso().setVisible(false);
		pf.getBtnSalir().setVisible(false);
		pf.setTitle(pf.getTitle() + iuser.getTitleConnection());

		frame = new JFrame();
		frame.setBounds(100, 100, 528, 412);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGap(0, 512, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGap(0, 373, Short.MAX_VALUE));
		frame.getContentPane().setLayout(groupLayout);
		lblError.setVisible(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (textNick.getText().equals("") || textPass.getPassword().length == 0) {
			lblError.setVisible(true);
		} else {
			lblError.setVisible(false);
			Users user = iuser.getUserLogin(this.textNick.getText(), String.valueOf(this.textPass.getPassword()));
			if (user == null) {
				this.lblError.setVisible(true);
			} else {
				pf.getInternalFrame().getContentPane().removeAll();
				pf.getInternalFrame().repaint();
				pf.getBtnSalir().setVisible(true);
				System.out.println(iuser.getUserLogged());
				System.out.println(iuser.getUserLoggedPermission());
				this.lblError.setVisible(false);
				PrincipalFrame.menuBar.setVisible(true);
				if (iuser.getUserLogged().getPermiso_id() == 3) {
					pf.getMntmCrearUsuario().setVisible(false);
					new CrearProyecto(iuser, pf);
				} else if (iuser.getUserLogged().getPermiso_id() == 1) {
					pf.getMntmCrearUsuario().setVisible(false);
					pf.getMntmCrearProyecto().setVisible(false);
				} else if (iuser.getUserLogged().getPermiso_id() == 2) {
					System.out.println("hola");
					pf.getMntmCrearProyecto().setVisible(false);
					pf.getMntmBuscarmodificarUsuarios().setVisible(false);
					pf.getMntmMostrarProyectos().setVisible(false);
					new CreateUser(iuser,pf);
				}else if (iuser.getUserLogged().getPermiso_id() == 4) {
					
					pf.getMntmCrearUsuario().setVisible(false);
					pf.getMntmCrearProyecto().setVisible(false);
				}
				pf.getLblUser().setVisible(true);
				pf.getLabelNickPermiso().setVisible(true);
				
				pf.getLabelNickPermiso().setText(iuser.getUserLogged().getNickname()+"("+iuser.getUserLoggedPermission()+")");;				
			}
		}
	}
}