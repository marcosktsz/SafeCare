package controllers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import models.Infermiere;
import models.Medico;
import models.Model;
import models.Observer;
import models.ParametriVitali;
import models.ParametriVitaliMedia;
import models.Paziente;
import models.Primario;
import models.User;
import view.MainFrame;
import view.NuovoPazienteFrame;
import view.ParametriVitaliFrame;
import view.PrescrizioneFrame;
import view.SomministrazioneFarmaciFrame;

public class MainFrameController {
	MainFrame theView;
	Model theModel;
	NuovoPazienteFrame nuovoPazienteFrame;
	NuovoPazienteFrameController nuovoPazienteFrameController;
	PrescrizioneFrame prescrizioneFrame;
	PrescrizioneFrameController prescrizioneFrameController;
	SomministrazioneFarmaciFrame somministrazioneFarmaciFrame;
	SomministrazioneFarmaciFrameController somministrazioneFarmaciFrameController;
	ParametriVitaliFrame parametriVitaliFrame;
	ParametriVitaliFrameController parametriVitaliFrameController;
	File file;
	Timer timer, timer2;
	Boolean timing = false;
	DecimalFormat df = new DecimalFormat("#.#");
	Double sommaSbp = (double) 0, sommaDbp = (double) 0, sommaFc = (double) 0, sommaTc = (double) 0;
	Double mediaSbp, mediaDbp, mediaFc, mediaTc;
	Double sbp, dbp, fc, tc;
	Integer counter = 0;
	
