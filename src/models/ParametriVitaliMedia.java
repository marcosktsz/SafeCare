package models;

public class ParametriVitaliMedia {
	Double mediaSBP;
	Double mediaDBP;
	Double mediaFC;
	Double mediaTC;
	
	public ParametriVitaliMedia(Double mediaSBP, Double mediaDBP, Double mediaFC, Double mediaTC) {
		this.mediaSBP = mediaSBP;
		this.mediaDBP = mediaDBP;
		this.mediaFC = mediaFC;
		this.mediaTC = mediaTC;
	}
	
	public Double getMediaSBP() {
		return mediaSBP;
	}
	public void setMediaSBP(Double mediaSBP) {
		this.mediaSBP = mediaSBP;
	}
	public Double getMediaDBP() {
		return mediaDBP;
	}
	public void setMediaDBP(Double mediaDBP) {
		this.mediaDBP = mediaDBP;
	}
	public Double getMediaFC() {
		return mediaFC;
	}
	public void setMediaFC(Double mediaFC) {
		this.mediaFC = mediaFC;
	}
	public Double getMediaTC() {
		return mediaTC;
	}
	public void setMediaTC(Double mediaTC) {
		this.mediaTC = mediaTC;
	}
	
	
}
