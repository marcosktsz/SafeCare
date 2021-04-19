package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import models.Farmaco;
import models.Model;
import models.Paziente;
import models.Prescrizione;
import view.MainFrame;
import view.PrescrizioneFrame;

public class PrescrizioneFrameController {
	private Model theModel;
	private PrescrizioneFrame theView;
	private MainFrame mainFrame;
	
	public PrescrizioneFrameController(Model theModel, PrescrizioneFrame theView, MainFrame mainFrame) {
		this.theModel = theModel;
		this.theView = theView;
		this.mainFrame = mainFrame;
		
		if(mainFrame.getLblProfessione().getText().equals(new String("Medico"))) {
			theView.getTabellaFarmaci().setEnabled(true);
			theModel.readPrescrizioniFromFile(mainFrame.getList().getSelectedValue().toString());
			if(!(theModel.getListaPrescrizioni().isEmpty())) {
				Prescrizione lastPrescrizione = theModel.getListaPrescrizioni().get(theModel.getListaPrescrizioni().size() - 1);
				theView.getTxtDataPrescrizione().setText(lastPrescrizione.getDataPrescrizione());
				for (Farmaco farmaco : lastPrescrizione.getPrescrizioneMedica()) {
					String[] tmp = {farmaco.getNomeFarmaco(), farmaco.getDurataTerapia().toString(), farmaco.getNrDosiGiornaliere().toString(), farmaco.getQuantitaDiFarmacoPerDose().toString()};
					theView.getTableModel().addRow(tmp);
				}
			} else {
				theView.getTxtDataPrescrizione().setText(mainFrame.getDateFormat().format(mainFrame.getTodayDate()));
			}
			
			theModel.readPazientiFromFile();
			for (Paziente paziente : theModel.getListaPazienti()) {
				if(paziente.getCodiceUnivocoSanitario().equals(mainFrame.getList().getSelectedValue().getCodiceUnivocoSanitario())) {
					theView.getLblTxtNome().setText(paziente.getNome());
					theView.getLblTxtCognome().setText(paziente.getCognome());
					theView.getLblTxtDottore().setText(paziente.getDottore());
				}
			}
			
		} else if(mainFrame.getLblProfessione().getText().equals(new String("Infermiere")) || mainFrame.getLblProfessione().getText().equals(new String("Primario"))) {
			theView.getBtnFarmaco().setEnabled(false);
			theView.getBtnRimuovi().setEnabled(false);
			theView.getBtnSave().setEnabled(false);
			theView.getBtnNuovaPrescrizione().setEnabled(false);
			theView.getBtnEsci().setEnabled(true);
			theView.getTabellaFarmaci().setEnabled(false);
			theModel.readPrescrizioniFromFile(mainFrame.getList().getSelectedValue().toString());
			if(!(theModel.getListaPrescrizioni().isEmpty())) {
				Prescrizione lastPrescrizione = theModel.getListaPrescrizioni().get(theModel.getListaPrescrizioni().size() - 1);
				theView.getTxtDataPrescrizione().setText(lastPrescrizione.getDataPrescrizione());
				for (Farmaco farmaco : lastPrescrizione.getPrescrizioneMedica()) {
					String[] tmp = {farmaco.getNomeFarmaco(), farmaco.getDurataTerapia().toString(), farmaco.getNrDosiGiornaliere().toString(), farmaco.getQuantitaDiFarmacoPerDose().toString()};
					theView.getTableModel().addRow(tmp);
				}
			} else {
				theView.getTxtDataPrescrizione().setText(mainFrame.getDateFormat().format(mainFrame.getTodayDate()));
			}
			
			theModel.readPazientiFromFile();
			for (Paziente paziente : theModel.getListaPazienti()) {
				if(paziente.getCodiceUnivocoSanitario().equals(mainFrame.getList().getSelectedValue().getCodiceUnivocoSanitario())) {
					theView.getLblTxtNome().setText(paziente.getNome());
					theView.getLblTxtCognome().setText(paziente.getCognome());
					theView.getLblTxtDottore().setText(paziente.getDottore());
				}
			}
		}
		
		this.theView.addEsciButtonListener(new EsciButtonListener());
		this.theView.addRimuoviButtonListener(new RimuoviButtonListener());
		this.theView.addSalvaButtonListener(new SalvaButtonListener());
		this.theView.addFarmacoListener(new FarmacoButtonListener());
		this.theView.addNuovaPrescrizioneButtonListener(new NuovaPrescrizioneButtonListener());
	}
	
