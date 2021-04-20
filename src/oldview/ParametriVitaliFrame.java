package oldview;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ParametriVitaliFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	BorderLayout mainLayout = new BorderLayout(2, 2);
	private static String titolo = "Parametri Vitali";
	Container frmContentPaneContainer = new Container();
	
	//{{ PANNELLO INFO
	private JPanel pannelloInfo = new JPanel();
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel txtNome;
	private JLabel txtCognome;
	//}}
	
	//{{ PANNELLO PARAMETRI
	private JPanel pannelloParametri = new JPanel();
	private JLabel lblPressioneSistolica;
	private JLabel lblPressioneDiastolica;
	private JLabel lblFrequenzaCardiaca;
	private JLabel lblTemperaturaCorporea;
	private JLabel txtPressioneSistolica;
	private JLabel txtPressioneDiastolica;
	private JLabel txtFrequenzaCardiaca;
	private JLabel txtTemperaturaCorporea;
	private Font fontGrosso = new Font("Arial", Font.BOLD, 50);
	private Font fontBottoni = new Font("Arial", Font.BOLD, 20);
	//}}
	
	public ParametriVitaliFrame() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//CONTAINER PRINCIPALE
		frmContentPaneContainer = this.getContentPane();
		frmContentPaneContainer.setLayout(mainLayout);
		frmContentPaneContainer.add(pannelloInfo, BorderLayout.NORTH);
		frmContentPaneContainer.add(pannelloParametri, BorderLayout.CENTER);
		
		//{{ PANNELLO INFO
		pannelloInfo.setPreferredSize(new Dimension(795, 50));
		pannelloInfo.setLayout(new GridBagLayout());
		GridBagConstraints gbcInfo = new GridBagConstraints();
		gbcInfo.ipady = 0;
		gbcInfo.ipadx = 10;
		
		lblNome = new JLabel("Nome:");
		gbcInfo.gridx = 0;
		gbcInfo.gridy = 0;
		pannelloInfo.add(lblNome, gbcInfo);
		txtNome = new JLabel();
		gbcInfo.gridx = 1;
		gbcInfo.gridy = 0;
		pannelloInfo.add(txtNome, gbcInfo);
		
		lblCognome = new JLabel("Cognome:");
		gbcInfo.gridx = 2;
		gbcInfo.gridy = 0;
		pannelloInfo.add(lblCognome, gbcInfo);
		txtCognome = new JLabel();
		gbcInfo.gridx = 3;
		gbcInfo.gridy = 0;
		pannelloInfo.add(txtCognome, gbcInfo);
		//}}
		
		//{{ PANNELLO PARAMETRI
		pannelloParametri.setPreferredSize(new Dimension(795, 455));
		pannelloParametri.setLayout(new GridBagLayout());
		GridBagConstraints gbcDati = new GridBagConstraints();
		gbcDati.weighty = 0.1;
		gbcDati.weightx = 1;
		//gbcDati.anchor = GridBagConstraints.HORIZONTAL;
		
		
		lblPressioneSistolica = new JLabel("Pressione Sistolica:");
		lblPressioneSistolica.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 0;
		pannelloParametri.add(lblPressioneSistolica, gbcDati);
		txtPressioneSistolica = new JLabel();
		txtPressioneSistolica.setFont(fontGrosso);
		gbcDati.gridx = 1;
		gbcDati.gridy = 0;
		pannelloParametri.add(txtPressioneSistolica, gbcDati);
		
		lblPressioneDiastolica = new JLabel("Pressione Diastolica:");
		lblPressioneDiastolica.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 1;
		pannelloParametri.add(lblPressioneDiastolica, gbcDati);
		txtPressioneDiastolica = new JLabel();
		txtPressioneDiastolica.setFont(fontGrosso);
		gbcDati.gridx = 1;
		gbcDati.gridy = 1;
		pannelloParametri.add(txtPressioneDiastolica, gbcDati);
		
		lblFrequenzaCardiaca = new JLabel("Frequenza Cardiaca:");
		lblFrequenzaCardiaca.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 2;
		pannelloParametri.add(lblFrequenzaCardiaca, gbcDati);
		txtFrequenzaCardiaca = new JLabel();
		txtFrequenzaCardiaca.setFont(fontGrosso);
		gbcDati.gridx = 1;
		gbcDati.gridy = 2;
		pannelloParametri.add(txtFrequenzaCardiaca, gbcDati);
		
		lblTemperaturaCorporea = new JLabel("Temperatura Corporea:");
		lblTemperaturaCorporea.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 3;
		pannelloParametri.add(lblTemperaturaCorporea, gbcDati);
		txtTemperaturaCorporea = new JLabel();
		txtTemperaturaCorporea.setFont(fontGrosso);
		gbcDati.gridx = 1;
		gbcDati.gridy = 3;
		pannelloParametri.add(txtTemperaturaCorporea, gbcDati);
		//}}
		
		this.setSize(450, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(450, 500));
		this.setVisible(false);
	}


	//{{ GETTERS AND SETTERS
	public JLabel getTxtNome() {
		return txtNome;
	}
	
	public JLabel getTxtCognome() {
		return txtCognome;
	}
	
	public JLabel getTxtPressioneSistolica() {
		return txtPressioneSistolica;
	}
	
	public JLabel getTxtPressioneDiastolica() {
		return txtPressioneDiastolica;
	}
	
	public JLabel getTxtFrequenzaCardiaca() {
		return txtFrequenzaCardiaca;
	}
	
	public JLabel getTxtTemperaturaCorporea() {
		return txtTemperaturaCorporea;
	}

	//}}
}
