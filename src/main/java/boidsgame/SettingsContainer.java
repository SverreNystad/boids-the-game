package boidsgame;

public class SettingsContainer {
	private String gameMode = "Hoid";
	private String wraparound = "on";
	private int startBoidsAmountSliderValue;
	private int startPoidProsentSliderValue;
	private int poidViewRangeSliderValue = 60;
	private int killRadiusSliderValue = 5;
	private double poidSeperationCoefficientValue = 1;
	private double AttractionToHoidsCoefficientValue = 1;
	private int hoidViewRangeSliderValue = 40;
	private double CohesionCoefficientValue = 1;
	private double AlignmentCoefficientValue = 1;
	private double hoidSeperationCoefficientValue = 1;

	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public String getWraparound() {
		return wraparound;
	}
	public void setWraparound(String wraparound) {
		this.wraparound = wraparound;
	}
	public int getStartBoidsAmountSliderValue() {
		return startBoidsAmountSliderValue;
	}
	public void setStartBoidsAmountSliderValue(int startBoidsAmountSliderValue) {
		this.startBoidsAmountSliderValue = startBoidsAmountSliderValue;
	}
	public int getStartPoidProsentSliderValue() {
		return startPoidProsentSliderValue;
	}
	public void setStartPoidProsentSliderValue(int startPoidProsentSliderValue) {
		this.startPoidProsentSliderValue = startPoidProsentSliderValue;
	}
	public int getPoidViewRangeSliderValue() {
		return poidViewRangeSliderValue;
	}
	public void setPoidViewRangeSliderValue(int poidViewRangeSliderValue) {
		this.poidViewRangeSliderValue = poidViewRangeSliderValue;
	}
	public int getKillRadiusSliderValue() {
		return killRadiusSliderValue;
	}
	public void setKillRadiusSliderValue(int killRadiusSliderValue) {
		this.killRadiusSliderValue = killRadiusSliderValue;
	}
	public double getPoidSeperationCoefficientValue() {
		return poidSeperationCoefficientValue;
	}
	public void setPoidSeperationCoefficientValue(double poidSeperationCoefficientValue) {
		this.poidSeperationCoefficientValue = poidSeperationCoefficientValue;
	}
	public double getAttractionToHoidsCoefficientValue() {
		return AttractionToHoidsCoefficientValue;
	}
	public void setAttractionToHoidsCoefficientValue(double attractionToHoidsCoefficientValue) {
		AttractionToHoidsCoefficientValue = attractionToHoidsCoefficientValue;
	}
	public int getHoidViewRangeSliderValue() {
		return hoidViewRangeSliderValue;
	}
	public void setHoidViewRangeSliderValue(int hoidViewRangeSliderValue) {
		this.hoidViewRangeSliderValue = hoidViewRangeSliderValue;
	}
	public double getCohesionCoefficientValue() {
		return CohesionCoefficientValue;
	}
	public void setCohesionCoefficientValue(double cohesionCoefficientValue) {
		CohesionCoefficientValue = cohesionCoefficientValue;
	}
	public double getAlignmentCoefficientValue() {
		return AlignmentCoefficientValue;
	}
	public void setAlignmentCoefficientValue(double alignmentCoefficientValue) {
		AlignmentCoefficientValue = alignmentCoefficientValue;
	}
	public double getHoidSeperationCoefficientValue() {
		return hoidSeperationCoefficientValue;
	}
	public void setHoidSeperationCoefficientValue(double hoidSeperationCoefficientValue) {
		this.hoidSeperationCoefficientValue = hoidSeperationCoefficientValue;
	}

	@Override
	public String toString(){
		return this.getGameMode() + " " + this.getStartBoidsAmountSliderValue() + " " + this.getStartPoidProsentSliderValue() + " " + this.getWraparound() + " " +
			this.getPoidViewRangeSliderValue() + " " + this.getKillRadiusSliderValue() + " " + this.getPoidSeperationCoefficientValue() + " " + this.getAttractionToHoidsCoefficientValue() + " " +
			this.getHoidViewRangeSliderValue() + " " + this.getCohesionCoefficientValue() + " " + this.getAlignmentCoefficientValue() + " " + this.getHoidSeperationCoefficientValue();
		
	}

	
}
