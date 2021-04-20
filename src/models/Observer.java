package models;

public class Observer{

	private String observerName;
	private Double sysPressure;
	private Double diasPressure;
	private Double oxSaturation;
	private Double bodyTemp;

	private Double SBPSum = (double) 0;
	private Double DBPSum = (double) 0;
	private Double oxSum = (double) 0;
	private Double btSum = (double) 0;
	private Integer counterSBPeDBP = 0;
	private Integer oxCounter = 0;
	private Integer btCounter = 0;
	
	public Observer(String observerName) {
		this.observerName = observerName;
	}
	
	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}

	public Double getSysPressure() {
		return sysPressure;
	}

	public void setSysPressure(Double sysPressure) {
		this.sysPressure = sysPressure;
	}

	public Double getDiasPressure() {
		return diasPressure;
	}

	public void setDiasPressure(Double diasPressure) {
		this.diasPressure = diasPressure;
	}

	public Double getOxSaturation() {
		return oxSaturation;
	}

	public void setOxSaturation(Double oxSaturation) {
		this.oxSaturation = oxSaturation;
	}
		

	public Double getBodyTemp() {
		return bodyTemp;
	}

	public void setBodyTemp(Double bodyTemp) {
		this.bodyTemp = bodyTemp;
	}
	
	public Double getSBPSum() {
		return SBPSum;
	}

	public void setSBPSum(Double SBPSum) {
		this.SBPSum = SBPSum;
	}

	public Double getDBPSum() {
		return DBPSum;
	}

	public void setDBPSum(Double DBPSum) {
		this.DBPSum = DBPSum;
	}

	public Double getOxSum() {
		return oxSum;
	}

	public void setOxSum(Double oxSum) {
		this.oxSum = oxSum;
	}

	public Double getBtSum() {
		return btSum;
	}

	public void setBtSum(Double btSum) {
		this.btSum = btSum;
	}
	
	public Integer getCounterSBPeDBP() {
		return counterSBPeDBP;
	}

	public void setCounterSBPeDBP(Integer counterSBPeDBP) {
		this.counterSBPeDBP = counterSBPeDBP;
	}

	public Integer getOxCounter() {
		return oxCounter;
	}

	public void setOxCounter(Integer oxCounter) {
		this.oxCounter = oxCounter;
	}

	public Integer getBtCounter() {
		return btCounter;
	}

	public void setBtCounter(Integer btCounter) {
		this.btCounter = btCounter;
	}

	public void print() {
		System.out.println(observerName + ":\n" + "[sysPressure=" + sysPressure
				+ ", diasPressure=" + diasPressure + ", oxSaturation=" + oxSaturation
				+ ", bodyTemp=" + bodyTemp + "]");
	}
}
