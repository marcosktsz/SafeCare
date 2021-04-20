package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import models.*;
import view.MainFrame;
import view.NuovoPazienteFrame;

public class NuovoPazienteFrameController {
	MainFrame mainFrame;
	NuovoPazienteFrame theView;
	Model theModel;
	
	public NuovoPazienteFrameController(NuovoPazienteFrame theView, Model theModel, MainFrame mainFrame) {
		this.theView = theView;
		this.theModel = theModel;
		this.mainFrame = mainFrame;
		
		this.theView.addAddButtonListener(new AddButtonListener());
		this.theView.addChiudiButtonListener(new ChiudiButtonListener());
	}
	
	class AddButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean verifica = false;
			models.Date currentDate = new models.Date(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.YEAR));
			String none = "-";
			Paziente tmp;
			
			if(theView.getTxtCodiceUnicoSanitario().length() == 0 || theView.getTxtNome().length() == 0 || theView.getTxtCognome().length() == 0) {
				JOptionPane.showMessageDialog(null, "Campi obbligatori non completati!");
			} else {
				if(theView.getTxtCodiceUnicoSanitario().length() == 16) {
					tmp = new Paziente(theView.getTxtCodiceUnicoSanitario(), theView.getTxtCognome(), theView.getTxtNome(), none, none, none, none, 
							none, currentDate.toString(), none, "RICOVERATO");
					for (Paziente paziente : theModel.getListaPazienti()) {
						if(paziente.getCodiceUnivocoSanitario().equals(tmp.getCodiceUnivocoSanitario())) {
							JOptionPane.showMessageDialog(null, "Paziente gi� esistente!");
							verifica = true;
						} else {
							verifica = false;
						}
					}
					if(verifica == false) {
						theModel.getListaPazienti().add(tmp);
						theModel.writeToFile(theModel.getListaPazienti());
						mainFrame.getListModel().addElement(tmp);
						theModel.getListaObserver().add(new Observer(tmp.getNome() + " " + tmp.getCognome()));
						theModel.getListaParametriVitali().put(tmp, new ArrayList<ParametriVitali>());
						theModel.getListaParametriVitaliMedia().put(tmp, new ArrayList<ParametriVitaliMedia>());
						theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + tmp.getNome() + " " + tmp.getCognome() + "[Codice Fiscale: " + 
								tmp.getCodiceUnivocoSanitario() + "] � stato RICOVERATO!", theModel.getNewReportFile());
						theView.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Codice Sanitario errato!");
				}
			}
			
			
			File file = new File("assets//Parametri Vitali//" + theView.getTxtNome() + " " + theView.getTxtCognome() + ".json");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class ChiudiButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.dispose();
		}
		
	}
}
