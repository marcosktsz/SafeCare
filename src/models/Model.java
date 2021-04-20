package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Model {
	private static Model theModel;
	File file;
	File oldReportFile = new File("assets//Report settimanali");
	File newReportFile = new File("assets//Report settimanali");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	private ArrayList<Paziente> listaPazienti;
	private ArrayList<Nurse> listaInfermieri;
	private ArrayList<Doctor> listaMedici;
	private ArrayList<Primario> listaPrimari;
	private ArrayList<Prescription> listaPrescrizioni;
	private ArrayList<Subministration> listaSomministrazioni;
	private ArrayList<Medicine> listaFarmaci;
	private ArrayList<Observer> listaObserver = new ArrayList<Observer>();
	private Map<Paziente, ArrayList<ParametriVitali>> listaParametriVitali = new HashMap<Paziente, ArrayList<ParametriVitali>>();
	private Map<Paziente, ArrayList<ParametriVitaliMedia>> listaParametriVitaliMedia = new HashMap<Paziente, ArrayList<ParametriVitaliMedia>>();
	 
	private Model() {
		//empty
	}
	
	public static Model getInstance() {
		if(theModel == null) {
			theModel = new Model();
			return theModel;
		}
		return theModel;
	}
	
	public void writeToFile(ArrayList<?> src) {
		String nomeToJson = "";
		for (Object object : src) {
			if(object instanceof Paziente) {
				file = new File("assets//filePazienti.json");
				nomeToJson = "Pazienti";
				break;
			} else if(object instanceof Nurse) {
				file = new File("assets//fileInfermieri.json");
				nomeToJson = "Infermieri";
				break;
			} else if(object instanceof Doctor) {
				file = new File("assets//fileMedici.json");
				nomeToJson = "Medici";
				break;
			} else if(object instanceof Primario) {
				file = new File("assets//filePrimari.json");
				nomeToJson = "Primari";
				break;
			}
		}
		
		writeObjectsToFile(nomeToJson, file, src);
	}
	
	@SuppressWarnings("unchecked")
	public void writeObjectsToFile(String nomeToJson, File file, ArrayList<?> src) {
		try (FileWriter fileWriter = new FileWriter(file)) {
			org.json.JSONObject jsonObject = new org.json.JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (Object object : src) {
				jsonArray.add(object);				
			}
			jsonObject.put(nomeToJson, jsonArray);
			fileWriter.write(jsonObject.toString());
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public void writePrescrizioniToFile(String nomeFile, ArrayList<Prescription> src) {
		try (FileWriter fileWriter = new FileWriter(new File("assets//Prescrizioni Mediche//" + nomeFile + ".json"))) {
			org.json.JSONObject jsonObject = new org.json.JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (Prescription prescrizione : src) {
				jsonArray.add(prescrizione);				
			}
			jsonObject.put("prescrizioni", jsonArray);
			fileWriter.write(jsonObject.toString());
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public void writeSomministrazioniToFile(String nomeFile, ArrayList<Subministration> src) {
		try (FileWriter fileWriter = new FileWriter(new File("assets//Somministrazioni Farmaci//" + nomeFile + ".json"))) {
			org.json.JSONObject jsonObject = new org.json.JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (Subministration somministrazione : src) {
				jsonArray.add(somministrazione);				
			}
			jsonObject.put("somministrazioni", jsonArray);
			fileWriter.write(jsonObject.toString());
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public void writeParametriVitaliToFile(String nomeFile, ArrayList<ParametriVitali> src) {
		try (FileWriter fileWriter = new FileWriter(new File("assets//Parametri Vitali//" + nomeFile + ".json"))) {
			org.json.JSONObject jsonObject = new org.json.JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (ParametriVitali parametriVitali : src) {
				jsonArray.add(parametriVitali);				
			}
			jsonObject.put("parametri vitali", jsonArray);
			fileWriter.write(jsonObject.toString());
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public void readPazientiFromFile() {
		listaPazienti = new ArrayList<Paziente>();
		JSONParser jsonParser = new JSONParser();
		try {
			Object obj = jsonParser.parse(new FileReader("assets//filePazienti.json"));
			for (Object o : ((Map<String, Object>)obj).values()) {
				for (Object o1 : (JSONArray)o) {
					String codiceUnivocoSanitario = ((Map<String, Object>)o1).get("codiceUnivocoSanitario").toString();
					String cognome = ((Map<String, Object>)o1).get("cognome").toString();
					String nome = ((Map<String, Object>)o1).get("nome").toString();
					String luogoDiNascita = ((Map<String, Object>)o1).get("luogoDiNascita").toString();
					String dottore = ((Map<String, Object>)o1).get("dottore").toString();
					String diagnosi = ((Map<String, Object>)o1).get("diagnosi").toString();
					String salone = ((Map<String, Object>)o1).get("salone").toString();
					String dataDiNascita = ((Map<String, Object>)o1).get("dataDiNascita").toString();
					String dataRicovero = ((Map<String, Object>)o1).get("dataRicovero").toString();
					String dataRilascio = ((Map<String, Object>)o1).get("dataRilascio").toString();
					String statoPaziente = ((Map<String, Object>)o1).get("statoPaziente").toString();
					Paziente paziente = new Paziente(codiceUnivocoSanitario, cognome, nome, luogoDiNascita, dottore, diagnosi, salone, dataDiNascita, dataRicovero, dataRilascio, statoPaziente);
					listaPazienti.add(paziente);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readInfermieriFromFile() {
		listaInfermieri = new ArrayList<Nurse>();
		JSONParser jsonParser = new JSONParser();
		try {
			Object obj = jsonParser.parse(new FileReader("assets//fileInfermieri.json"));
			for (Object o : ((Map<String, Object>)obj).values()) {
				for (Object o1 : (JSONArray)o) {
					String codiceDiIdentificazione = ((Map<String, Object>)o1).get("codiceDiIdentificazione").toString();
					String cognome = ((Map<String, Object>)o1).get("cognome").toString();
					String nome = ((Map<String, Object>)o1).get("nome").toString();
					Object user = ((Map<String, Object>)o1).get("autentificazione");
					String id = ((Map<String, Object>)user).get("id").toString();
					String pass = ((Map<String, Object>)user).get("pass").toString();
					User tmpUser = new User(id, pass);
					Nurse infermiere = new Nurse(nome, cognome, codiceDiIdentificazione, tmpUser);
					listaInfermieri.add(infermiere);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readMediciFromFile() {
		listaMedici = new ArrayList<Doctor>();
		JSONParser jsonParser = new JSONParser();
		try {
			Object obj = jsonParser.parse(new FileReader("assets//fileMedici.json"));
			for (Object o : ((Map<String, Object>)obj).values()) {
				for (Object o1 : (JSONArray)o) {
					String codiceDiIdentificazione = ((Map<String, Object>)o1).get("codiceDiIdentificazione").toString();
					String cognome = ((Map<String, Object>)o1).get("cognome").toString();
					String nome = ((Map<String, Object>)o1).get("nome").toString();
					Object user = ((Map<String, Object>)o1).get("autentificazione");
					String id = ((Map<String, Object>)user).get("id").toString();
					String pass = ((Map<String, Object>)user).get("pass").toString();
					User tmpUser = new User(id, pass);
					Doctor medico = new Doctor(nome, cognome, codiceDiIdentificazione, tmpUser);
					listaMedici.add(medico);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readPrimariFromFile() {
		listaPrimari = new ArrayList<>();
		JSONParser jsonParser = new JSONParser();
		try {
			Object obj = jsonParser.parse(new FileReader("assets//filePrimari.json"));
			for (Object o : ((Map<String, Object>)obj).values()) {
				for (Object o1 : (JSONArray)o) {
					String codiceDiIdentificazione = ((Map<String, Object>)o1).get("codiceDiIdentificazione").toString();
					String cognome = ((Map<String, Object>)o1).get("cognome").toString();
					String nome = ((Map<String, Object>)o1).get("nome").toString();
					Object user = ((Map<String, Object>)o1).get("autentificazione");
					String id = ((Map<String, Object>)user).get("id").toString();
					String pass = ((Map<String, Object>)user).get("pass").toString();
					User tmpUser = new User(id, pass);
					Primario primario = new Primario(nome, cognome, codiceDiIdentificazione, tmpUser);
					listaPrimari.add(primario);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readPrescrizioniFromFile(String nomeJson) {
		listaPrescrizioni = new ArrayList<>();
		File file = new File("assets//Prescrizioni Mediche//" + nomeJson + ".json");
		if(file.length() != 0) {
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(new FileReader(file));
				for (Object o : ((Map<String, Object>)obj).values()) {
					for (Object o1 : (JSONArray)o) {
						Object prescrizioneMedica = ((Map<String, Object>)o1).get("prescrizioneMedica");
						String dataPrescrizione = ((Map<String, Object>)o1).get("dataPrescrizione").toString();
						listaFarmaci = new ArrayList<>();
						for (Object o2 : (JSONArray)prescrizioneMedica) {
							Integer durataTerapia = Integer.parseInt(((Map<String, Object>)o2).get("durataTerapia").toString());
							String nomeFarmaco = ((Map<String, Object>)o2).get("nomeFarmaco").toString();
							Integer quantitaDiFarmacoPerDose = Integer.parseInt(((Map<String, Object>)o2).get("quantitaDiFarmacoPerDose").toString());
							Integer nrDosiGiornaliere = Integer.parseInt(((Map<String, Object>)o2).get("nrDosiGiornaliere").toString());
							Medicine farmaco = new Medicine(nomeFarmaco, durataTerapia, nrDosiGiornaliere, quantitaDiFarmacoPerDose);
							listaFarmaci.add(farmaco);
						}
						Prescription prescrizione = new Prescription(listaFarmaci, dataPrescrizione);
						listaPrescrizioni.add(prescrizione);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readSomministrazioniFromFile(String nomeJson) {
		listaSomministrazioni = new ArrayList<>();
		File file = new File("assets//Somministrazioni Farmaci//" + nomeJson + ".json");
		if(file.length() != 0) {
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(new FileReader(file));
				for (Object o : ((Map<String, Object>)obj).values()) {
					for (Object o1 : (JSONArray)o) {
						String todayDate = ((Map<String, Object>)o1).get("todayDate").toString();
						String nomeFarmaco = ((Map<String, Object>)o1).get("nomeFarmaco").toString();
						String nomeInfermiere = ((Map<String, Object>)o1).get("nomeInfermiere").toString();
						Integer quantitaDosi = Integer.parseInt(((Map<String, Object>)o1).get("quantitaDosi").toString());
						Subministration tmp = new Subministration(nomeFarmaco, todayDate, quantitaDosi, nomeInfermiere);
						listaSomministrazioni.add(tmp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeCartellaClinica(Paziente paziente, File file) throws IOException {
		FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("CARTELLA CLINICA");
        bw.close();
	}
	
	public void removeObserver(Paziente paziente) {
		int index = 0;
		for (Observer observer : listaObserver) {
			if(observer.getNomeObserver().equals(paziente.getNome() + " " + paziente.getCognome())) {
				index = listaObserver.indexOf(observer);
			}
		}
		listaObserver.remove(index);
	}
	
	public void removeParametriVitali(Paziente paziente) {
		listaParametriVitali.remove(paziente);
		listaParametriVitaliMedia.remove(paziente);
	}
	
	@SuppressWarnings("unused")
	public File getLatestFilefromDir(String dirPath){
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		
		return lastModifiedFile;
	}
	
	//{{ FILE REPORT CONTROLS
		public void writeReportTitleToFile(models.Date date, File file) {
			String textToAppend = "REPORT SETTIMANALE dal " + date.toString() + "\n";
		     
		    BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter("assets//Report settimanali//" + file.getName(), true));
				writer.newLine();   //Add new line
				writer.write(textToAppend);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		public void appendToReportFile(String textToAppend, File file) {
			 
		    BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter("assets//Report settimanali//" + file.getName(), true));
				writer.newLine();   //Add new line
				writer.write(textToAppend);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//}}
	
	//{{ GETTERS AND SETTERS
	public static Model getTheModel() {
		return theModel;
	}
	
	public static void setTheModel(Model theModel) {
		Model.theModel = theModel;
	}

	public ArrayList<Paziente> getListaPazienti() {
		return listaPazienti;
	}

	public void setListaPazienti(ArrayList<Paziente> listaPazienti) {
		this.listaPazienti = listaPazienti;
	}

	public ArrayList<Nurse> getListaInfermieri() {
		return listaInfermieri;
	}

	public void setListaInfermieri(ArrayList<Nurse> listaInfermieri) {
		this.listaInfermieri = listaInfermieri;
	}

	public ArrayList<Doctor> getListaMedici() {
		return listaMedici;
	}

	public void setListaMedici(ArrayList<Doctor> listaMedici) {
		this.listaMedici = listaMedici;
	}

	public ArrayList<Primario> getListaPrimari() {
		return listaPrimari;
	}

	public void setListaPrimari(ArrayList<Primario> listaPrimari) {
		this.listaPrimari = listaPrimari;
	}

	public ArrayList<Prescription> getListaPrescrizioni() {
		return listaPrescrizioni;
	}

	public void setListaPrescrizioni(ArrayList<Prescription> listaPrescrizioni) {
		this.listaPrescrizioni = listaPrescrizioni;
	}

	public ArrayList<Medicine> getListaFarmaci() {
		return listaFarmaci;
	}

	public void setListaFarmaci(ArrayList<Medicine> listaFarmaci) {
		this.listaFarmaci = listaFarmaci;
	}

	public ArrayList<Subministration> getListaSomministrazioni() {
		return listaSomministrazioni;
	}

	public void setListaSomministrazioni(ArrayList<Subministration> listaSomministrazioni) {
		this.listaSomministrazioni = listaSomministrazioni;
	}

	public ArrayList<Observer> getListaObserver() {
		return listaObserver;
	}

	public void setListaObserver(ArrayList<Observer> listaObserver) {
		this.listaObserver = listaObserver;
	}

	public Map<Paziente, ArrayList<ParametriVitali>> getListaParametriVitali() {
		return listaParametriVitali;
	}

	public void setListaParametriVitali(Map<Paziente, ArrayList<ParametriVitali>> listaParametriVitali) {
		this.listaParametriVitali = listaParametriVitali;
	}

	public Map<Paziente, ArrayList<ParametriVitaliMedia>> getListaParametriVitaliMedia() {
		return listaParametriVitaliMedia;
	}

	public void setListaParametriVitaliMedia(Map<Paziente, ArrayList<ParametriVitaliMedia>> listaParametriVitaliMedia) {
		this.listaParametriVitaliMedia = listaParametriVitaliMedia;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getOldReportFile() {
		return oldReportFile;
	}

	public void setOldReportFile(File oldReportFile) {
		this.oldReportFile = oldReportFile;
	}

	public File getNewReportFile() {
		return newReportFile;
	}

	public void setNewReportFile(File newReportFile) {
		this.newReportFile = newReportFile;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}	
	
	//}}
}
