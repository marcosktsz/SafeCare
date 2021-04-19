package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import models.Model;
import models.Observer;
import models.ParametriVitali;
import models.ParametriVitaliMedia;
import models.Paziente;
import view.MainFrame;
import view.ParametriVitaliFrame;

public class ParametriVitaliFrameController {
	Model theModel;
	ParametriVitaliFrame theView;
	MainFrame mainFrame;
	//variabili necessarie per i parametri vitali
	private Timer timer; 
	private Random rand;
	private Date date;
	private DateFormat dateFormat;
	Double sbp, dbp, fc, tc;
	Double sommaSbp = (double) 0, sommaDbp = (double) 0, sommaFc = (double) 0, sommaTc = (double) 0;
	Double mediaSbp, mediaDbp, mediaFc, mediaTc;
	Integer counter = 0;
	
	public ParametriVitaliFrameController(Model theModel, ParametriVitaliFrame theView, MainFrame mainFrame) {
		this.theModel = theModel;
		this.theView = theView;
		this.mainFrame = mainFrame;
								
		//parametri vitali di ogni paziente
				rand = new Random();
				timer = new Timer();
				
				
				//forumla di gauss: (double) (rand.nextGaussian() * standardDeviation) + mean
				//{{ TIMERS
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						
						for (Observer observer: theModel.getListaObserver()) {
							sbp = (double) (rand.nextGaussian() * 4) + 105;				
							dbp = (double) (rand.nextGaussian() * 3) + 70;
							observer.setPressioneSistolica(sbp);
							observer.setPressioneDiastolica(dbp);
							observer.setSommaSBP(observer.getSommaSBP() + sbp);
							observer.setSommaDBP(observer.getSommaDBP() + dbp);
							observer.setCounterSBPeDBP(observer.getCounterSBPeDBP() + 1);
							
							//ipotensione
							if(observer.getPressioneSistolica() <= 90 && observer.getPressioneDiastolica() <= 60) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {										
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "IPOTENSIONE", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "IPOTENSIONE - Livello 2", theModel.getNewReportFile());
									}
								}
							}
							
							//ipertensione
							if(observer.getPressioneSistolica() >= 130 && observer.getPressioneDiastolica() >= 90) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "IPERTENSIONE", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "IPERTENSIONE - Livello 2", theModel.getNewReportFile());
									}
								}
							}
						}
					}
				}, 3000, 15000);
				
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() { 
						
						for (Observer observer: theModel.getListaObserver()) {
							fc = (double) (rand.nextGaussian() * 3) + 85;	
							observer.setFrequenzaCardiaca(fc);
							observer.setSommaFC(observer.getSommaFC() + fc);
							observer.setCounterFC(observer.getCounterFC() + 1);
							
							//tachicardia
							if(observer.getFrequenzaCardiaca() >= 100) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "TACHICARDIA", new Integer(1).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "TACHICARDIA - Livello 1", theModel.getNewReportFile());
									}
								}
							}
							
							//brachicardia
							if(observer.getFrequenzaCardiaca() <= 60) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "BRACHICARDIA", new Integer(1).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "BRACHICARDIA - Livello 1", theModel.getNewReportFile());
									}
								}
							}
							
							//fibrilazione ventricolare
							if(observer.getFrequenzaCardiaca() >= 140) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "FLUTTER", new Integer(3).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "FIBRILAZIONE VENTRICOLARE - Livello 3", theModel.getNewReportFile());
									}
								}
							}
						}
						
					}
				}, 3000, 25000);
				
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						
						for (Observer observer: theModel.getListaObserver()) {
							tc = (double) (rand.nextGaussian() * 0.2) + 37;
							observer.setTemperaturaCorporea(tc);
							observer.setSommaTC(observer.getSommaTC() + tc);
							observer.setCounterTC(observer.getCounterTC() + 1);
							
							//ipotermia
							if(observer.getTemperaturaCorporea() <= 36.5) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "IPOTERMIA", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "IPOTERMIA - Livello 2", theModel.getNewReportFile());
									}
								}
							}
							
							//ipetermia
							if(observer.getTemperaturaCorporea() >= 37.5) {
								for (Paziente paziente : theModel.getListaPazienti()) {
									if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
										String[] tmp = {paziente.getNome(), paziente.getCognome(), paziente.getSalone(), "IPETERMIA", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPaziente: " + paziente.getNome() + " " + 
												paziente.getCognome() + "[Codice Fiscale: " + paziente.getCodiceUnivocoSanitario() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "IPETERMIA - Livello 2", theModel.getNewReportFile());
									}
								}
							}
							
						}

					}
				}, 3000, 30000);
				
				//timer per salvare i parametri di ogni paziente su file
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						date = new Date();
						dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						
						//salvo i parametri
						for (Paziente paziente : theModel.getListaParametriVitali().keySet()) {
							for (Observer observer : theModel.getListaObserver()) {
								if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
									theModel.getListaParametriVitali().get(paziente).add(new ParametriVitali(sbp, dbp, fc, tc, dateFormat.format(date)));
									theModel.writeParametriVitaliToFile(observer.getNomeObserver(), theModel.getListaParametriVitali().get(paziente));
								}
							}
						}
						
						//faccio le medie dei parametri
						for (Paziente paziente : theModel.getListaParametriVitaliMedia().keySet()) {
							for (Observer observer : theModel.getListaObserver()) {
								if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
									theModel.getListaParametriVitaliMedia().get(paziente).add(new ParametriVitaliMedia(observer.getSommaSBP()/observer.getCounterSBPeDBP(), 
											observer.getSommaDBP()/observer.getCounterSBPeDBP(), 
											observer.getSommaFC()/observer.getCounterFC(), 
											observer.getSommaTC()/observer.getCounterTC()));
								}
							}
						}

					}
				}, 60000, 60000);
				//}}
	}
	
	
}
