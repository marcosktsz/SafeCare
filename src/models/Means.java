package models;

public class Means {
	Double SBPMean;
	Double DBPMean;
	Double oxMean;
	Double btMean;
	
	public Means(Double SBPMean, Double DBPMean, Double oxMean, Double btMean) {
		this.SBPMean = SBPMean;
		this.DBPMean = DBPMean;
		this.oxMean = oxMean;
		this.btMean = btMean;
	}
	
	public Double getSBPMean() {
		return SBPMean;
	}
	public void setSBPMean(Double SBPMean) {
		this.SBPMean = SBPMean;
	}
	public Double getDBPMean() {
		return DBPMean;
	}
	public void setDBPMean(Double DBPMean) {
		this.DBPMean = DBPMean;
	}
	public Double getOxMean() {
		return oxMean;
	}
	public void setOxMean(Double oxMean) {
		this.oxMean = oxMean;
	}
	public Double getBtMean() {
		return btMean;
	}
	public void setBtMean(Double btMean) {
		this.btMean = btMean;
	}
	
	
}
