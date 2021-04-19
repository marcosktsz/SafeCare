package view;

import java.awt.BorderLayout;
import java.util.Date;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import models.Paziente;


public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout mainLayout = new BorderLayout(15, 15);
	private static String titolo = "Hospital";
	Container frmContentPaneContainer = new Container();
	private Font fontBottoni = new Font("Arial", Font.BOLD, 16);
	private Font fontTesto = new Font("Arial", Font.PLAIN, 16);
	private String nomeBottonePremuto;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Date todayDate = new Date();
	
	//{{ PANNELLO LOGO
	private JPanel pannelloLogo = new JPanel();
	private ImageIcon imageIcon = new ImageIcon(new ImageIcon("assets//logo.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
	private JLabel imgJLabel;
	private JLabel infoLbl;
	private JLabel lblBenvenuto;
	private JLabel lblProfessione;
	private JLabel lblNomePersonale;
	//}}
	
	//{{ PANNELLO ALLARMI
	private JPanel pannelloAllarmi = new JPanel();
	private DefaultTableModel tableModelAllarmi;
	private JTable tabellaAllarmi;
	private JScrollPane scrollPaneAllarmi;
	private JButton bottoneAllarme;
	//}}	
	
	//{{ PANNELLO LISTA PAZIENTI
	private JPanel pannelloListaPazienti = new JPanel();
	private JLabel lblPazienti;
	private JScrollPane scrollPane;
	private DefaultListModel<Paziente> listModel;
	private JList<Paziente> list;
	//}}
	
	//{{ PANNELLO BOTTONI
	private JPanel pannelloBottoni = new JPanel();
	private JButton btnPersonaleInfermieristico;
	private JButton btnPersonaleMedico;
	private JButton btnPrimario;
	//}}
	
	//{{ PANNELLO DATI PAZIENTI
	private JPanel pannelloDati = new JPanel();
	private JTextField txtDataDiNascita;
	private JTextField txtDataRicovero;
	private JTextField txtDataRilascio;
	private JTextField txtNome;
	private JTextField txtCodiceUnicoSanitario;
	private JTextField txtCognome;
	private JTextField txtLuogoDiNascita;
	private JTextField txtSalone;
	private JTextField txtDiagnosi;
	private JTextField txtDottore;
	private JLabel lblCodiceUnicoSanitario;
	private JLabel lblDataRilascio;
	private JLabel lblStanza;
	private JLabel lblDiagnosi;
	private JLabel lblDottore;
	private JLabel lblStato;
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblLuogoDiNascita;
	private JLabel lblDataDiNascita;
	private JLabel lblDataRicovero;
	private JLabel lblStatoPaziente;
	private JLabel lblPressioneSistolica;
	private JTextField txtPressioneSistolica;
	private JLabel lblPressioneDiastolica;
	private JTextField txtPressioneDiastolica;
	private JLabel lblFrequenzaCardiaca;
	private JTextField txtFrequenzaCardiaca;
	private JLabel lblTemperaturaCorporea;
	private JTextField txtTemperaturaCorporea;
	private JLabel lblAggiornamentoDati;
	
	//}}
	
	//{{ PANNELLO LOGIN
	private JPanel pannelloLogin = new JPanel();
	private JLabel lblUtente;
	private JLabel lblPassword;
	private JTextField txtUtente;
	private JPasswordField txtPasswordField;
	//}}
	
	//{{ PANNELLO INFERMIERE
	private JPanel pannelloInfermiere = new JPanel();
	private JButton btnNuovoPaziente;
	private JButton btnModificaDatiInfermiere;
	private JButton btnSomministraFarmaci;
	private JButton btnVisualizzaPrescrizioneInfermiere;
	private JButton btnVisualizzaParametriVitaliInfermiere;
	private JButton btnLogoutInfermiere;
	//}}
	
	//{{ PANNELLO MEDICO
	private JPanel pannelloMedico = new JPanel();
	private JButton btnModificaDatiMedico;
	private JButton btnPrescrizione;
	private JButton btnVisualizzaParametriVitaliMedico;
	private JButton btnLogoutMedico;
	//}}
		
	//{{ PANNELLO PRIMARIO
	private JPanel pannelloPrimario = new JPanel();
	private JButton btnModificaDatiPrimario;
	private JButton btnRilascioPaziente;
	private JButton btnStampaReport;
	private JButton btnVisualizzaPrescrizionePrimario;
	private JButton btnVisualizzaParametriVitaliPrimario;
	private JButton btnLogoutPrimario;
	//}}
	
	//{{ PANNELLO BOTTONI LOGIN
	private JPanel pannelloBottoniLogin = new JPanel();
	private JButton btnLogin;
	private JButton btnIndietro;	
	private JButton btnRegistrati;
	//}}
	
	//{{ PANNELLO REGISTRAZIONE
	private JPanel pannelloRegistrazione = new JPanel();
	private JLabel lblNomeRegistrazione;
	private JLabel lblCognomeRegistrazione;
	private JLabel lblCodiceDiIdentificazione;
	private JLabel lblUtenteRegistrazione;
	private JLabel lblPasswordRegistrazione;
	private JTextField txtNomeRegistrazione;
	private JTextField txtCognomeRegistrazione;
	private JTextField txtCodiceDiIdentificazioneRegistrazione;
	private JTextField txtUtenteRegistrazione;
	private JPasswordField txtPasswordRegistrazione;
	//}}
	
	//{{ Listeners	
	public void addApriSchermataLoginListener(ActionListener src) {
		btnPersonaleInfermieristico.addActionListener(src);
		btnPersonaleMedico.addActionListener(src);
		btnPrimario.addActionListener(src);
	}
	
	public void addIndietroButtonListener(ActionListener src) {
		btnIndietro.addActionListener(src);
	}
	
	public void addListMouseListener(MouseListener src) {
		list.addMouseListener(src);
	}
	
	public void addLoginButtonListener(ActionListener src) {
		btnLogin.addActionListener(src);
	}
	
	public void addNuovoPazienteListener(ActionListener src) {
		btnNuovoPaziente.addActionListener(src);
	}
	
	public void addModificaDatiListener(ActionListener src) {
		btnModificaDatiInfermiere.addActionListener(src);
		btnModificaDatiMedico.addActionListener(src);
		btnModificaDatiPrimario.addActionListener(src);
	}
	
	public void addLogoutButtonListener(ActionListener src) {
		btnLogoutInfermiere.addActionListener(src);
		btnLogoutMedico.addActionListener(src);
		btnLogoutPrimario.addActionListener(src);
	}
	
	public void addPrescrizioneButtonListener(ActionListener src) {
		btnPrescrizione.addActionListener(src);
	}
	
	public void addVisualizzaPrescrizioneButtonListener(ActionListener src) {
		btnVisualizzaPrescrizioneInfermiere.addActionListener(src);
		btnVisualizzaPrescrizionePrimario.addActionListener(src);
	}
	
	public void addSomministrazioneFarmaciButtonListener(ActionListener src) {
		btnSomministraFarmaci.addActionListener(src);
	}
	
	public void addRegistratiButtonListener(ActionListener src) {
		btnRegistrati.addActionListener(src);
	}
	
	public void addRilasciaPazienteButtonListener(ActionListener src) {
		btnRilascioPaziente.addActionListener(src);
	}
	
	public void addVisualizzaParametriVitaliListener(ActionListener src) {
		btnVisualizzaParametriVitaliInfermiere.addActionListener(src);
		btnVisualizzaParametriVitaliMedico.addActionListener(src);
		btnVisualizzaParametriVitaliPrimario.addActionListener(src);
	}
	
	public void addButtonAllarmiListener(ActionListener src) {
		bottoneAllarme.addActionListener(src);
	}
	
	public void addButtonStampaReportListener(ActionListener src) {
		btnStampaReport.addActionListener(src);
	}
	
	//}}
		
	public MainFrame() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//CONTAINER PRINCIPALE
		frmContentPaneContainer = this.getContentPane();
		frmContentPaneContainer.setLayout(mainLayout);
		frmContentPaneContainer.add(pannelloLogo, BorderLayout.NORTH);
		frmContentPaneContainer.add(pannelloAllarmi, BorderLayout.SOUTH);
		frmContentPaneContainer.add(pannelloListaPazienti, BorderLayout.WEST);
		frmContentPaneContainer.add(pannelloDati, BorderLayout.CENTER);
		frmContentPaneContainer.add(pannelloBottoni, BorderLayout.EAST);
		
		//{{ PANNELLO LOGO
		pannelloLogo.setPreferredSize(new Dimension(1080, 85)); //85
		infoLbl = new JLabel("Hospital");
		infoLbl.setFont(new Font("Arial", Font.BOLD, 50));
		// = new JSeparator(SwingConstants.VERTICAL);
		imgJLabel = new JLabel(imageIcon);
		imgJLabel.setIcon(imageIcon);
		pannelloLogo.add(imgJLabel); 
		pannelloLogo.add(infoLbl);
		
		
		
		lblBenvenuto = new JLabel("Benvenuto");
		lblBenvenuto.setFont(new Font("Arial", Font.BOLD, 20));
		pannelloLogo.add(lblBenvenuto);
		
		lblProfessione = new JLabel();
		lblProfessione.setFont(new Font("Arial", Font.BOLD, 20));
		pannelloLogo.add(lblProfessione);
		
		lblNomePersonale = new JLabel();
		lblNomePersonale.setFont(new Font("Arial", Font.BOLD, 20));
		pannelloLogo.add(lblNomePersonale);
		//}}
		//{{ PANNELLO LISTA PAZIENTI
		pannelloListaPazienti.setPreferredSize(new Dimension(190, 455));
		pannelloListaPazienti.setLayout(new BorderLayout());
		
		lblPazienti = new JLabel("Pazienti", SwingConstants.CENTER);
		lblPazienti.setFont(fontBottoni);
		pannelloListaPazienti.add(lblPazienti, BorderLayout.NORTH);
		
		listModel = new DefaultListModel<Paziente>();
		list = new JList<Paziente>(listModel);
		list.setFont(fontBottoni);
		scrollPane = new JScrollPane(list);
		list.setBackground(Color.WHITE);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		pannelloListaPazienti.add(scrollPane, BorderLayout.CENTER);
		//}}
		//{{ PANNELLO BOTTONI
		pannelloBottoni.setPreferredSize(new Dimension(178, 455));
		pannelloBottoni.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 65;
		gbc.ipady = 120;
		
		btnPersonaleInfermieristico = new JButton("Infermiere");
		btnPersonaleInfermieristico.setFont(fontBottoni);
		gbc.gridx = 0;
		gbc.gridy = 0;
		pannelloBottoni.add(btnPersonaleInfermieristico, gbc);
		
		btnPersonaleMedico = new JButton("Medico");
		btnPersonaleMedico.setFont(fontBottoni);
		gbc.gridx = 0;
		gbc.gridy = 6;
		pannelloBottoni.add(btnPersonaleMedico, gbc);
		
		btnPrimario = new JButton("Primario");
		btnPrimario.setFont(fontBottoni);
		gbc.gridx = 0;
		gbc.gridy = 12;
		pannelloBottoni.add(btnPrimario, gbc);
		//}}
		//{{ PANNELLO DATI
		pannelloDati.setPreferredSize(new Dimension(795, 455));
		pannelloDati.setLayout(new GridBagLayout());
		GridBagConstraints gbcDati = new GridBagConstraints();
		gbcDati.weighty = 0.1;
		gbcDati.weightx = 1;
		gbcDati.anchor = GridBagConstraints.WEST;
		
		lblStato = new JLabel("Stato:");
		lblStato.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 0;
		pannelloDati.add(lblStato, gbcDati);
		lblStatoPaziente = new JLabel();
		lblStatoPaziente.setFont(new Font("Arial", Font.BOLD, 22));
		gbcDati.gridx = 1;
		gbcDati.gridy = 0;
		pannelloDati.add(lblStatoPaziente, gbcDati);
		
		lblAggiornamentoDati = new JLabel("*Dati aggiornati degli ultimi 15 minuti");
		lblAggiornamentoDati.setFont(new Font("Arial", Font.PLAIN, 12));
		gbcDati.gridx = 3;
		gbcDati.gridy = 0;
		pannelloDati.add(lblAggiornamentoDati, gbcDati);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 1;
		pannelloDati.add(lblNome, gbcDati);
		txtNome = new JTextField();
		txtNome.setFont(fontTesto);
		gbcDati.gridx = 1;
		gbcDati.gridy = 1;
		txtNome.setPreferredSize(new Dimension(200, 25));
		txtNome.setEditable(false);
		pannelloDati.add(txtNome, gbcDati);
		
		lblCognome = new JLabel("Cognome:");
		lblCognome.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 2;
		pannelloDati.add(lblCognome, gbcDati);
		txtCognome = new JTextField();
		txtCognome.setFont(fontTesto);
		gbcDati.gridx = 1;
		gbcDati.gridy = 2;
		txtCognome.setPreferredSize(new Dimension(200, 25));
		txtCognome.setEditable(false);
		pannelloDati.add(txtCognome, gbcDati);
		
		lblLuogoDiNascita = new JLabel("Luogo di nascità:");
		lblLuogoDiNascita.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 3;
		pannelloDati.add(lblLuogoDiNascita, gbcDati);
		txtLuogoDiNascita = new JTextField();
		txtLuogoDiNascita.setFont(fontTesto);
		gbcDati.gridx = 1;
		gbcDati.gridy = 3;
		txtLuogoDiNascita.setPreferredSize(new Dimension(200, 25));
		txtLuogoDiNascita.setEditable(false);
		pannelloDati.add(txtLuogoDiNascita, gbcDati);
		
		
		lblDataDiNascita = new JLabel("Data di nascità:");
		lblDataDiNascita.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 4;
		pannelloDati.add(lblDataDiNascita, gbcDati);
		txtDataDiNascita = new JTextField();
		txtDataDiNascita.setFont(fontTesto);
		gbcDati.gridx = 1;
		gbcDati.gridy = 4;
		txtDataDiNascita.setPreferredSize(new Dimension(200, 25));
		txtDataDiNascita.setEditable(false);
		pannelloDati.add(txtDataDiNascita, gbcDati);
		
		lblStanza = new JLabel("Stanza:");
		lblStanza.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 5;
		pannelloDati.add(lblStanza, gbcDati);
		txtSalone = new JTextField();
		txtSalone.setFont(fontTesto);
		gbcDati.gridx = 1;
		gbcDati.gridy = 5;
		txtSalone.setPreferredSize(new Dimension(200, 25));
		txtSalone.setEditable(false);
		pannelloDati.add(txtSalone, gbcDati);
		
		lblDiagnosi = new JLabel("Diagnosi:");
		lblDiagnosi.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 6;
		pannelloDati.add(lblDiagnosi, gbcDati);
		gbcDati.gridx = 1;
		gbcDati.gridy = 6;
		txtDiagnosi = new JTextField();
		txtDiagnosi.setFont(fontTesto);
		txtDiagnosi.setPreferredSize(new Dimension(200, 25));
		txtDiagnosi.setEditable(false);
		pannelloDati.add(txtDiagnosi, gbcDati);
		
		lblDottore = new JLabel("Dottore:");
		lblDottore.setFont(fontBottoni);
		gbcDati.gridx = 0;
		gbcDati.gridy = 7;
		pannelloDati.add(lblDottore, gbcDati);
		gbcDati.gridx = 1;
		gbcDati.gridy = 7;
		txtDottore = new JTextField();
		txtDottore.setFont(fontTesto);
		txtDottore.setPreferredSize(new Dimension(200, 25));
		txtDottore.setEditable(false);
		pannelloDati.add(txtDottore, gbcDati);
		
		lblCodiceUnicoSanitario = new JLabel("Codice Unico Sanitario:");
		lblCodiceUnicoSanitario.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 1;
		pannelloDati.add(lblCodiceUnicoSanitario, gbcDati);
		txtCodiceUnicoSanitario = new JTextField();
		txtCodiceUnicoSanitario.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 1;
		txtCodiceUnicoSanitario.setPreferredSize(new Dimension(200, 25));
		txtCodiceUnicoSanitario.setEditable(false);
		pannelloDati.add(txtCodiceUnicoSanitario, gbcDati);
		
		lblDataRicovero = new JLabel("Data Ricovero:");
		lblDataRicovero.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 2;
		pannelloDati.add(lblDataRicovero, gbcDati);
		txtDataRicovero = new JTextField();
		txtDataRicovero.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 2;
		txtDataRicovero.setPreferredSize(new Dimension(200, 25));
		txtDataRicovero.setEditable(false);
		pannelloDati.add(txtDataRicovero, gbcDati);
		
		lblDataRilascio = new JLabel("Data Rilascio:");
		lblDataRilascio.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 3;
		pannelloDati.add(lblDataRilascio, gbcDati);
		txtDataRilascio = new JTextField();
		txtDataRilascio.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 3;
		txtDataRilascio.setPreferredSize(new Dimension(200, 25));
		txtDataRilascio.setEditable(false);
		pannelloDati.add(txtDataRilascio, gbcDati);
		
		lblPressioneSistolica = new JLabel("*Pressione Sistolica");
		lblPressioneSistolica.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 4;
		pannelloDati.add(lblPressioneSistolica, gbcDati);
		txtPressioneSistolica = new JTextField();
		txtPressioneSistolica.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 4;
		txtPressioneSistolica.setPreferredSize(new Dimension(200, 25));
		txtPressioneSistolica.setEditable(false);
		pannelloDati.add(txtPressioneSistolica, gbcDati);
		
		lblPressioneDiastolica = new JLabel("*Pressione Diastolica");
		lblPressioneDiastolica.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 5;
		pannelloDati.add(lblPressioneDiastolica, gbcDati);
		txtPressioneDiastolica = new JTextField();
		txtPressioneDiastolica.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 5;
		txtPressioneDiastolica.setPreferredSize(new Dimension(200, 25));
		txtPressioneDiastolica.setEditable(false);
		pannelloDati.add(txtPressioneDiastolica, gbcDati);
		
		lblFrequenzaCardiaca = new JLabel("*Frequenza Cardiaca");
		lblFrequenzaCardiaca.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 6;
		pannelloDati.add(lblFrequenzaCardiaca, gbcDati);
		txtFrequenzaCardiaca = new JTextField();
		txtFrequenzaCardiaca.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 6;
		txtFrequenzaCardiaca.setPreferredSize(new Dimension(200, 25));
		txtFrequenzaCardiaca.setEditable(false);
		pannelloDati.add(txtFrequenzaCardiaca, gbcDati);
		
		lblTemperaturaCorporea = new JLabel("*Temperatura Corporea");
		lblTemperaturaCorporea.setFont(fontBottoni);
		gbcDati.gridx = 2;
		gbcDati.gridy = 7;
		pannelloDati.add(lblTemperaturaCorporea, gbcDati);
		txtTemperaturaCorporea = new JTextField();
		txtTemperaturaCorporea.setFont(fontTesto);
		gbcDati.gridx = 3;
		gbcDati.gridy = 7;
		txtTemperaturaCorporea.setPreferredSize(new Dimension(200, 25));
		txtTemperaturaCorporea.setEditable(false);
		pannelloDati.add(txtTemperaturaCorporea, gbcDati);
		

		
		
		//}}
		//{{ PANNELLO LOGIN
		pannelloLogin.setPreferredSize(new Dimension(795, 455));
		pannelloLogin.setLayout(new GridBagLayout());
		GridBagConstraints gbcLogin = new GridBagConstraints();
		
		lblUtente = new JLabel("Utente:");
		lblUtente.setFont(fontBottoni);
		gbcLogin.gridx = 2;
		gbcLogin.gridy = 0;
		pannelloLogin.add(lblUtente, gbcLogin);
		txtUtente = new JTextField();
		txtUtente.setPreferredSize(new Dimension(200, 25));
		gbcLogin.gridx = 3;
		gbcLogin.gridy = 0;
		pannelloLogin.add(txtUtente, gbcLogin);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(fontBottoni);
		gbcLogin.gridx = 2;
		gbcLogin.gridy = 1;
		pannelloLogin.add(lblPassword, gbcLogin);
		txtPasswordField = new JPasswordField();
		txtPasswordField.setPreferredSize(new Dimension(200, 25));
		gbcLogin.gridx = 3;
		gbcLogin.gridy = 1;
		pannelloLogin.add(txtPasswordField, gbcLogin);
		//}}
		//{{ PANNELLO BOTTONI LOGIN
		pannelloBottoniLogin.setPreferredSize(new Dimension(178, 455));
		pannelloBottoniLogin.setLayout(new GridBagLayout());
		GridBagConstraints gbcBottoniLogin = new GridBagConstraints();
		gbcBottoniLogin.fill = GridBagConstraints.HORIZONTAL;
		gbcBottoniLogin.ipadx = 65;
		gbcBottoniLogin.ipady = 120;
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(fontBottoni);
		gbcBottoniLogin.gridx = 0;
		gbcBottoniLogin.gridy = 0;
		pannelloBottoniLogin.add(btnLogin, gbcBottoniLogin);
		
		btnRegistrati = new JButton("Registrati");
		btnRegistrati.setFont(fontBottoni);
		gbcBottoniLogin.gridx = 0;
		gbcBottoniLogin.gridy = 1;
		pannelloBottoniLogin.add(btnRegistrati, gbcBottoniLogin);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(fontBottoni);
		gbcBottoniLogin.gridx = 0;
		gbcBottoniLogin.gridy = 2;
		pannelloBottoniLogin.add(btnIndietro, gbcBottoniLogin);
		
		//}}
		
		//{{ PANNELLO INFERMIERE
		pannelloInfermiere.setPreferredSize(new Dimension(178, 455));
		pannelloInfermiere.setLayout(new GridBagLayout());
		GridBagConstraints gbcInfermiere = new GridBagConstraints();
		gbcInfermiere.fill = GridBagConstraints.HORIZONTAL;
		gbcInfermiere.ipadx = 0;
		gbcInfermiere.ipady = 30;
		
		btnNuovoPaziente = new JButton("<html><center>Nuovo<br />Paziente</center></html>");
		btnNuovoPaziente.setFont(fontBottoni);
		gbcInfermiere.gridx = 0;
		gbcInfermiere.gridy = 1;
		pannelloInfermiere.add(btnNuovoPaziente, gbcInfermiere);
		
		btnModificaDatiInfermiere = new JButton("Modifica Dati");
		btnModificaDatiInfermiere.setFont(fontBottoni);
		gbcInfermiere.gridx = 0;
		gbcInfermiere.gridy = 2;
		pannelloInfermiere.add(btnModificaDatiInfermiere, gbcInfermiere);
		
		btnSomministraFarmaci = new JButton("<html><center>Somministrazione<br />farmaci</center></html>");
		btnSomministraFarmaci.setFont(fontBottoni);
		gbcInfermiere.gridx = 0;
		gbcInfermiere.gridy = 3;
		pannelloInfermiere.add(btnSomministraFarmaci, gbcInfermiere);
		
		btnVisualizzaPrescrizioneInfermiere = new JButton("<html><center>Visualizza<br />prescrizione</center></html>");
		btnVisualizzaPrescrizioneInfermiere.setFont(fontBottoni);
		gbcInfermiere.gridx = 0;
		gbcInfermiere.gridy = 4;
		pannelloInfermiere.add(btnVisualizzaPrescrizioneInfermiere, gbcInfermiere);
		
		btnVisualizzaParametriVitaliInfermiere = new JButton("<html><center>Visualizza<br />parametri<br />vitali</center></html>");
		btnVisualizzaParametriVitaliInfermiere.setFont(fontBottoni);
		gbcInfermiere.gridx = 0;
		gbcInfermiere.gridy = 5;
		pannelloInfermiere.add(btnVisualizzaParametriVitaliInfermiere, gbcInfermiere);
		
		btnLogoutInfermiere = new JButton("Logout");
		btnLogoutInfermiere.setFont(fontBottoni);
		gbcInfermiere.gridx = 0;
		gbcInfermiere.gridy = 6;
		pannelloInfermiere.add(btnLogoutInfermiere, gbcInfermiere);
		//}}
		//{{ PANNELLO MEDICO
		pannelloMedico.setPreferredSize(new Dimension(178, 455));
		pannelloMedico.setLayout(new GridBagLayout());
		GridBagConstraints gbcMedico = new GridBagConstraints();
		gbcMedico.fill = GridBagConstraints.HORIZONTAL;
		gbcMedico.ipadx = 0;
		gbcMedico.ipady = 30;
		
		btnModificaDatiMedico = new JButton("Modifica Dati");
		btnModificaDatiMedico.setFont(fontBottoni);
		gbcMedico.gridx = 0;
		gbcMedico.gridy = 0;
		pannelloMedico.add(btnModificaDatiMedico, gbcMedico);
		
		btnPrescrizione = new JButton("Prescrizione");
		btnPrescrizione.setFont(fontBottoni);
		gbcMedico.gridx = 0;
		gbcMedico.gridy = 1;
		pannelloMedico.add(btnPrescrizione, gbcMedico);
		btnVisualizzaParametriVitaliMedico = new JButton("<html><center>Visualizza<br />parametri vitali</center></html>");
		btnVisualizzaParametriVitaliMedico.setFont(fontBottoni);
		gbcMedico.gridx = 0;
		gbcMedico.gridy = 2;
		pannelloMedico.add(btnVisualizzaParametriVitaliMedico, gbcMedico);
		
		btnLogoutMedico = new JButton("Logout");
		btnLogoutMedico.setFont(fontBottoni);
		gbcMedico.gridx = 0;
		gbcMedico.gridy = 3;
		pannelloMedico.add(btnLogoutMedico, gbcMedico);
		//}}
		//{{ PANNELLO PRIMARIO
		pannelloPrimario.setPreferredSize(new Dimension(178, 455));
		pannelloPrimario.setLayout(new GridBagLayout());
		GridBagConstraints gbcPrimario = new GridBagConstraints();
		gbcPrimario.fill = GridBagConstraints.HORIZONTAL;
		gbcPrimario.ipadx = 0;
		gbcPrimario.ipady = 30;
		
		btnRilascioPaziente = new JButton("Rilascia Paziente");
		btnRilascioPaziente.setFont(fontBottoni);
		gbcPrimario.gridx = 0;
		gbcPrimario.gridy = 0;
		pannelloPrimario.add(btnRilascioPaziente, gbcPrimario);
		
		btnModificaDatiPrimario = new JButton("Modifica Dati");
		btnModificaDatiPrimario.setFont(fontBottoni);
		gbcPrimario.gridx = 0;
		gbcPrimario.gridy = 1;
		pannelloPrimario.add(btnModificaDatiPrimario, gbcPrimario);
		
		btnStampaReport = new JButton("Stampa Report");
		btnStampaReport.setFont(fontBottoni);
		gbcPrimario.gridx = 0;
		gbcPrimario.gridy = 2;
		pannelloPrimario.add(btnStampaReport, gbcPrimario);
		
		btnVisualizzaPrescrizionePrimario = new JButton("<html><center>Visualizza<br />prescrizione</center></html>");
		btnVisualizzaPrescrizionePrimario.setFont(fontBottoni);
		gbcPrimario.gridx = 0;
		gbcPrimario.gridy = 3;
		pannelloPrimario.add(btnVisualizzaPrescrizionePrimario, gbcPrimario);
		
		btnVisualizzaParametriVitaliPrimario = new JButton("<html><center>Visualizza<br />parametri vitali</center></html>");
		btnVisualizzaParametriVitaliPrimario.setFont(fontBottoni);
		gbcPrimario.gridx = 0;
		gbcPrimario.gridy = 4;
		pannelloPrimario.add(btnVisualizzaParametriVitaliPrimario, gbcPrimario);
		
		btnLogoutPrimario = new JButton("Logout");
		btnLogoutPrimario.setFont(fontBottoni);
		gbcPrimario.gridx = 0;
		gbcPrimario.gridy = 5;
		pannelloPrimario.add(btnLogoutPrimario, gbcPrimario);
		//}}
		
		//{{ PANNELLO ALLARMI
				
		pannelloAllarmi.setPreferredSize(new Dimension(1080, 170));
		pannelloAllarmi.setLayout(new FlowLayout());
		
		tableModelAllarmi = new DefaultTableModel();
		tableModelAllarmi.addColumn("Nome");
		tableModelAllarmi.addColumn("Cognome");
		tableModelAllarmi.addColumn("Stanza");
		tableModelAllarmi.addColumn("Allarme");
		tableModelAllarmi.addColumn("Livello");
		tableModelAllarmi.addColumn("");
		
		tabellaAllarmi = new JTable(tableModelAllarmi);
		tabellaAllarmi.setPreferredSize(new Dimension(1080, 130));
		scrollPaneAllarmi = new JScrollPane(tabellaAllarmi);
		scrollPaneAllarmi.setPreferredSize(new Dimension(1080, 150));
		pannelloAllarmi.add(scrollPaneAllarmi);
		
		bottoneAllarme = new JButton("STOP");
		pannelloAllarmi.add(bottoneAllarme);
			
		//}}
		
		//{{ PANNELLO REGISTRAZIONE
		pannelloRegistrazione.setPreferredSize(new Dimension(795, 455));
		pannelloRegistrazione.setLayout(new GridBagLayout());
		GridBagConstraints gbcRegistrazione = new GridBagConstraints();
		
		lblUtenteRegistrazione = new JLabel("Utente:");
		lblUtenteRegistrazione.setFont(fontBottoni);
		gbcRegistrazione.gridx = 2;
		gbcRegistrazione.gridy = 0;
		pannelloRegistrazione.add(lblUtenteRegistrazione, gbcRegistrazione);
		txtUtenteRegistrazione = new JTextField();
		gbcRegistrazione.gridx = 3;
		gbcRegistrazione.gridy = 0;
		txtUtenteRegistrazione.setPreferredSize(new Dimension(200, 25));
		pannelloRegistrazione.add(txtUtenteRegistrazione, gbcRegistrazione);
		
		lblPasswordRegistrazione = new JLabel("Password:");
		lblPasswordRegistrazione.setFont(fontBottoni);
		gbcRegistrazione.gridx = 2;
		gbcRegistrazione.gridy = 1;
		pannelloRegistrazione.add(lblPasswordRegistrazione, gbcRegistrazione);
		txtPasswordRegistrazione = new JPasswordField();
		txtPasswordRegistrazione.setPreferredSize(new Dimension(200, 25));
		gbcRegistrazione.gridx = 3;
		gbcRegistrazione.gridy = 1;
		pannelloRegistrazione.add(txtPasswordRegistrazione, gbcRegistrazione);
		
		lblNomeRegistrazione = new JLabel("Nome:");
		lblNomeRegistrazione.setFont(fontBottoni);
		gbcRegistrazione.gridx = 2;
		gbcRegistrazione.gridy = 2;
		pannelloRegistrazione.add(lblNomeRegistrazione, gbcRegistrazione);
		txtNomeRegistrazione = new JTextField();
		txtNomeRegistrazione.setPreferredSize(new Dimension(200, 25));
		gbcRegistrazione.gridx = 3;
		gbcRegistrazione.gridy = 2;
		pannelloRegistrazione.add(txtNomeRegistrazione, gbcRegistrazione);
		
		lblCognomeRegistrazione = new JLabel("Cognome:");
		lblCognomeRegistrazione.setFont(fontBottoni);
		gbcRegistrazione.gridx = 2;
		gbcRegistrazione.gridy = 3;
		pannelloRegistrazione.add(lblCognomeRegistrazione, gbcRegistrazione);
		txtCognomeRegistrazione = new JTextField();
		txtCognomeRegistrazione.setPreferredSize(new Dimension(200, 25));
		gbcRegistrazione.gridx = 3;
		gbcRegistrazione.gridy = 3;
		pannelloRegistrazione.add(txtCognomeRegistrazione, gbcRegistrazione);
		
		lblCodiceDiIdentificazione = new JLabel("Codice di identificazione:");
		lblCodiceDiIdentificazione.setFont(fontBottoni);
		gbcRegistrazione.gridx = 2;
		gbcRegistrazione.gridy = 4;
		pannelloRegistrazione.add(lblCodiceDiIdentificazione, gbcRegistrazione);
		txtCodiceDiIdentificazioneRegistrazione = new JTextField();
		txtCodiceDiIdentificazioneRegistrazione.setPreferredSize(new Dimension(200, 25));
		gbcRegistrazione.gridx = 3;
		gbcRegistrazione.gridy = 4;
		pannelloRegistrazione.add(txtCodiceDiIdentificazioneRegistrazione, gbcRegistrazione);
		//}}

		//Frame settings
		this.setSize(1250, 760);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1250, 780));
		this.pack();
		this.setVisible(true);
	}

	//{{ GETTERS AND SETTERS
	public void setList(ArrayList<Paziente> list) {
		for(Paziente paziente: list) {
			this.listModel.addElement(paziente);
		}
	}

	public JList<Paziente> getList() {
		return list;
	}

	public void setTxtDataDiNascita(String txtDataDiNascita) {
		this.txtDataDiNascita.setText(txtDataDiNascita);
	}

	public void setTxtDataRicovero(String txtDataRicovero) {
		this.txtDataRicovero.setText(txtDataRicovero);
	}

	public void setTxtDataRilascio(String txtDataRilascio) {
		this.txtDataRilascio.setText(txtDataRilascio);
	}

	public void setTxtNome(String txtNome) {
		this.txtNome.setText(txtNome);
	}

	public void setTxtCodiceUnicoSanitario(String txtCodiceUnicoSanitario) {
		this.txtCodiceUnicoSanitario.setText(txtCodiceUnicoSanitario);
	}

	public void setTxtCognome(String txtCognome) {
		this.txtCognome.setText(txtCognome);
	}

	public void setTxtLuogoDiNascita(String txtLuogoDiNascita) {
		this.txtLuogoDiNascita.setText(txtLuogoDiNascita);
	}

	public void setTxtSalone(String txtSalone) {
		this.txtSalone.setText(txtSalone);
	}

	public void setTxtDiagnosi(String txtDiagnosi) {
		this.txtDiagnosi.setText(txtDiagnosi);
	}

	public void setTxtDottore(String txtDottore) {
		this.txtDottore.setText(txtDottore);
	}

	public void setLblStatoPaziente(String lblStatoPaziente) {
		this.lblStatoPaziente.setText(lblStatoPaziente);
	}

	public Container getFrmContentPaneContainer() {
		return frmContentPaneContainer;
	}

	public JPanel getPannelloDati() {
		return pannelloDati;
	}

	public JPanel getPannelloLogin() {
		return pannelloLogin;
	}

	public String getNomeBottonePremuto() {
		return nomeBottonePremuto;
	}

	public JTextField getTxtUtente() {
		return txtUtente;
	}

	public JPasswordField getTxtPasswordField() {
		return txtPasswordField;
	}

	public JPanel getPannelloInfermiere() {
		return pannelloInfermiere;
	}

	public JPanel getPannelloMedico() {
		return pannelloMedico;
	}

	public JPanel getPannelloPrimario() {
		return pannelloPrimario;
	}

	public JPanel getPannelloBottoni() {
		return pannelloBottoni;
	}

	public JPanel getPannelloBottoniLogin() {
		return pannelloBottoniLogin;
	}

	public DefaultListModel<Paziente> getListModel() {
		return listModel;
	}

	public JTextField getTxtDataDiNascita() {
		return txtDataDiNascita;
	}

	public JTextField getTxtDataRicovero() {
		return txtDataRicovero;
	}

	public JTextField getTxtDataRilascio() {
		return txtDataRilascio;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtCodiceUnicoSanitario() {
		return txtCodiceUnicoSanitario;
	}

	public JTextField getTxtCognome() {
		return txtCognome;
	}

	public JTextField getTxtLuogoDiNascita() {
		return txtLuogoDiNascita;
	}

	public JTextField getTxtSalone() {
		return txtSalone;
	}

	public JTextField getTxtDiagnosi() {
		return txtDiagnosi;
	}

	public JTextField getTxtDottore() {
		return txtDottore;
	}

	public JButton getBtnModificaDatiInfermiere() {
		return btnModificaDatiInfermiere;
	}

	public JButton getBtnModificaDatiMedico() {
		return btnModificaDatiMedico;
	}

	public JButton getBtnModificaDatiPrimario() {
		return btnModificaDatiPrimario;
	}

	public JLabel getLblStatoPaziente() {
		return lblStatoPaziente;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public JButton getBtnRegistrati() {
		return btnRegistrati;
	}

	public JPanel getPannelloRegistrazione() {
		return pannelloRegistrazione;
	}

	public void setNomeBottonePremuto(String nomeBottonePremuto) {
		this.nomeBottonePremuto = nomeBottonePremuto;
	}

	public JTextField getTxtNomeRegistrazione() {
		return txtNomeRegistrazione;
	}

	public JTextField getTxtCognomeRegistrazione() {
		return txtCognomeRegistrazione;
	}

	public JTextField getTxtCodiceDiIdentificazioneRegistrazione() {
		return txtCodiceDiIdentificazioneRegistrazione;
	}

	public JTextField getTxtUtenteRegistrazione() {
		return txtUtenteRegistrazione;
	}

	public JPasswordField getTxtPasswordRegistrazione() {
		return txtPasswordRegistrazione;
	}

	public JLabel getLblProfessione() {
		return lblProfessione;
	}

	public JLabel getLblNomePersonale() {
		return lblNomePersonale;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}

	public JButton getBtnSomministraFarmaci() {
		return btnSomministraFarmaci;
	}

	public JButton getBtnVisualizzaPrescrizioneInfermiere() {
		return btnVisualizzaPrescrizioneInfermiere;
	}

	public JButton getBtnVisualizzaParametriVitaliInfermiere() {
		return btnVisualizzaParametriVitaliInfermiere;
	}

	public JButton getBtnVisualizzaParametriVitaliMedico() {
		return btnVisualizzaParametriVitaliMedico;
	}

	public JButton getBtnVisualizzaPrescrizionePrimario() {
		return btnVisualizzaPrescrizionePrimario;
	}

	public JButton getBtnVisualizzaParametriVitaliPrimario() {
		return btnVisualizzaParametriVitaliPrimario;
	}

	public JButton getBtnPrescrizione() {
		return btnPrescrizione;
	}

	public JButton getBtnRilascioPaziente() {
		return btnRilascioPaziente;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JTextField getTxtPressioneSistolica() {
		return txtPressioneSistolica;
	}

	public JTextField getTxtPressioneDiastolica() {
		return txtPressioneDiastolica;
	}

	public JTextField getTxtFrequenzaCardiaca() {
		return txtFrequenzaCardiaca;
	}

	public JTextField getTxtTemperaturaCorporea() {
		return txtTemperaturaCorporea;
	}

	public DefaultTableModel getTableModelAllarmi() {
		return tableModelAllarmi;
	}

	public JButton getBottoneAllarme() {
		return bottoneAllarme;
	}

	public JTable getTabellaAllarmi() {
		return tabellaAllarmi;
	}
	
	
		
	
	//}}
	
}
