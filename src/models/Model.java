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
	
	private ArrayList<Patient> patientList;
	private ArrayList<Nurse> nurseList;
	private ArrayList<Doctor> docList;
	private ArrayList<ChiefDoc> chiefList;
	private ArrayList<Prescription> prescriptionList;
	private ArrayList<Subministration> subList;
	private ArrayList<Medicine> medicineList;
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	private Map<Patient, ArrayList<VitalParameters>> vitalParametersList = new HashMap<Patient, ArrayList<VitalParameters>>();
	private Map<Patient, ArrayList<Means>> meansList = new HashMap<Patient, ArrayList<Means>>();
	 
	private Model() {
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
			if(object instanceof Patient) {
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
			} else if(object instanceof ChiefDoc) {
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
	public void writePrescriptionToFile(String nomeFile, ArrayList<Prescription> src) {
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
	public void writeSubToFile(String nomeFile, ArrayList<Subministration> src) {
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
	public void writeVitalParametersToFile(String nomeFile, ArrayList<VitalParameters> src) {
		try (FileWriter fileWriter = new FileWriter(new File("assets//Parametri Vitali//" + nomeFile + ".json"))) {
			org.json.JSONObject jsonObject = new org.json.JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (VitalParameters parametriVitali : src) {
				jsonArray.add(parametriVitali);
			}
			jsonObject.put("parametri vitali", jsonArray);
			fileWriter.write(jsonObject.toString());
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public void readPatientFromFile() {
		patientList = new ArrayList<Patient>();
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
					Patient patient = new Patient(codiceUnivocoSanitario, cognome, nome, luogoDiNascita, dottore, diagnosi, salone, dataDiNascita, dataRicovero, dataRilascio, statoPaziente);
					patientList.add(patient);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readNurseFromFile() {
		nurseList = new ArrayList<Nurse>();
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
					nurseList.add(infermiere);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readDocFromFile() {
		docList = new ArrayList<Doctor>();
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
					docList.add(medico);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readChiefFromFile() {
		chiefList = new ArrayList<>();
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
					ChiefDoc chiefDoc = new ChiefDoc(nome, cognome, codiceDiIdentificazione, tmpUser);
					chiefList.add(chiefDoc);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readPrescriptionFromFile(String nomeJson) {
		prescriptionList = new ArrayList<>();
		File file = new File("assets//Prescrizioni Mediche//" + nomeJson + ".json");
		if(file.length() != 0) {
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(new FileReader(file));
				for (Object o : ((Map<String, Object>)obj).values()) {
					for (Object o1 : (JSONArray)o) {
						Object prescrizioneMedica = ((Map<String, Object>)o1).get("prescrizioneMedica");
						String dataPrescrizione = ((Map<String, Object>)o1).get("dataPrescrizione").toString();
						medicineList = new ArrayList<>();
						for (Object o2 : (JSONArray)prescrizioneMedica) {
							Integer durataTerapia = Integer.parseInt(((Map<String, Object>)o2).get("durataTerapia").toString());
							String nomeFarmaco = ((Map<String, Object>)o2).get("nomeFarmaco").toString();
							Integer quantitaDiFarmacoPerDose = Integer.parseInt(((Map<String, Object>)o2).get("quantitaDiFarmacoPerDose").toString());
							Integer nrDosiGiornaliere = Integer.parseInt(((Map<String, Object>)o2).get("nrDosiGiornaliere").toString());
							Medicine farmaco = new Medicine(nomeFarmaco, durataTerapia, nrDosiGiornaliere, quantitaDiFarmacoPerDose);
							medicineList.add(farmaco);
						}
						Prescription prescrizione = new Prescription(medicineList, dataPrescrizione);
						prescriptionList.add(prescrizione);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readSubFromFile(String nomeJson) {
		subList = new ArrayList<>();
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
						subList.add(tmp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeRecords(Patient patient, File file) throws IOException {
		FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("CARTELLA CLINICA");
        bw.close();
	}
	
	public void removeObserver(Patient patient) {
		int index = 0;
		for (Observer observer : observerList) {
			if(observer.getObserverName().equals(patient.getName() + " " + patient.getLastName())) {
				index = observerList.indexOf(observer);
			}
		}
		observerList.remove(index);
	}
	
	public void removeVitalParameters(Patient patient) {
		vitalParametersList.remove(patient);
		meansList.remove(patient);
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
		
	public static Model getTheModel() {
		return theModel;
	}
	
	public static void setTheModel(Model theModel) {
		Model.theModel = theModel;
	}

	public ArrayList<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(ArrayList<Patient> patientList) {
		this.patientList = patientList;
	}

	public ArrayList<Nurse> getNurseList() {
		return nurseList;
	}

	public void setNurseList(ArrayList<Nurse> nurseList) {
		this.nurseList = nurseList;
	}

	public ArrayList<Doctor> getDocList() {
		return docList;
	}

	public void setDocList(ArrayList<Doctor> docList) {
		this.docList = docList;
	}

	public ArrayList<ChiefDoc> getChiefList() {
		return chiefList;
	}

	public void setChiefList(ArrayList<ChiefDoc> chiefList) {
		this.chiefList = chiefList;
	}

	public ArrayList<Prescription> getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(ArrayList<Prescription> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}

	public ArrayList<Medicine> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(ArrayList<Medicine> medicineList) {
		this.medicineList = medicineList;
	}

	public ArrayList<Subministration> getSubList() {
		return subList;
	}

	public void setSubList(ArrayList<Subministration> subList) {
		this.subList = subList;
	}

	public ArrayList<Observer> getObserverList() {
		return observerList;
	}

	public void setObserverList(ArrayList<Observer> observerList) {
		this.observerList = observerList;
	}

	public Map<Patient, ArrayList<VitalParameters>> getVitalParametersList() {
		return vitalParametersList;
	}

	public void setVitalParametersList(Map<Patient, ArrayList<VitalParameters>> vitalParametersList) {
		this.vitalParametersList = vitalParametersList;
	}

	public Map<Patient, ArrayList<Means>> getMeansList() {
		return meansList;
	}

	public void setMeansList(Map<Patient, ArrayList<Means>> meansList) {
		this.meansList = meansList;
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
	
}
