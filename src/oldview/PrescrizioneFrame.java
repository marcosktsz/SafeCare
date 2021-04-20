package oldview;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PrescrizioneFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout mainLayout = new BorderLayout(2, 2);
	private static String titolo = "Prescrizione Medica";
	Container frmContentPaneContainer = new Container();
	private Font fontBottoni = new Font("Arial", Font.BOLD, 20);
	//{{ PANNELLO INFO
	private JPanel pannelloInfo = new JPanel();
	private JLabel lblPrescrizioneMedica;
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblDottore;
	//}}
	
	//{{ PANNELLO BOTTONI
	private JPanel pannelloBottoni = new JPanel();
	private JTextField txtDataPrescrizione;
	private JLabel lblDataPrescrizione;
	private JLabel lblTxtNome;
	private JLabel lblTxtCognome;
	private JLabel lblTxtDottore;
	private JButton btnNuovaPrescrizione;
	private JButton btnSave;
	private JButton btnEsci;
	private JButton btnFarmaco;
	private JButton btnRimuovi;
	//}}
	
	//{{ PANNELLO TABELLA FARMACI
	private JPanel pannelloFarmaci = new JPanel();
	private DefaultTableModel tableModel;
	private JTable tabellaFarmaci;
	private JScrollPane scrollPane;
	//}}

	//{{ LISTENERS
	public void addEsciButtonListener(ActionListener src) {
		btnEsci.addActionListener(src);
	}
	
	public void addSalvaButtonListener(ActionListener src) {
		btnSave.addActionListener(src);
	}
	
	public void addRimuoviButtonListener(ActionListener src) {
		btnRimuovi.addActionListener(src);
	}
	
	public void addFarmacoListener(ActionListener src) {
		btnFarmaco.addActionListener(src);
	}
	
	public void addNuovaPrescrizioneButtonListener(ActionListener src) {
		btnNuovaPrescrizione.addActionListener(src);
	}
	//}}
	
	public PrescrizioneFrame() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//CONTAINER PRINCIPALE
		frmContentPaneContainer = this.getContentPane();
		frmContentPaneContainer.setLayout(mainLayout);
		frmContentPaneContainer.add(pannelloInfo, BorderLayout.NORTH);
		frmContentPaneContainer.add(pannelloFarmaci, BorderLayout.CENTER);
		frmContentPaneContainer.add(pannelloBottoni, BorderLayout.SOUTH);
		
		
		//{{ PANNELLO INFO
		pannelloInfo.setPreferredSize(new Dimension(500, 50));
		pannelloInfo.setLayout(new GridBagLayout());
		GridBagConstraints gbcInfo = new GridBagConstraints();
		//gbcInfo.fill = GridBagConstraints.HORIZONTAL;
		gbcInfo.weightx = 1;
		gbcInfo.weighty = 0;
		
		lblNome = new JLabel("Nome:");
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 0;
		pannelloInfo.add(lblNome, gbcInfo);
		lblTxtNome = new JLabel();
		gbcInfo.ipadx = 1;
		gbcInfo.ipady = 0;
		pannelloInfo.add(lblTxtNome, gbcInfo);
		
		lblCognome = new JLabel("Cognome:");
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 1;
		pannelloInfo.add(lblCognome, gbcInfo);
		lblTxtCognome = new JLabel();
		gbcInfo.ipadx = 1;
		gbcInfo.ipady = 1;
		pannelloInfo.add(lblTxtCognome, gbcInfo);
		
		lblDottore = new JLabel("Dottore:");
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 2;
		pannelloInfo.add(lblDottore, gbcInfo);
		lblTxtDottore = new JLabel();
		gbcInfo.ipadx = 2;
		gbcInfo.ipady = 2;
		pannelloInfo.add(lblTxtDottore, gbcInfo);
		//}}
		
		//{{ PANNELLO BOTTONI
		lblDataPrescrizione = new JLabel("Data prescrizione:");
		pannelloBottoni.add(lblDataPrescrizione);
		txtDataPrescrizione = new JTextField();
		txtDataPrescrizione.setPreferredSize(new Dimension(80, 25));
		pannelloBottoni.add(txtDataPrescrizione);
		txtDataPrescrizione.setEditable(false);
		
		
		btnFarmaco = new JButton("+ Farmaco");
		pannelloBottoni.add(btnFarmaco);
		
		btnRimuovi = new JButton("Rimuovi");
		pannelloBottoni.add(btnRimuovi);
		
		btnNuovaPrescrizione = new JButton("Nuova");
		pannelloBottoni.add(btnNuovaPrescrizione);
		
		btnSave = new JButton("Salva");
		pannelloBottoni.add(btnSave);
		
		btnEsci = new JButton("Esci");
		pannelloBottoni.add(btnEsci);
		//}}
		
		//{{ PANNELLO TABELLA FARMACI
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Nome Farmaco");
		tableModel.addColumn("Durata terapia (gg)");
		tableModel.addColumn("Dosi giornaliere");
		tableModel.addColumn("Quantita dosi (mg)");
		tabellaFarmaci = new JTable(tableModel);
		tabellaFarmaci.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(tabellaFarmaci);
		scrollPane.setPreferredSize(new Dimension(575, 400));
		pannelloFarmaci.add(scrollPane);
		//}}
		
		this.setSize(610, 540);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(610, 540));
		this.setVisible(false);
	}

	
	//{{ GETTERS AND SETTERS
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	public JTable getTabellaFarmaci() {
		return tabellaFarmaci;
	}

	public JTextField getTxtDataPrescrizione() {
		return txtDataPrescrizione;
	}

	public JLabel getLblTxtNome() {
		return lblTxtNome;
	}

	public JLabel getLblTxtCognome() {
		return lblTxtCognome;
	}

	public JLabel getLblTxtDottore() {
		return lblTxtDottore;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnFarmaco() {
		return btnFarmaco;
	}

	public JButton getBtnRimuovi() {
		return btnRimuovi;
	}

	public JButton getBtnNuovaPrescrizione() {
		return btnNuovaPrescrizione;
	}

	public JButton getBtnEsci() {
		return btnEsci;
	}

	public Font getFontBottoni() {
		return fontBottoni;
	}

	public void setFontBottoni(Font fontBottoni) {
		this.fontBottoni = fontBottoni;
	}

	public JLabel getLblPrescrizioneMedica() {
		return lblPrescrizioneMedica;
	}

	public void setLblPrescrizioneMedica(JLabel lblPrescrizioneMedica) {
		this.lblPrescrizioneMedica = lblPrescrizioneMedica;
	}
	
	//}}
}