	public MainFrameController(MainFrame theView, Model theModel) {
		this.theView = theView;
		this.theModel = theModel;
		parametriVitaliFrame = new ParametriVitaliFrame();
		parametriVitaliFrameController = new ParametriVitaliFrameController(theModel, parametriVitaliFrame, theView);
					
		this.theModel.readPazientiFromFile();
		this.theView.setList(theModel.getListaPazienti());		
		this.theView.addListMouseListener(new ListMouseListener());
		this.theView.addLoginButtonListener(new LoginButtonListener());
		this.theView.addNuovoPazienteListener(new NuovoPazienteListener());
		this.theView.addModificaDatiListener(new ModificaDatiListener());
		this.theView.addLogoutButtonListener(new LogoutButtonListener());
		this.theView.addPrescrizioneButtonListener(new PrescrizioneButtonListener());
		this.theView.addVisualizzaPrescrizioneButtonListener(new VisualizzaPrescrizioneButtonListener());
		this.theView.addSomministrazioneFarmaciButtonListener(new SomministrazioneFarmaciButtonListener());
		this.theView.addRegistratiButtonListener(new RegistratiButtonListener());
		this.theView.addApriSchermataLoginListener(new SchermataLoginListener());
		this.theView.addIndietroButtonListener(new IndietroButtonListener());
		this.theView.addRilasciaPazienteButtonListener(new RilasciaPazienteButtonListener());
		this.theView.addVisualizzaParametriVitaliListener(new VisualizzaParametriVitaliListener());
		this.theView.addButtonAllarmiListener(new ButtonAllarmiListener());
		this.theView.addButtonStampaReportListener(new ButtonStampaReportListener());
		
		for (Paziente paziente: theModel.getListaPazienti()) {
			if(paziente.getStatoPaziente().equals(new String("RICOVERATO"))) {
				theModel.getListaObserver().add(new Observer(paziente.getNome() + " " + paziente.getCognome()));
				theModel.getListaParametriVitali().put(paziente, new ArrayList<ParametriVitali>());
				theModel.getListaParametriVitaliMedia().put(paziente, new ArrayList<ParametriVitaliMedia>());
			}
		}
		
		
		//crea report settimanale se è più vecchio di 7 giorni
		theModel.setOldReportFile(theModel.getLatestFilefromDir("assets//Report settimanali//"));
		models.Date todayDate = new models.Date(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 
				(Calendar.getInstance().get(Calendar.MONTH) + 1), Calendar.getInstance().get(Calendar.YEAR));
		if(fileNameToDate(theModel.getOldReportFile().getName()).checkIfSevenDaysHavePassed(todayDate)) {
			theModel.setNewReportFile(new File("assets//Report settimanali//" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "-" + (Calendar.getInstance().get(Calendar.MONTH) + 1) + 
					"-" + Calendar.getInstance().get(Calendar.YEAR) + ".txt"));
			theModel.writeReportTitleToFile(todayDate, theModel.getNewReportFile());
			try {
				theModel.getNewReportFile().createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			theModel.setNewReportFile(theModel.getOldReportFile());
		}
	}
	
	class ListMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			for (Paziente paziente : theModel.getListaPazienti()) {
				if(theView.getList().getSelectedValue().equals(paziente)) {
					if(timing) {
						timing = false;
						timer2.cancel();
					}
					if(paziente.getStatoPaziente().equals(new String("RICOVERATO"))) {
						theView.setLblStatoPaziente(paziente.getStatoPaziente());
						theView.getLblStatoPaziente().setForeground(new Color(211, 11, 11));
						theView.getBtnModificaDatiInfermiere().setEnabled(true);
						theView.getBtnSomministraFarmaci().setEnabled(true);
						theView.getBtnVisualizzaPrescrizioneInfermiere().setEnabled(true);
						theView.getBtnVisualizzaParametriVitaliInfermiere().setEnabled(true);
						theView.getBtnModificaDatiMedico().setEnabled(true);
						theView.getBtnPrescrizione().setEnabled(true);
						theView.getBtnVisualizzaParametriVitaliMedico().setEnabled(true);
						theView.getBtnModificaDatiPrimario().setEnabled(true);
						theView.getBtnRilascioPaziente().setEnabled(true);
						theView.getBtnVisualizzaPrescrizionePrimario().setEnabled(true);
						theView.getBtnVisualizzaParametriVitaliPrimario().setEnabled(true);
						
						//metto a 0 i parametri se il paziente è RICOVERATO
						for (Paziente paziente2 : theModel.getListaParametriVitaliMedia().keySet()) {
							if(paziente.equals(paziente2)) {
								if(theModel.getListaParametriVitaliMedia().get(paziente2).isEmpty()) {
									theView.getTxtPressioneSistolica().setText(new Integer(0).toString());
									theView.getTxtPressioneDiastolica().setText(new Integer(0).toString());
									theView.getTxtFrequenzaCardiaca().setText(new Integer(0).toString());
									theView.getTxtTemperaturaCorporea().setText(new Integer(0).toString());
								} else {
									theView.getTxtPressioneSistolica().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaSBP()).toString());
									theView.getTxtPressioneDiastolica().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaDBP()).toString());
									theView.getTxtFrequenzaCardiaca().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaFC()).toString());
									theView.getTxtTemperaturaCorporea().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaTC()).toString());
								}
							}
						}						
						
						//timer che aggiorna le medie dei parametri per ogni paziente
						timer2 = new Timer();
						timer2.schedule(new TimerTask() {
								
							@Override
							public void run() {
								timing = true;
								for (Paziente paziente2 : theModel.getListaParametriVitaliMedia().keySet()) {
									if(paziente.equals(paziente2)) {
										if(theModel.getListaParametriVitaliMedia().get(paziente2).isEmpty()) {
											theView.getTxtPressioneSistolica().setText(new Integer(0).toString());
											theView.getTxtPressioneDiastolica().setText(new Integer(0).toString());
											theView.getTxtFrequenzaCardiaca().setText(new Integer(0).toString());
											theView.getTxtTemperaturaCorporea().setText(new Integer(0).toString());
										} else {
											theView.getTxtPressioneSistolica().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaSBP()).toString());
											theView.getTxtPressioneDiastolica().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaDBP()).toString());
											theView.getTxtFrequenzaCardiaca().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaFC()).toString());
											theView.getTxtTemperaturaCorporea().setText(df.format(theModel.getListaParametriVitaliMedia().get(paziente2).get(theModel.getListaParametriVitaliMedia().get(paziente2).size() - 1).getMediaTC()).toString());
										}
									}
								}
							}
						}, 1000, 5000);
						
					} else if(paziente.getStatoPaziente().equals(new String("RILASCIATO"))) {
						theView.setLblStatoPaziente(paziente.getStatoPaziente());
						theView.getLblStatoPaziente().setForeground(new Color(24, 158, 24));
						theView.getBtnModificaDatiInfermiere().setEnabled(false);
						theView.getBtnSomministraFarmaci().setEnabled(false);
						theView.getBtnVisualizzaPrescrizioneInfermiere().setEnabled(false);
						theView.getBtnVisualizzaParametriVitaliInfermiere().setEnabled(false);
						theView.getBtnModificaDatiMedico().setEnabled(false);
						theView.getBtnPrescrizione().setEnabled(false);
						theView.getBtnVisualizzaParametriVitaliMedico().setEnabled(false);
						theView.getBtnModificaDatiPrimario().setEnabled(false);
						theView.getBtnRilascioPaziente().setEnabled(false);
						theView.getBtnVisualizzaPrescrizionePrimario().setEnabled(false);
						theView.getBtnVisualizzaParametriVitaliPrimario().setEnabled(false);
						theView.getTxtPressioneSistolica().setText("-");
						theView.getTxtPressioneDiastolica().setText("-");
						theView.getTxtFrequenzaCardiaca().setText("-");
						theView.getTxtTemperaturaCorporea().setText("-");
					}
					theView.setTxtCodiceUnicoSanitario(paziente.getCodiceUnivocoSanitario());
					theView.setTxtNome(paziente.getNome());
					theView.setTxtCognome(paziente.getCognome());
					theView.setTxtLuogoDiNascita(paziente.getLuogoDiNascita());
					theView.setTxtDataDiNascita(paziente.getDataDiNascita());
					theView.setTxtDataRicovero(paziente.getDataRicovero());
					theView.setTxtDataRilascio(paziente.getDataRilascio());
					theView.setTxtSalone(paziente.getSalone());
					theView.setTxtDottore(paziente.getDottore());
					theView.setTxtDiagnosi(paziente.getDiagnosi());
					
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void cambiaPannelloPersonale(JPanel pannelloDaRimuovere, JPanel pannelloDaAggiungere) {
		theView.getFrmContentPaneContainer().remove(pannelloDaRimuovere);
		theView.getFrmContentPaneContainer().add(pannelloDaAggiungere, BorderLayout.EAST);
		theView.repaint();
		theView.printAll(theView.getGraphics());
	}
	
	public void cambiaPannelloCentrale(JPanel pannelloDaRimuovere, JPanel pannelloDaAggiungere) {
		theView.getFrmContentPaneContainer().remove(pannelloDaRimuovere);
		theView.getFrmContentPaneContainer().add(pannelloDaAggiungere, BorderLayout.CENTER);
		theView.repaint();
		theView.printAll(theView.getGraphics());
	}
	
	class IndietroButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getBtnIndietro().getText().equals(new String("Indietro"))) {
				theView.getLblProfessione().setText("");
				cambiaPannelloCentrale(theView.getPannelloLogin(), theView.getPannelloDati());
				cambiaPannelloPersonale(theView.getPannelloBottoniLogin(), theView.getPannelloBottoni());
			} else if(theView.getBtnIndietro().getText().equals(new String("Annulla"))) {
				cambiaPannelloCentrale(theView.getPannelloRegistrazione(), theView.getPannelloLogin());
				theView.getBtnLogin().setEnabled(true);
				theView.getBtnIndietro().setText("Indietro");
				theView.getBtnRegistrati().setText("Registrati");
			}
		}
		
	}
	
	class SchermataLoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.setNomeBottonePremuto(e.getActionCommand());
			theView.getLblProfessione().setText(theView.getNomeBottonePremuto());
			cambiaPannelloCentrale(theView.getPannelloDati(), theView.getPannelloLogin());
			cambiaPannelloPersonale(theView.getPannelloBottoni(), theView.getPannelloBottoniLogin());
			
		}
		
	}
	
	class LoginButtonListener implements ActionListener{
		boolean verifica = false;
		@SuppressWarnings({ "deprecation" })
		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getNomeBottonePremuto().equals(new String("Infermiere"))) {
                theModel.readInfermieriFromFile();
                for (Infermiere infermiere : theModel.getListaInfermieri()) {
                	if(theView.getTxtUtente().getText().equals(infermiere.getAutentificazione().getId()) && 
                			theView.getTxtPasswordField().getText().equals(infermiere.getAutentificazione().getPass())) {
                			verifica = true;
                			theView.getLblNomePersonale().setText(infermiere.getNome() + " " + infermiere.getCognome());
                	}
                }
                if(verifica == true) {
                	cambiaPannelloPersonale(theView.getPannelloBottoniLogin(), theView.getPannelloInfermiere());
                	cambiaPannelloCentrale(theView.getPannelloLogin(), theView.getPannelloDati());
                	verifica = false;
                } else {
                	JOptionPane.showMessageDialog(null, "Utente e password errati! Riprova!");
                	verifica = false;
                }
            }
            
			if(theView.getNomeBottonePremuto().equals(new String("Medico"))) {
                theModel.readMediciFromFile();
                for (Medico medico : theModel.getListaMedici()) {
                	if(theView.getTxtUtente().getText().equals(medico.getAutentificazione().getId()) && 
                			theView.getTxtPasswordField().getText().equals(medico.getAutentificazione().getPass())) {
                			verifica = true;
                			theView.getLblNomePersonale().setText(medico.getNome() + " " + medico.getCognome());
                	}
                }
                if(verifica == true) {
                	cambiaPannelloPersonale(theView.getPannelloBottoniLogin(), theView.getPannelloMedico());
                	cambiaPannelloCentrale(theView.getPannelloLogin(), theView.getPannelloDati());
                	verifica = false;
                } else {
                	JOptionPane.showMessageDialog(null, "Utente e password errati! Riprova!");
                	verifica = false;
                }
            }
            
			if(theView.getNomeBottonePremuto().equals(new String("Primario"))) {
                theModel.readPrimariFromFile();
                for (Primario primario : theModel.getListaPrimari()) {
                	if(theView.getTxtUtente().getText().equals(primario.getAutentificazione().getId()) && 
                			theView.getTxtPasswordField().getText().equals(primario.getAutentificazione().getPass())) {
                			verifica = true;
                			theView.getLblNomePersonale().setText(primario.getNome() + " " + primario.getCognome());
                	}
                }
                if(verifica == true) {
                	cambiaPannelloPersonale(theView.getPannelloBottoniLogin(), theView.getPannelloPrimario());
                	cambiaPannelloCentrale(theView.getPannelloLogin(), theView.getPannelloDati());
                	verifica = false;
                } else {
                	JOptionPane.showMessageDialog(null, "Utente e password errati! Riprova!");
                	verifica = false;
                }
            }
			
		}
		
	}
	
	class LogoutButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getNomeBottonePremuto().equals(new String("Infermiere"))) {
				cambiaPannelloPersonale(theView.getPannelloInfermiere(), theView.getPannelloBottoni());
            }
            
			if(theView.getNomeBottonePremuto().equals(new String("Medico"))) {
				cambiaPannelloPersonale(theView.getPannelloMedico(), theView.getPannelloBottoni());
            }
            
			if(theView.getNomeBottonePremuto().equals(new String("Primario"))) {
				cambiaPannelloPersonale(theView.getPannelloPrimario(), theView.getPannelloBottoni());
            }
		theView.getLblNomePersonale().setText("");
		theView.getLblProfessione().setText("");
		theView.getTxtUtente().setText("");
		theView.getTxtPasswordField().setText("");
		}
		
	}

	class RegistratiButtonListener implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getBtnRegistrati().getText().equals(new String("Registrati"))) {
				cambiaPannelloCentrale(theView.getPannelloLogin(), theView.getPannelloRegistrazione());
				theView.getBtnLogin().setEnabled(false);
				theView.getBtnRegistrati().setText("Conferma");
				theView.getBtnIndietro().setText("Annulla");
			} else if(theView.getBtnRegistrati().getText().equals(new String("Conferma"))) {
				if(theView.getTxtCodiceDiIdentificazioneRegistrazione().getText().isEmpty() ||
						theView.getTxtNomeRegistrazione().getText().isEmpty() ||
						theView.getTxtCognomeRegistrazione().getText().isEmpty() ||
						theView.getTxtUtenteRegistrazione().getText().isEmpty() ||
						theView.getTxtPasswordRegistrazione().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Non tutti i campi sono stati completati!");
				} else if(theView.getTxtCodiceDiIdentificazioneRegistrazione().getText().length() != 16){
					JOptionPane.showMessageDialog(null, "Codice di identificazione errato!");
				} else {
					cambiaPannelloCentrale(theView.getPannelloRegistrazione(), theView.getPannelloLogin());
					theView.getBtnLogin().setEnabled(true);
					theView.getBtnRegistrati().setText("Registrati");
					if(theView.getNomeBottonePremuto().equals(new String("Infermiere"))) {
						theModel.readInfermieriFromFile();
						theModel.getListaInfermieri().add(new Infermiere(theView.getTxtNomeRegistrazione().getText(),
								theView.getTxtCognomeRegistrazione().getText(), 
								theView.getTxtCodiceDiIdentificazioneRegistrazione().getText(), 
								new User(theView.getTxtUtenteRegistrazione().getText(), theView.getTxtPasswordRegistrazione().getText())));
						theModel.writeToFile(theModel.getListaInfermieri());
					} else if(theView.getNomeBottonePremuto().equals(new String("Medico"))) {
						theModel.readMediciFromFile();
						theModel.getListaMedici().add(new Medico(theView.getTxtNomeRegistrazione().getText(),
								theView.getTxtCognomeRegistrazione().getText(), 
								theView.getTxtCodiceDiIdentificazioneRegistrazione().getText(), 
								new User(theView.getTxtUtenteRegistrazione().getText(), theView.getTxtPasswordRegistrazione().getText())));
						theModel.writeToFile(theModel.getListaMedici());
					} else if(theView.getNomeBottonePremuto().equals(new String("Primario"))) {
						theModel.readPrimariFromFile();
						theModel.getListaPrimari().add(new Primario(theView.getTxtNomeRegistrazione().getText(),
								theView.getTxtCognomeRegistrazione().getText(), 
								theView.getTxtCodiceDiIdentificazioneRegistrazione().getText(), 
								new User(theView.getTxtUtenteRegistrazione().getText(), theView.getTxtPasswordRegistrazione().getText())));
						theModel.writeToFile(theModel.getListaPrimari());
					}
				}
			}
		}
		
	}

	class NuovoPazienteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			nuovoPazienteFrame = new NuovoPazienteFrame();
			nuovoPazienteFrameController = new NuovoPazienteFrameController(nuovoPazienteFrame, theModel, theView);
			nuovoPazienteFrame.setVisible(true);			
		}
		
	}
	
	class ModificaDatiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getList().getSelectedValue() != null) {
				if(theView.getBtnModificaDatiInfermiere().getText().equals(new String("Modifica Dati")) &&
						theView.getBtnModificaDatiMedico().getText().equals(new String("Modifica Dati")) &&
						theView.getBtnModificaDatiPrimario().getText().equals(new String("Modifica Dati"))) {
					theView.getBtnModificaDatiInfermiere().setText("Salva");
					theView.getBtnModificaDatiMedico().setText("Salva");
					theView.getBtnModificaDatiPrimario().setText("Salva");
					if(theView.getNomeBottonePremuto().equals(new String("Infermiere"))) {
						theView.getTxtCodiceUnicoSanitario().setEditable(false);
						theView.getTxtNome().setEditable(true);
						theView.getTxtCognome().setEditable(true);
						theView.getTxtLuogoDiNascita().setEditable(true);
						theView.getTxtDataDiNascita().setEditable(true);
						theView.getTxtDataRicovero().setEditable(false);
						theView.getTxtDataRilascio().setEditable(true);
						theView.getTxtSalone().setEditable(true);
						theView.getTxtDiagnosi().setEditable(false);
						theView.getTxtDottore().setEditable(true);
						theView.getTxtDataRilascio().setEditable(false);
					}
				
					if(theView.getNomeBottonePremuto().equals(new String("Medico"))) {
						theView.getTxtCodiceUnicoSanitario().setEditable(false);
						theView.getTxtNome().setEditable(true);
						theView.getTxtCognome().setEditable(true);
						theView.getTxtLuogoDiNascita().setEditable(true);
						theView.getTxtDataDiNascita().setEditable(true);
						theView.getTxtDataRicovero().setEditable(false);
						theView.getTxtDataRilascio().setEditable(true);
						theView.getTxtSalone().setEditable(true);
						theView.getTxtDiagnosi().setEditable(true);
						theView.getTxtDottore().setEditable(false);
						theView.getTxtDataRilascio().setEditable(false);
					}
				
					if(theView.getNomeBottonePremuto().equals(new String("Primario"))) {
						theView.getTxtCodiceUnicoSanitario().setEditable(false);
						theView.getTxtNome().setEditable(true);
						theView.getTxtCognome().setEditable(true);
						theView.getTxtLuogoDiNascita().setEditable(true);
						theView.getTxtDataDiNascita().setEditable(true);
						theView.getTxtDataRicovero().setEditable(false);
						theView.getTxtDataRilascio().setEditable(true);
						theView.getTxtDiagnosi().setEditable(true);
						theView.getTxtDottore().setEditable(true);
						theView.getTxtDataRilascio().setEditable(false);
						theView.getTxtSalone().setEditable(true);
					}
				} else if(theView.getBtnModificaDatiInfermiere().getText().equals(new String("Salva")) &&
						theView.getBtnModificaDatiMedico().getText().equals(new String("Salva")) &&
						theView.getBtnModificaDatiPrimario().getText().equals(new String("Salva"))) {
					if(checkDateValidation(theView.getTxtDataDiNascita().getText())) {
						for (Paziente paziente : theModel.getListaPazienti()) {
							if(theView.getList().getSelectedValue().getNome().equals(paziente.getNome()) &&
									theView.getList().getSelectedValue().getCognome().equals(paziente.getCognome())) {
								paziente.setCodiceUnivocoSanitario(theView.getTxtCodiceUnicoSanitario().getText());
								paziente.setNome(theView.getTxtNome().getText());
								paziente.setCognome(theView.getTxtCognome().getText());
								paziente.setDataDiNascita(theView.getTxtDataDiNascita().getText());
								paziente.setLuogoDiNascita(theView.getTxtLuogoDiNascita().getText());
								paziente.setDiagnosi(theView.getTxtDiagnosi().getText());
								paziente.setDataRicovero(theView.getTxtDataRicovero().getText());
								paziente.setDataRilascio(theView.getTxtDataRilascio().getText());
								paziente.setSalone(theView.getTxtSalone().getText());
								paziente.setDottore(theView.getTxtDottore().getText());
								paziente.setStatoPaziente(theView.getLblStatoPaziente().getText());
								theModel.writeToFile(theModel.getListaPazienti());
							}
						}
						theView.getTxtCodiceUnicoSanitario().setEditable(false);
						theView.getTxtNome().setEditable(false);
						theView.getTxtCognome().setEditable(false);
						theView.getTxtLuogoDiNascita().setEditable(false);
						theView.getTxtDataDiNascita().setEditable(false);
						theView.getTxtDataRicovero().setEditable(false);
						theView.getTxtDataRilascio().setEditable(false);
						theView.getTxtSalone().setEditable(false);
						theView.getTxtDiagnosi().setEditable(false);
						theView.getTxtDottore().setEditable(false);
						theView.getTxtDataRilascio().setEditable(false);
						theView.getBtnModificaDatiInfermiere().setText("Modifica Dati");
						theView.getBtnModificaDatiMedico().setText("Modifica Dati");
						theView.getBtnModificaDatiPrimario().setText("Modifica Dati");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid date! Correct format: dd/MM/yyyy");
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Paziente non selezionato!");
			}
		}
	}
	
	class PrescrizioneButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getList().getSelectedValue() != null) {
				file = new File("assets//Prescrizioni Mediche//" + theView.getList().getSelectedValue() + ".json");
				if(file.isFile() == true) {
					prescrizioneFrame = new PrescrizioneFrame();
					prescrizioneFrameController = new PrescrizioneFrameController(theModel, prescrizioneFrame, theView);
					prescrizioneFrame.setVisible(true);
				} else {
					try {
						file.createNewFile();
						prescrizioneFrame = new PrescrizioneFrame();
						prescrizioneFrameController = new PrescrizioneFrameController(theModel, prescrizioneFrame, theView);
						prescrizioneFrame.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Paziente non selezionato!");
			}
		}
		
	}

	class VisualizzaPrescrizioneButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getList().getSelectedValue() != null) {
				prescrizioneFrame = new PrescrizioneFrame();
				prescrizioneFrameController = new PrescrizioneFrameController(theModel, prescrizioneFrame, theView);
				prescrizioneFrame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Paziente non selezionato!");
			}
		}
		
	}
	
	class SomministrazioneFarmaciButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getList().getSelectedValue() != null) {
				theModel.readPrescrizioniFromFile(theView.getList().getSelectedValue().toString());
				if(!(theModel.getListaPrescrizioni().isEmpty())) {
					file = new File("assets//Somministrazioni Farmaci//" + theView.getList().getSelectedValue() + ".json");
					if(file.isFile() == true) {
						somministrazioneFarmaciFrame = new SomministrazioneFarmaciFrame();
						somministrazioneFarmaciFrameController = new SomministrazioneFarmaciFrameController(theModel, somministrazioneFarmaciFrame, theView);
						somministrazioneFarmaciFrame.setVisible(true);
					} else {
						try {
							file.createNewFile();
							somministrazioneFarmaciFrame = new SomministrazioneFarmaciFrame();
							somministrazioneFarmaciFrameController = new SomministrazioneFarmaciFrameController(theModel, somministrazioneFarmaciFrame, theView);
							somministrazioneFarmaciFrame.setVisible(true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Il paziente selezionato non ha una PRESCRIZIONE MEDICA");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Paziente non selezionato!");
			}
		}
		
	}
	
	class RilasciaPazienteButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			models.Date currentDate = new models.Date(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.YEAR));
			
			if(theView.getList().getSelectedValue() != null) {			
				for (Paziente paziente : theModel.getListaPazienti()) {
					if(theView.getList().getSelectedValue().getCodiceUnivocoSanitario().equals(paziente.getCodiceUnivocoSanitario())) {
						if(theView.getTxtDataRilascio().getText().equals(new String("-"))) {
							paziente.setStatoPaziente(new String("RILASCIATO"));
							theView.getLblStatoPaziente().setText(new String("RILASCIATO"));
							theView.getLblStatoPaziente().setForeground(new Color(24, 158, 24));
							theView.getTxtDataRilascio().setText(currentDate.toString());
							paziente.setDataRilascio(theView.getTxtDataRilascio().getText());
							theModel.writeToFile(theModel.getListaPazienti());
							theModel.removeObserver(paziente);
							theModel.removeParametriVitali(paziente);
							theView.getTxtPressioneSistolica().setText("-");
							theView.getTxtPressioneDiastolica().setText("-");
							theView.getTxtFrequenzaCardiaca().setText("-");
							theView.getTxtTemperaturaCorporea().setText("-");
							theView.getBtnRilascioPaziente().setEnabled(false);
							theView.getBtnModificaDatiPrimario().setEnabled(false);
							theView.getBtnVisualizzaPrescrizionePrimario().setEnabled(false);
							theView.getBtnVisualizzaParametriVitaliPrimario().setEnabled(false);
							theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(theView.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + paziente.getCognome() + "[Codice Fiscale: " + 
								paziente.getCodiceUnivocoSanitario() + "] è stato RILASCIATO!", theModel.getNewReportFile());
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Paziente non selezionato!");
			}
		}
	}
	
	class VisualizzaParametriVitaliListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getList().getSelectedValue() != null) {
				parametriVitaliFrame.setVisible(true);
				parametriVitaliFrame.getTxtNome().setText(theView.getList().getSelectedValue().getNome());
				parametriVitaliFrame.getTxtCognome().setText(theView.getList().getSelectedValue().getCognome());
				
				timer = new Timer();
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						for (Observer observer: theModel.getListaObserver()) {
							if(observer.getNomeObserver().equals(theView.getList().getSelectedValue().getNome() + " " + theView.getList().getSelectedValue().getCognome())) {
								//pressione sistolica
								if(observer.getPressioneSistolica() <= 90 || observer.getPressioneSistolica() >= 120) {
									parametriVitaliFrame.getTxtPressioneSistolica().setForeground(new Color(211, 11, 11));
									parametriVitaliFrame.getTxtPressioneSistolica().setText(df.format(observer.getPressioneSistolica()).toString());
								} else {
									parametriVitaliFrame.getTxtPressioneSistolica().setForeground(new Color(24, 158, 24));
									parametriVitaliFrame.getTxtPressioneSistolica().setText(df.format(observer.getPressioneSistolica()).toString());
								}
								
								//pressione diastolica
								if(observer.getPressioneDiastolica() <= 60 || observer.getPressioneDiastolica() >= 80) {
									parametriVitaliFrame.getTxtPressioneDiastolica().setForeground(new Color(211, 11, 11));
									parametriVitaliFrame.getTxtPressioneDiastolica().setText(df.format(observer.getPressioneDiastolica()).toString());
								} else {
									parametriVitaliFrame.getTxtPressioneDiastolica().setForeground(new Color(24, 158, 24));
									parametriVitaliFrame.getTxtPressioneDiastolica().setText(df.format(observer.getPressioneDiastolica()).toString());
								}
								
								//frequenza cardiaca
								if(observer.getFrequenzaCardiaca() <= 60 || observer.getFrequenzaCardiaca() >= 100) {
									parametriVitaliFrame.getTxtFrequenzaCardiaca().setForeground(new Color(211, 11, 11));
									parametriVitaliFrame.getTxtFrequenzaCardiaca().setText(df.format(observer.getFrequenzaCardiaca()).toString());
								} else {
									parametriVitaliFrame.getTxtFrequenzaCardiaca().setForeground(new Color(24, 158, 24));
									parametriVitaliFrame.getTxtFrequenzaCardiaca().setText(df.format(observer.getFrequenzaCardiaca()).toString());
								}
								
								//temperatura corporea
								if(observer.getTemperaturaCorporea() <= 36.5 || observer.getTemperaturaCorporea() >= 37.5) {
									parametriVitaliFrame.getTxtTemperaturaCorporea().setForeground(new Color(211, 11, 11));
									parametriVitaliFrame.getTxtTemperaturaCorporea().setText(df.format(observer.getTemperaturaCorporea()).toString());
								} else {
									parametriVitaliFrame.getTxtTemperaturaCorporea().setForeground(new Color(24, 158, 24));
									parametriVitaliFrame.getTxtTemperaturaCorporea().setText(df.format(observer.getTemperaturaCorporea()).toString());
								}
							}
						}
					}
				}, 6000, 6000);
			} else {
				JOptionPane.showMessageDialog(null, "Paziente non selezionato!");
			}
		}
		
	}

	class ButtonAllarmiListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(theView.getLblProfessione().getText().equals(new String("Medico"))) {
				if(theView.getTabellaAllarmi().getValueAt(theView.getTabellaAllarmi().getSelectedRow(), 5) == null) {
					theView.getTabellaAllarmi().setValueAt("STOPPED", theView.getTabellaAllarmi().getSelectedRow(), 5);
				} else {
				JOptionPane.showMessageDialog(null, "L'allarme è già stato fermato");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Permesso negato! Solo un medico ha il permesso di fermare gli allarmi!");
			}
			
		}
		
	}
	
	class ButtonStampaReportListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			File file = theModel.getNewReportFile();
			Desktop desktop = Desktop.getDesktop();
			
				try {
					if(file.exists()) {
						desktop.open(file);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
	}
	
	//{{ DATE CONTROLLS
	public static boolean checkDatePattern(String data) {
		boolean validation;
		
		try {
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        format.parse(data);
	        validation = true;
	    } catch (ParseException e) {
	        validation = false;
	    }
		
		return validation;
	}
	
	public static models.Date stringToDate(String data) {
		int i, counter = 0;
		String day = "";
		String month = "";
		String year = "";
		Integer giorno, mese, anno;

		for(i = 0; i < data.length(); i++) {
    		if(data.charAt(i) == '/') {
    			counter++;
    		} else {
    			if(counter == 0) {
    				Character halfDay = data.charAt(i);
    				day = day + halfDay.toString();
    			}	    			
    			if(counter == 1) {
    				Character halfMonth = data.charAt(i);
    				month =  month + halfMonth.toString();
    			}
    			if(counter == 2) {
    				Character halfYear = data.charAt(i);
    				year = year + halfYear.toString();	    				
    			}
    		}
    		
    	}
		
		if(day.startsWith("0")) {
			day.substring(1);
			giorno = Integer.parseInt(day);
		} else {
			giorno = Integer.parseInt(day);
		}
		
		if(month.startsWith("0")) {
			month.substring(1);
			mese = Integer.parseInt(month);
		} else {
			mese = Integer.parseInt(month);
		}
    	anno = Integer.parseInt(year);
    	models.Date date = new models.Date(giorno, mese, anno);
    	
    	return date;
	}
	
	public static models.Date fileNameToDate(String data) {
		int i = 0, counter = 0;
		String day = "";
		String month = "";
		String year = "";
		Integer giorno, mese, anno;

		while(data.charAt(i) != '.' ) {
    		if(data.charAt(i) == '-') {
    			counter++;
    		} else {
    			if(counter == 0) {
    				Character halfDay = data.charAt(i);
    				day = day + halfDay.toString();
    			}	    			
    			if(counter == 1) {
    				Character halfMonth = data.charAt(i);
    				month =  month + halfMonth.toString();
    			}
    			if(counter == 2) {
    				Character halfYear = data.charAt(i);
    				year = year + halfYear.toString();	    				
    			}
    		}
    		i++;
    	}
		
		if(day.startsWith("0")) {
			day.substring(1);
			giorno = Integer.parseInt(day);
		} else {
			giorno = Integer.parseInt(day);
		}
		
		if(month.startsWith("0")) {
			month.substring(1);
			mese = Integer.parseInt(month);
		} else {
			mese = Integer.parseInt(month);
		}
    	anno = Integer.parseInt(year);
    	models.Date date = new models.Date(giorno, mese, anno);
    	
    	return date;
	}
	
	public static boolean checkDateValidation(String data) {
		boolean validation = false;
		models.Date currentDate = new models.Date(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.YEAR));
		
		if(checkDatePattern(data)){
			System.out.println("Pattern della data è corretto!");
			models.Date date = stringToDate(data);
			if(date.checkIfLegal() && date.compareTo(currentDate) != 1) {
				validation = true;
				System.out.println("Data legale!");
			} else {
				validation = false;
				System.out.println("Data illegale");
			}
		} else {
			validation = false;
			System.out.println("Pattern della data non è corretto");
		}
		
		return validation;
	}
	//}}
	
}