	class EsciButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.dispose();			
		}
		
	}
	
	class RimuoviButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.getTableModel().removeRow(theView.getTabellaFarmaci().getSelectedRow());			
		}
		
	}
	
	class FarmacoButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] tmp = {"", "", "", ""};
			theView.getTableModel().addRow(tmp);
		}
		
	}

	class SalvaButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Farmaco> listaFarmaciTmp = new ArrayList<>();
			for(int i = 0; i < theView.getTableModel().getRowCount(); i++) {
				int j = 0;
				Farmaco farmacoTmp = new Farmaco(theView.getTableModel().getValueAt(i, j).toString(), 
						Integer.parseInt(theView.getTableModel().getValueAt(i, j + 1).toString()), 
						Integer.parseInt(theView.getTableModel().getValueAt(i, j + 2).toString()), 
						Integer.parseInt(theView.getTableModel().getValueAt(i, j + 3).toString()));
				listaFarmaciTmp.add(farmacoTmp);
			}
			
			if(!(theModel.getListaPrescrizioni().isEmpty())) {
				theModel.readPrescrizioniFromFile(mainFrame.getList().getSelectedValue().toString());
				boolean verifica = false;
				for (Prescrizione prescrizione : theModel.getListaPrescrizioni()) {
					if(prescrizione.getDataPrescrizione().equals(theView.getTxtDataPrescrizione().getText())) {
						prescrizione.setPrescrizioneMedica(listaFarmaciTmp);
						verifica = true;
						break;
					}
				}
				
				if(verifica == false) {
					theModel.getListaPrescrizioni().add(new Prescrizione(listaFarmaciTmp, theView.getTxtDataPrescrizione().getText()));
				}
				
				theModel.writePrescrizioniToFile(mainFrame.getList().getSelectedValue().toString(), theModel.getListaPrescrizioni());
				theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + mainFrame.getList().getSelectedValue().getNome() + " " + 
						mainFrame.getList().getSelectedValue().getCognome() + "[Codice Fiscale: " + mainFrame.getList().getSelectedValue().getCodiceUnivocoSanitario() + "]\t Medico: " +
						mainFrame.getList().getSelectedValue().getDottore() + "\n\t\tPrescrizione Medica [" + theView.getTxtDataPrescrizione().getText() + "]:" + 
						listaFarmaciTmp.toString(), theModel.getNewReportFile());
				theView.dispose();
			} else if(theModel.getListaPrescrizioni().isEmpty()){
				theModel.getListaPrescrizioni().add(new Prescrizione(listaFarmaciTmp, theView.getTxtDataPrescrizione().getText()));
				theModel.writePrescrizioniToFile(mainFrame.getList().getSelectedValue().toString(), theModel.getListaPrescrizioni());
				theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + mainFrame.getList().getSelectedValue().getNome() + " " + 
						mainFrame.getList().getSelectedValue().getCognome() + "[Codice Fiscale: " + mainFrame.getList().getSelectedValue().getCodiceUnivocoSanitario() + "]\t Medico: " +
						mainFrame.getList().getSelectedValue().getDottore() + "\n\t\tPrescrizione Medica [" + theView.getTxtDataPrescrizione().getText() + "]:\n\t\t\t" + 
						listaFarmaciTmp.toString(), theModel.getNewReportFile());
				theView.dispose();
			} 
		}
		
	}

	class NuovaPrescrizioneButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.getTableModel().setRowCount(0);
			theView.getTxtDataPrescrizione().setText(mainFrame.getDateFormat().format(mainFrame.getTodayDate()));
			
		}
	}
}
