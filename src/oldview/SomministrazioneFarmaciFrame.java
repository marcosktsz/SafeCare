package oldview;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class SomministrazioneFarmaciFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BorderLayout mainLayout = new BorderLayout(1,1);
	private static String titolo = "Somministrazine Farmaci";
	Container frmContentPaneContainer = new Container();
	private Font fontBottoni = new Font("Arial", Font.BOLD, 20);
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy     HH:mm");
	private Date todayDate = new Date();
	//{{ PANNELLO INFO PRESCRIZIONE
	private JPanel pannelloInfoPrescrizione = new JPanel();
	private JLabel lblNomePrescrizione;
	private JLabel lblCognomePrescrizione;
	private JLabel lblDottorePrescrizione;
	private JLabel lblTxtNomePrescrizione;
	private JLabel lblTxtCognomePrescrizione;
	private JLabel lblTxtDottorePrescrizione;
	private JTextField txtDataPrescrizione;
	private JLabel lblDataPrescrizione;
	//}}
	
	
	
	//{{ PANNELLO TABELLA PRESCRIZIONE
	private JPanel pannelloTabellaPrescrizione = new JPanel();
	private DefaultTableModel tableModelPrescrizione;
	private JTable tabellaFarmaciPrescrizione;
	private JScrollPane scrollPanePrescrizione;
	//}}
	
	//{{ PANNELLO TABELLA SOMMINISTRAZIONI
	private JPanel pannelloTabellaSomministrazione = new JPanel();
	private DefaultTableModel tableModelSomministrazione;
	private JTable tabellaFarmaciSomministrazione;
	private JScrollPane scrollPaneSomministrazione;
	//}}
	
	//{{ PANNELLO BOTTONI
	private JPanel pannelloBottoni = new JPanel();
	private JButton btnSalva;
	private JButton btnEsci;
	//}}

	//{{ LISTENERS
	public void addSalvaButtonListener(ActionListener src) {
		btnSalva.addActionListener(src);
	}
	
	public void addEsciButtonListener(ActionListener src) {
		btnEsci.addActionListener(src);
	}
	//}}
	
	public SomministrazioneFarmaciFrame() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//CONTAINER PRINCIPALE
		frmContentPaneContainer = this.getContentPane();
		frmContentPaneContainer.setLayout(mainLayout);
		frmContentPaneContainer.add(pannelloInfoPrescrizione, BorderLayout.NORTH);
		frmContentPaneContainer.add(pannelloTabellaPrescrizione, BorderLayout.WEST);
		frmContentPaneContainer.add(pannelloTabellaSomministrazione, BorderLayout.EAST);
		frmContentPaneContainer.add(pannelloBottoni, BorderLayout.SOUTH);
		
		
		//{{ PANNELLO INFO PRESCRIZIONE
		pannelloInfoPrescrizione.setPreferredSize(new Dimension(500, 50));
		pannelloInfoPrescrizione.setLayout(new GridBagLayout());
		GridBagConstraints gbcInfo = new GridBagConstraints();
		//gbcInfo.fill = GridBagConstraints.HORIZONTAL;
		gbcInfo.weightx = 1;
		gbcInfo.weighty = 0;
		
		
		lblDataPrescrizione = new JLabel("Data prescrizione:");
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 0;
		pannelloInfoPrescrizione.add(lblDataPrescrizione, gbcInfo);
		txtDataPrescrizione = new JTextField();
		txtDataPrescrizione.setPreferredSize(new Dimension(90, 15));
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 1;
		pannelloInfoPrescrizione.add(txtDataPrescrizione, gbcInfo);
		txtDataPrescrizione.setEditable(false);
		
		lblNomePrescrizione = new JLabel("Nome:");
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 1;
		pannelloInfoPrescrizione.add(lblNomePrescrizione, gbcInfo);
		lblTxtNomePrescrizione = new JLabel();
		pannelloInfoPrescrizione.add(lblTxtNomePrescrizione, gbcInfo);
		
		lblCognomePrescrizione = new JLabel("Cognome:");
		gbcInfo.ipadx = 1;
		gbcInfo.ipady = 1;
		pannelloInfoPrescrizione.add(lblCognomePrescrizione, gbcInfo);
		lblTxtCognomePrescrizione = new JLabel();
		pannelloInfoPrescrizione.add(lblTxtCognomePrescrizione, gbcInfo);
		
		lblDottorePrescrizione = new JLabel("Dottore:");
		gbcInfo.ipadx = 0;
		gbcInfo.ipady = 2;
		pannelloInfoPrescrizione.add(lblDottorePrescrizione, gbcInfo);
		lblTxtDottorePrescrizione = new JLabel();
		gbcInfo.ipadx = 2;
		gbcInfo.ipady = 2;
		pannelloInfoPrescrizione.add(lblTxtDottorePrescrizione, gbcInfo);
		//}}
				
		//}}
		
		//{{ PANNELLO TABELLA PRESCRIZIONE
		tableModelPrescrizione = new DefaultTableModel();
		tableModelPrescrizione.addColumn("Nome Farmaco");
		tableModelPrescrizione.addColumn("Durata terapia (gg)");
		tableModelPrescrizione.addColumn("Dosi giornaliere");
		tableModelPrescrizione.addColumn("Quantita dosi (mg)");
		tabellaFarmaciPrescrizione = new JTable(tableModelPrescrizione);
		tabellaFarmaciPrescrizione.setFillsViewportHeight(true);
		scrollPanePrescrizione = new JScrollPane(tabellaFarmaciPrescrizione);
		scrollPanePrescrizione.setPreferredSize(new Dimension(480, 350));
		pannelloTabellaPrescrizione.add(scrollPanePrescrizione);
		//}}
		
		//{{ PANNELLO TABELLA SOMMINISTRAZIONE
		tableModelSomministrazione = new DefaultTableModel();
		tableModelSomministrazione.addColumn("Nome Farmaco");
		tableModelSomministrazione.addColumn("Quantita dosi (mg)");
		tableModelSomministrazione.addColumn("Data somministrazione");
		tableModelSomministrazione.addColumn("Nome Infermiere");
		tabellaFarmaciSomministrazione = new JTable(tableModelSomministrazione);
		tabellaFarmaciSomministrazione.setFillsViewportHeight(true);
		scrollPaneSomministrazione = new JScrollPane(tabellaFarmaciSomministrazione);
		scrollPaneSomministrazione.setPreferredSize(new Dimension(580, 350));
		pannelloTabellaSomministrazione.add(scrollPaneSomministrazione);
		//}}
		
		//{{ PANNELLO BOTTONI
		pannelloBottoni.setPreferredSize(new Dimension(500, 50));
		pannelloBottoni.setLayout(new GridBagLayout());
		GridBagConstraints gbcBottoni = new GridBagConstraints();
		//gbcBottoni.fill = GridBagConstraints.HORIZONTAL;
		gbcBottoni.ipadx = 0;
		gbcBottoni.ipady = 0;
		
		btnSalva = new JButton("Salva");
		pannelloBottoni.add(btnSalva, gbcBottoni);

		btnEsci = new JButton("Esci");
		pannelloBottoni.add(btnEsci, gbcBottoni);
		//}}
		
		this.setSize(1100, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1100, 500));
		this.setVisible(false);
	}


	
	//{{ GETTERS AND SETTERS
	public JLabel getLblTxtNomePrescrizione() {
		return lblTxtNomePrescrizione;
	}
	
	public JLabel getLblTxtCognomePrescrizione() {
		return lblTxtCognomePrescrizione;
	}
	
	public JLabel getLblTxtDottorePrescrizione() {
		return lblTxtDottorePrescrizione;
	}

	public JTextField getTxtDataPrescrizione() {
		return txtDataPrescrizione;
	}

	public DefaultTableModel getTableModelPrescrizione() {
		return tableModelPrescrizione;
	}

	public DefaultTableModel getTableModelSomministrazione() {
		return tableModelSomministrazione;
	}

	public JTable getTabellaFarmaciPrescrizione() {
		return tabellaFarmaciPrescrizione;
	}

	public JTable getTabellaFarmaciSomministrazione() {
		return tabellaFarmaciSomministrazione;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public Font getFontBottoni() {
		return fontBottoni;
	}

	public void setFontBottoni(Font fontBottoni) {
		this.fontBottoni = fontBottoni;
	}
		
	//}}
}
