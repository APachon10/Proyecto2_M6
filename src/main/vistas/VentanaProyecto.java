package main.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.modelos.Proyectos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class VentanaProyecto extends JFrame {

	private JPanel contentPane;
	private JTextField NameProject;
	private JInternalFrame internalFrame ;
	private JDesktopPane desktopPane;
	private JLabel lblNombreProyecto,lblDescripcion,lblScrumMaster,lblProductoOwner,ProjectoExistente;
	private JComboBox comboBox_1,comboBox;
	private JTextArea DescArea;
	private JButton btnAadir;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaProyecto frame = new VentanaProyecto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VentanaProyecto() {
		setTitle("Nuevo Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
					.addGap(40))
		);
		
		internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(10, 11, 579, 309);
		desktopPane.add(internalFrame);
		
		lblNombreProyecto = new JLabel("Nombre Proyecto");
		
		lblDescripcion = new JLabel("Descripcion");
		
		NameProject = new JTextField();
		NameProject.setColumns(10);
		
		lblScrumMaster = new JLabel("Scrum Master");
		
		lblProductoOwner = new JLabel("Producto Owner");
		
		comboBox_1 = new JComboBox();
		
		comboBox = new JComboBox();
		
		DescArea = new JTextArea();
		
		JButton btnAadir = new JButton("A\u00F1adir");
		
		ProjectoExistente = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(internalFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblScrumMaster)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblProductoOwner)
									.addGap(26)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnAadir)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
							.addComponent(DescArea, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombreProyecto)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(ProjectoExistente, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
								.addComponent(NameProject, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
							.addGap(17)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreProyecto)
						.addComponent(NameProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ProjectoExistente, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(DescArea, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescripcion))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScrumMaster)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductoOwner))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAadir)
					.addContainerGap())
		);
		internalFrame.getContentPane().setLayout(groupLayout);
		internalFrame.setVisible(true);
		contentPane.setLayout(gl_contentPane);
	}
	public void añadirProjecto() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("scrumprojectmanager");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
		Proyectos p = new Proyectos();
		if (p.getProject_name().equals(NameProject.getText())) {
			ProjectoExistente.setText("El Proyecto" +p.getProject_name()+" ya existe ");
		}else {
			p.setProjectID(1);
			p.setProject_name(NameProject.getText());
			p.setDescripcion(DescArea.getText());
		}
		entityManager.close();
		factory.close();
	}
}
