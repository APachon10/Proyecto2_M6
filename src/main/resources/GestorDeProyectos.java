package main.resources;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

public class GestorDeProyectos extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtPassword;
	private JTextField txtNombre;
	private JTextField txtLoginGen;
	private JTextField txtPassword2;
	private JTextField txtRepitaPassword;
	private JTextField txtMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorDeProyectos frame = new GestorDeProyectos();
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
	public GestorDeProyectos() {
		setTitle("Gestor de Proyectos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 490);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Proyecto");
		mnNewMenu.setEnabled(false);
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Usuarios");
		mnNewMenu_1.setEnabled(false);
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mnNewMenu_1.add(mntmNuevoUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JInternalFrame ifLogin = new JInternalFrame("Login");
		ifLogin.setResizable(true);
		ifLogin.setClosable(true);
		ifLogin.setBounds(167, 96, 302, 271);
		desktopPane.add(ifLogin);

		JLabel lblLogin = new JLabel("Login:");

		JLabel lblPassword = new JLabel("Password:");
		
				JInternalFrame ifNewUser = new JInternalFrame("Nuevo Usuario");
				ifNewUser.setResizable(true);
				ifNewUser.setClosable(true);
				ifNewUser.setBounds(10, 96, 585, 313);
				desktopPane.add(ifNewUser);
				
				JLabel lblNombre = new JLabel("Nombre:");
				
				JLabel lblLoginGen = new JLabel("Login Generado:");
				
				JLabel lblPassword2 = new JLabel("Password:");
				
				JLabel lblRepitaPassword = new JLabel("Repita password:");
				
				JLabel lblMail = new JLabel("Mail:");
				
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un perfil de usuario", "Master Owner", "Scrum Master", "Developer", "Administrador"}));
				
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				
				txtLoginGen = new JTextField();
				txtLoginGen.setColumns(10);
				
				txtPassword2 = new JTextField();
				txtPassword2.setColumns(10);
				
				txtRepitaPassword = new JTextField();
				txtRepitaPassword.setColumns(10);
				
				txtMail = new JTextField();
				txtMail.setColumns(10);
				
				JButton btnGuardar = new JButton("GUARDAR");
				
				JButton btnGenerarPassword = new JButton("Generar Password");
				GroupLayout groupLayout_1 = new GroupLayout(ifNewUser.getContentPane());
				groupLayout_1.setHorizontalGroup(
					groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_1.createSequentialGroup()
									.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMail)
										.addComponent(lblPassword2)
										.addComponent(lblRepitaPassword)
										.addComponent(lblLoginGen)
										.addComponent(lblNombre))
									.addGap(104)
									.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtLoginGen)
										.addComponent(txtPassword2)
										.addComponent(txtRepitaPassword, Alignment.LEADING)
										.addComponent(txtMail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
										.addComponent(txtNombre, Alignment.LEADING))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnGenerarPassword, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
									.addGap(23))
								.addGroup(groupLayout_1.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
									.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
									.addGap(70))))
				);
				groupLayout_1.setVerticalGroup(
					groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLoginGen)
								.addComponent(txtLoginGen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword2)
								.addComponent(txtPassword2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGenerarPassword))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRepitaPassword)
								.addComponent(txtRepitaPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMail)
								.addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGuardar))
							.addContainerGap(114, Short.MAX_VALUE))
				);
				ifNewUser.getContentPane().setLayout(groupLayout_1);
				
						ifNewUser.setVisible(false);

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setBounds(292, 15, 204, 14);
		desktopPane.add(lblUser);

		txtLogin = new JTextField();
		txtLogin.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);

		JButton btnEnviar = new JButton("ENVIAR");

		btnEnviar.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Usuario o Password incorrecto", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						mnNewMenu.setEnabled(true);
						mnNewMenu_1.setEnabled(true);
						ifLogin.hide();
						JOptionPane.showMessageDialog(null, "Login correcto", "Informacion",
								JOptionPane.INFORMATION_MESSAGE);
						lblUser.setText("Usuario: " + txtLogin.getText() + " ( Scrum Master )");
					}
				}
				txtLogin.setText("");
				txtPassword.setText("");
			}
		});

		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Usuario o Password incorrecto.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					mnNewMenu.setEnabled(true);
					mnNewMenu_1.setEnabled(true);
					ifLogin.hide();
					JOptionPane.showMessageDialog(null, "Login correcto.", "Informacion",
							JOptionPane.INFORMATION_MESSAGE);
					lblUser.setText("Usuario: " + txtLogin.getText() + " ( Scrum Master )");
				}
				txtLogin.setText("");
				txtPassword.setText("");
			}
		});

		GroupLayout groupLayout = new GroupLayout(ifLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblLogin)
									.addGap(41))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(20)))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtPassword, Alignment.LEADING)
								.addComponent(txtLogin, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(37)
					.addComponent(btnEnviar)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		ifLogin.getContentPane().setLayout(groupLayout);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(506, 11, 89, 23);
		desktopPane.add(btnSalir);
		
		JLabel lblOnline = new JLabel("Estado: Offline");
		lblOnline.setBounds(10, 15, 177, 14);
		desktopPane.add(lblOnline);
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
			EntityManager entityManager = factory.createEntityManager();
			
			lblOnline.setText("Estado: Online");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ifLogin.setVisible(true);

		mntmNuevoUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ifNewUser.setVisible(true);
			}
		});

		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblUser.setText("Usuario:");
				ifLogin.setVisible(true);
				ifNewUser.setVisible(false);
				mnNewMenu.setEnabled(false);
				mnNewMenu_1.setEnabled(false);
			}
		});
	}
}
