package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import models.Farmaco;
import models.Model;
import models.Paziente;
import models.Prescrizione;
import models.Somministrazione;
import view.MainFrame;
import view.SomministrazioneFarmaciFrame;

public class SomministrazioneFarmaciFrameController {
	private Model theModel;
	private SomministrazioneFarmaciFrame theView;
	private MainFrame mainFrame;
	
	public SomministrazioneFarmaciFrameController(Model theModel, SomministrazioneFarmaciFrame theView,
			MainFrame mainFrame) {
		this.theModel = theModel;
		this.theView = theView;
		this.mainFrame = mainFrame;
		
		
		//{{ TABELLA PRESCRIZIONI
		Prescrizione lastPrescrizione = theModel.getListaPrescrizioni().get(theModel.getListaPrescrizioni().size() - 1);
		theView.getTxtDataPrescrizione().setText(lastPrescrizione.getDataPrescrizione());
			for (Farmaco farmaco : lastPrescrizione.getPrescrizioneMedica()) {	
				String[] tmpPrescrizione = {farmaco.getNomeFarmaco(), farmaco.getDurataTerapia().toString(), farmaco.getNrDosiGiornaliere().toString(), farmaco.getQuantitaDiFarmacoPerDose().toString()};
				String[] tmpSomministrazione = {farmaco.getNomeFarmaco(), "", theView.getDateFormat().format(theView.getTodayDate()), mainFrame.getLblNomePersonale().getText()};
				theView.getTableModelPrescrizione().addRow(tmpPrescrizione);
				theView.getTableModelSomministrazione().addRow(tmpSomministrazione);
				theView.getTabellaFarmaciPrescrizione().setEnabled(false);
			}
			String[] none = {"", "", "", ""};
			theView.getTableModelSomministrazione().addRow(none);
				
		theModel.readPazientiFromFile();
			for (Paziente paziente : theModel.getListaPazienti()) {
				if(paziente.getCodiceUnivocoSanitario().equals(mainFrame.getList().getSelectedValue().getCodiceUnivocoSanitario())) {
					theView.getLblTxtNomePrescrizione().setText(paziente.getNome());
					theView.getLblTxtCognomePrescrizione().setText(paziente.getCognome());
					theView.getLblTxtDottorePrescrizione().setText(paziente.getDottore());
				}
			}	
		//}}
			
		//{{ TABELLA SOMMINISTRAZIONI
		theModel.readSomministrazioniFromFile(mainFrame.getList().getSelectedValue().toString());
		for (Somministrazione somministrazione : theModel.getListaSomministrazioni()) {
			String[] tmpSomministrazione = {somministrazione.getNomeFarmaco(), somministrazione.getQuantitaDosi().toString(), somministrazione.getTodayDate(), somministrazione.getNomeInfermiere()};
			theView.getTableModelSomministrazione().addRow(tmpSomministrazione);
		}			
		//}}
		
		this.theView.addSalvaButtonListener(new SalvaButtonListener());
		this.theView.addEsciButtonListener(new EsciButtonListener());
	}
	
	class EsciButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.dispose();			
		}
		
	}
	
	class SalvaButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Somministrazione> listaSomministrazioniTmp = new ArrayList<>();
			theModel.readSomministrazioniFromFile(mainFrame.getList().getSelectedValue().toString());
			int rowBlock = 0;
			
			for(int i = 0; i < theView.getTableModelSomministrazione().getRowCount(); i++) {
				int j = 0;
				
				if(theView.getTableModelSomministrazione().getValueAt(i, j).toString().equals(new String(""))) {
					rowBlock = i + 1;
					break;
				} else {
					Somministrazione somministrazioneTmp = new Somministrazione(theView.getTableModelSomministrazione().getValueAt(i, j).toString(), 
							Integer.parseInt(theView.getTableModelSomministrazione().getValueAt(i, j+1).toString()), 
							theView.getTableModelSomministrazione().getValueAt(i, j+2).toString(), 
							theView.getTabellaFarmaciSomministrazione().getValueAt(i, j+3).toString());
					listaSomministrazioniTmp.add(somministrazioneTmp);
					theModel.setListaSomministrazioni(listaSomministrazioniTmp);
				}
			}
			theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + mainFrame.getList().getSelectedValue().getNome() + " " + 
					mainFrame.getList().getSelectedValue().getCognome() + "[Codice Fiscale: " + mainFrame.getList().getSelectedValue().getCodiceUnivocoSanitario() + "]" +
					"\n\t\tSomministrazione farmaci:\n\t\t\t" + listaSomministrazioniTmp.toString(), theModel.getNewReportFile());
			
			for(int i = rowBlock; i < theView.getTableModelSomministrazione().getRowCount(); i++) {
				int j = 0;
				
				if(theView.getTableModelSomministrazione().getValueAt(i, j).toString().equals(new String(""))) {					
				} else {
					Somministrazione somministrazioneTmp = new Somministrazione(theView.getTableModelSomministrazione().getValueAt(i, j).toString(), 
							Integer.parseInt(theView.getTableModelSomministrazione().getValueAt(i, j+1).toString()), 
							theView.getTableModelSomministrazione().getValueAt(i, j+2).toString(), 
							theView.getTabellaFarmaciSomministrazione().getValueAt(i, j+3).toString());
					listaSomministrazioniTmp.add(somministrazioneTmp);
					theModel.setListaSomministrazioni(listaSomministrazioniTmp);
				}
			}
			theModel.writeSomministrazioniToFile(mainFrame.getList().getSelectedValue().toString(), theModel.getListaSomministrazioni());
			theView.dispose();
		}
		
	}
}
