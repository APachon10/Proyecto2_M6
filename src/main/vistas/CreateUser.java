package main.vistas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.interfaces.IUser;
import main.modelos.Users;

import javax.swing.JComboBox;

public class CreateUser {

	private JTextField txtName;
	private JTextField txtLogin;
	private JPasswordField txtPassword;
	private JPasswordField txtRepeatPassword;
	private JTextField txtMail;
	private PrincipalFrame pf;
	JComboBox comboBox;
	private boolean emailOk;
	private IUser iuser;

	public static String NUMEROS = "0123456789";
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	public static String ESPECIALES = "ñÑ";

	public CreateUser(IUser iuser, PrincipalFrame pf) {
		this.iuser = iuser;
		this.pf = pf;
		pf.getInternalFrame().setVisible(true);
		pf.getInternalFrame().setTitle("Nuevo Usuario");
		pf.getInternalFrame().setBounds(63, 11, 493, 355);
		pf.getInternalFrame().setClosable(true);
		pf.getInternalFrame().setDefaultCloseOperation(pf.getInternalFrame().HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(pf.getInternalFrame().getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		JLabel lblNombre = new JLabel("Nombre:");

		JLabel lblLoguinGenerado = new JLabel("Login generado:");

		JLabel lblPassword = new JLabel("Password:");

		JLabel lblRepitaPassword = new JLabel("Repita password:");

		JLabel lblMail = new JLabel("Mail:");

		txtName = new JTextField();
		txtName.setColumns(10);

		txtLogin = new JTextField();
		txtLogin.setEditable(false);
		txtLogin.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);

		txtRepeatPassword = new JPasswordField();
		txtRepeatPassword.setColumns(10);

		txtMail = new JTextField();
		txtMail.setColumns(10);

		JCheckBox chckbxMostrarPassword = new JCheckBox("Mostrar Password");

		JLabel lblValidar = new JLabel("");
		lblValidar.setForeground(Color.RED);

		JButton btnGenerarPassword = new JButton("Generar Password");
		btnGenerarPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = getPassword(MINUSCULAS + MAYUSCULAS + NUMEROS + ESPECIALES, 6);
				JOptionPane.showMessageDialog(null, "Tu password es: " + password, "Información",
						JOptionPane.INFORMATION_MESSAGE);
				txtPassword.setText(password);
				txtRepeatPassword.setText("");
			}
		});

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Seleccione un perfil de usuario", "Product Owner",
				"Scrum Master", "Developer", "Administrator" }));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));

		// generate login
		txtName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				validation();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validation();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validation();
			}

			public void validation() {
				txtName.addFocusListener(new FocusListener() {

					@Override
					public void focusLost(FocusEvent e) {
						String nombre = txtName.getText();
						String[] arrayNombre = nombre.split(" ");
						if (arrayNombre.length == 1) {
							JOptionPane.showMessageDialog(null, "Formato del nombre incorrecto.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						txtLogin.setText(arrayNombre[0].substring(0, 1) + arrayNombre[1]);
					}

					@Override
					public void focusGained(FocusEvent e) {
						txtLogin.setText("");
					}
				});
			}
		});

		// checkbox show password
		chckbxMostrarPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxMostrarPassword.isSelected()) {
					txtPassword.setEchoChar((char) 0);
					txtRepeatPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('*');
					txtRepeatPassword.setEchoChar('*');
				}
			}
		});

		// validate mail
		txtMail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (getMail(txtMail.getText()) == false) {
					JOptionPane.showMessageDialog(null, "El formato del email incorrecto.", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtMail.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtName.getText().equals("") || txtLogin.getText().equals("") || txtPassword.getText().equals("")
						|| txtRepeatPassword.getText().equals("") || txtMail.getText().equals("")
						|| comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Faltan campos por rellenar.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (!txtName.getText().equals("") || !txtLogin.getText().equals("")
						|| !txtPassword.getText().equals("") || !txtRepeatPassword.getText().equals("")
						|| !txtMail.getText().equals("") || comboBox.getSelectedIndex() != 0) {
					if (txtPassword.getText().equals(txtRepeatPassword.getText())) {
						lblValidar.setText("");
						if (getMail(txtMail.getText()) == true) {
							Users user = new Users();
							user.setComplete_name(txtName.getText());
							user.setNickname(txtLogin.getText());
							user.setPassword(String.valueOf(txtPassword.getPassword()));
							user.setMail(txtMail.getText());
							user.setPermiso_id(idPermiso(comboBox));
							iuser.añadirUsuario(user);
							txtName.setText("");
							txtLogin.setText("");
							txtPassword.setText("");
							txtRepeatPassword.setText("");
							chckbxMostrarPassword.setSelected(false);
							txtMail.setText("");
							comboBox.setSelectedIndex(0);
						}
					} else {
						lblValidar.setText("No coinciden");
					}
				}
			}

			private Integer idPermiso(JComboBox comboBox) {
				int id = 0;
				if (comboBox.getSelectedItem().toString().equals("Product Owner")) {
					id = 4;
				} else if (comboBox.getSelectedItem().toString().equals("Scrum Master")) {
					id = 3;
				} else if (comboBox.getSelectedItem().toString().equals("Developer")) {
					id = 1;
				} else if (comboBox.getSelectedItem().toString().equals("Administrator")) {
					id = 2;
				}
				return id;
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel
								.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel
												.createSequentialGroup().addContainerGap().addGroup(gl_panel
														.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
																.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNombre)
																.addComponent(lblLoguinGenerado)
																.addComponent(lblPassword)
																.addComponent(lblRepitaPassword).addComponent(lblMail))
																.addGap(39)
																.addGroup(gl_panel
																		.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(txtLogin,
																				GroupLayout.DEFAULT_SIZE, 130,
																				Short.MAX_VALUE)
																		.addComponent(txtName).addComponent(txtPassword)
																		.addComponent(txtRepeatPassword)
																		.addComponent(txtMail)))
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(43)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel
																.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(btnGuardar, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnGenerarPassword, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addComponent(lblValidar, GroupLayout.DEFAULT_SIZE, 118,
																Short.MAX_VALUE)))
										.addGroup(Alignment.TRAILING,
												gl_panel.createSequentialGroup().addGap(307).addComponent(
														chckbxMostrarPassword, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(24)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre).addComponent(txtName,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(29)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblLoguinGenerado).addComponent(
						txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGenerarPassword))
				.addGap(8).addComponent(chckbxMostrarPassword).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblRepitaPassword)
						.addComponent(txtRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValidar))
				.addGap(37)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblMail).addComponent(txtMail,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGuardar))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		pf.getInternalFrame().getContentPane().setLayout(groupLayout);
	}

	// validate mail
	public boolean getMail(String correo) {

		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		String email = correo;
		Matcher mather = pattern.matcher(email);

		if (!correo.equals("")) {
			if (mather.find() == true) {
				emailOk = true;
			} else {
				emailOk = false;
			}
		}
		return emailOk;
	}

	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES, length);
	}

	public static String getPassword(String key, int length) {
		String pswd = "";

		for (int i = 0; i < length; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		return pswd;
	}
}