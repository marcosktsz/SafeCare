package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import models.*;
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
						
						for (Observer observer: theModel.getObserverList()) {
							sbp = (double) (rand.nextGaussian() * 4) + 105;				
							dbp = (double) (rand.nextGaussian() * 3) + 70;
							observer.setSysPressure(sbp);
							observer.setDiasPressure(dbp);
							observer.setSBPSum(observer.getSBPSum() + sbp);
							observer.setDBPSum(observer.getDBPSum() + dbp);
							observer.setCounterSBPeDBP(observer.getCounterSBPeDBP() + 1);
							
							//ipotensione
							if(observer.getSysPressure() <= 90 && observer.getDiasPressure() <= 60) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "IPOTENSIONE", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "IPOTENSIONE - Livello 2", theModel.getNewReportFile());
									}
								}
							}
							
							//ipertensione
							if(observer.getSysPressure() >= 130 && observer.getDiasPressure() >= 90) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "IPERTENSIONE", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
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
						
						for (Observer observer: theModel.getObserverList()) {
							fc = (double) (rand.nextGaussian() * 3) + 85;	
							observer.setOxSaturation(fc);
							observer.setOxSum(observer.getOxSum() + fc);
							observer.setOxCounter(observer.getOxCounter() + 1);
							
							//tachicardia
							if(observer.getOxSaturation() >= 100) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "TACHICARDIA", new Integer(1).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "TACHICARDIA - Livello 1", theModel.getNewReportFile());
									}
								}
							}
							
							//brachicardia
							if(observer.getOxSaturation() <= 60) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "BRACHICARDIA", new Integer(1).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "BRACHICARDIA - Livello 1", theModel.getNewReportFile());
									}
								}
							}
							
							//fibrilazione ventricolare
							if(observer.getOxSaturation() >= 140) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "FLUTTER", new Integer(3).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
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
						
						for (Observer observer: theModel.getObserverList()) {
							tc = (double) (rand.nextGaussian() * 0.2) + 37;
							observer.setBodyTemp(tc);
							observer.setBtSum(observer.getBtSum() + tc);
							observer.setBtCounter(observer.getBtCounter() + 1);
							
							//ipotermia
							if(observer.getBodyTemp() <= 36.5) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "IPOTERMIA", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
												"\n\t\tAllarme in corso:\n\t\t\t" + "IPOTERMIA - Livello 2", theModel.getNewReportFile());
									}
								}
							}
							
							//ipetermia
							if(observer.getBodyTemp() >= 37.5) {
								for (Patient patient : theModel.getPatientList()) {
									if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
										String[] tmp = {patient.getName(), patient.getLastName(), patient.getRoom(), "IPETERMIA", new Integer(2).toString()};
										mainFrame.getTableModelAllarmi().addRow(tmp);
										theModel.appendToReportFile("\n[" + theModel.getDateFormat().format(mainFrame.getTodayDate()) + "]\tPatient: " + patient.getName() + " " +
												patient.getLastName() + "[Codice Fiscale: " + patient.getHealthId() + "]" +
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
						for (Patient patient : theModel.getVitalParametersList().keySet()) {
							for (Observer observer : theModel.getObserverList()) {
								if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
									theModel.getVitalParametersList().get(patient).add(new VitalParameters(sbp, dbp, fc, tc, dateFormat.format(date)));
									theModel.writeVitalParametersToFile(observer.getObserverName(), theModel.getVitalParametersList().get(patient));
								}
							}
						}
						
						//faccio le medie dei parametri
						for (Patient patient : theModel.getMeansList().keySet()) {
							for (Observer observer : theModel.getObserverList()) {
								if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
									theModel.getMeansList().get(patient).add(new Means(observer.getSBPSum()/observer.getCounterSBPeDBP(),
											observer.getDBPSum()/observer.getCounterSBPeDBP(),
											observer.getOxSum()/observer.getOxCounter(),
											observer.getBtSum()/observer.getBtCounter()));
								}
							}
						}

					}
				}, 60000, 60000);
				//}}
	}
	
	
}
