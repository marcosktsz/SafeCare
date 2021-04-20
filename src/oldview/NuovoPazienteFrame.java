package oldview;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NuovoPazienteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagLayout mainLayout = new GridBagLayout();
	private static String titolo = "Nuovo Patient";
	Container frmContentPaneContainer = new Container();
	private JPanel pannello = new JPanel();
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblCodiceUnicoSanitario;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtCodiceUnicoSanitario;
	private JButton btnAddPaziente;
	private JButton btnChiudi;

	//{{ Listeners
	public void addAddButtonListener(ActionListener src) {
		btnAddPaziente.addActionListener(src);
	}
	
	public void addChiudiButtonListener(ActionListener src) {
		btnChiudi.addActionListener(src);
	}
	//}}
	
	public NuovoPazienteFrame() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//CONTAINER PRINCIPALE
		pannello.setPreferredSize(new Dimension(375, 200));
		
		frmContentPaneContainer = this.getContentPane();
		frmContentPaneContainer.setLayout(mainLayout);
		frmContentPaneContainer.add(pannello);
		pannello.setLayout(mainLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 70;
		gbc.weighty = 0.0001;
		
		
		lblNome = new JLabel("Nome");
		gbc.gridx = 0;
		gbc.gridy = 0;
		pannello.add(lblNome, gbc);
		txtNome = new JTextField();
		txtNome.setPreferredSize(new Dimension(200, 25));
		gbc.gridx = 1;
		gbc.gridy = 0;
		pannello.add(txtNome, gbc);
		
		lblCognome = new JLabel("Cognome");
		gbc.gridx = 0;
		gbc.gridy = 1;
		pannello.add(lblCognome, gbc);
		txtCognome = new JTextField();
		txtCognome.setPreferredSize(new Dimension(200, 25));
		gbc.gridx = 1;
		gbc.gridy = 1;
		pannello.add(txtCognome, gbc);
		
		lblCodiceUnicoSanitario = new JLabel("Codice Unico Sanitario");
		gbc.gridx = 0;
		gbc.gridy = 2;
		pannello.add(lblCodiceUnicoSanitario, gbc);
		txtCodiceUnicoSanitario = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		pannello.add(txtCodiceUnicoSanitario, gbc);
		
		btnAddPaziente = new JButton("Aggiungi");
		gbc.gridx = 0;
		gbc.gridy = 3;
		pannello.add(btnAddPaziente, gbc);
		
		btnChiudi = new JButton("Chiudi");
		gbc.gridx = 1;
		gbc.gridy = 3;
		pannello.add(btnChiudi, gbc);
		
		//Frame settings
		this.setSize(450, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(450, 250));
		this.pack();
		this.setVisible(false);
	}


	//{{ GETTERS AND SETTERS
	public String getTxtNome() {
		return txtNome.getText();
	}
	
	public String getTxtCognome() {
		return txtCognome.getText();
	}

	public String getTxtCodiceUnicoSanitario() {
		return txtCodiceUnicoSanitario.getText();
	}
	
	
	//}}
}
