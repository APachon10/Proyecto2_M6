package main.vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.MenuBar;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.interfaces.IUser;

import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalFrame extends JFrame {

	private JInternalFrame internalFrame;
	private JPanel panel;
	private JLabel lblUser;
	private JLabel labelNickPermiso;
	private JButton btnSalir;
	private JDesktopPane desktopPane;
	private GroupLayout gl_panel;
	public static JMenuBar menuBar;
	private JMenu mnProyecto;
	private JMenu mnUsuario;
	private JMenuItem mntmMostrarProyectos;
	private JMenuItem mntmCrearProyecto;
	private JMenuItem mntmBuscarmodificarUsuarios;
	private JMenuItem mntmCrearUsuario;
	private PrincipalFrame pf;
	private IUser iuser;

	PrincipalFrame(IUser iuser) {
		pf=this;
		this.setVisible(true);
		this.setTitle("Gestor de Proyectos");
		this.setBounds(100, 100, 596, 587);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(panel,
				Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE));

		btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				internalFrame.getContentPane().removeAll();
				internalFrame.repaint();
				dispose();
                new Loguin(iuser);
			}
		});

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);

		lblUser = new JLabel("User:");

		labelNickPermiso = new JLabel("");
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(284, Short.MAX_VALUE)
					.addComponent(lblUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(labelNickPermiso, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUser)
						.addComponent(btnSalir)
						.addComponent(labelNickPermiso, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
		);

		internalFrame = new JInternalFrame("Login", true, true, true);
		internalFrame.setResizable(true);
		internalFrame.setMaximizable(true);
		internalFrame.setClosable(true);
		internalFrame.setBounds(114, 11, 350, 355);
		desktopPane.add(internalFrame);
		internalFrame.setVisible(true);
		panel.setLayout(gl_panel);
		this.getContentPane().setLayout(groupLayout);

		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnProyecto = new JMenu("Proyectos");
		menuBar.add(mnProyecto);

		mntmCrearProyecto = new JMenuItem("Crear Proyecto");
		mntmCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CrearProyecto(iuser,pf);
			}
		});
		mnProyecto.add(mntmCrearProyecto);

		mntmMostrarProyectos = new JMenuItem("Mostrar Proyectos");
		mnProyecto.add(mntmMostrarProyectos);

		mnUsuario = new JMenu("Usuarios");
		menuBar.add(mnUsuario);

		mntmCrearUsuario = new JMenuItem("Crear Usuario");
		mntmCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateUser(iuser,pf);
			}
		});
		mnUsuario.add(mntmCrearUsuario);

		mntmBuscarmodificarUsuarios = new JMenuItem("Buscar/Modificar Usuarios");
		mnUsuario.add(mntmBuscarmodificarUsuarios);
	}

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLblUser() {
		return lblUser;
	}

	public void setLblUser(JLabel lblUser) {
		this.lblUser = lblUser;
	}

	public JLabel getLabelNickPermiso() {
		return labelNickPermiso;
	}

	public void setLabelNickPermiso(JLabel labelNickPermiso) {
		this.labelNickPermiso = labelNickPermiso;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	// public JMenuBar getMenuBar() {
	// return menuBar;
	// }

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMnProyecto() {
		return mnProyecto;
	}

	public void setMnProyecto(JMenu mnProyecto) {
		this.mnProyecto = mnProyecto;
	}

	public JMenu getMnUsuario() {
		return mnUsuario;
	}

	public void setMnUsuario(JMenu mnUsuario) {
		this.mnUsuario = mnUsuario;
	}

	public JMenuItem getMntmMostrarProyectos() {
		return mntmMostrarProyectos;
	}

	public void setMntmMostrarProyectos(JMenuItem mntmMostrarProyectos) {
		this.mntmMostrarProyectos = mntmMostrarProyectos;
	}

	public JMenuItem getMntmCrearProyecto() {
		return mntmCrearProyecto;
	}

	public void setMntmCrearProyecto(JMenuItem mntmCrearProyecto) {
		this.mntmCrearProyecto = mntmCrearProyecto;
	}

	public JMenuItem getMntmBuscarmodificarUsuarios() {
		return mntmBuscarmodificarUsuarios;
	}

	public void setMntmBuscarmodificarUsuarios(JMenuItem mntmBuscarmodificarUsuarios) {
		this.mntmBuscarmodificarUsuarios = mntmBuscarmodificarUsuarios;
	}

	public JMenuItem getMntmCrearUsuario() {
		return mntmCrearUsuario;
	}

	public void setMntmCrearUsuario(JMenuItem mntmCrearUsuario) {
		this.mntmCrearUsuario = mntmCrearUsuario;
	}
}
