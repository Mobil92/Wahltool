package de.bbqesports.wahltool.db;

public class Auswertung {

	private Abstimmung abstimmung;
	private int ja;
	private int nein;
	private int enthaltung;

	public Auswertung(Abstimmung abstimmung, int ja, int nein, int enthaltung) {
		this.abstimmung = abstimmung;
		this.ja = ja;
		this.nein = nein;
		this.enthaltung = enthaltung;
	}

	public Abstimmung getAbstimmung() {
		return abstimmung;
	}

	public long getAbstimmungId() {
		return abstimmung.getId();
	}

	public void setAbstimmung(Abstimmung abstimmung) {
		this.abstimmung = abstimmung;
	}

	public int getJa() {
		return ja;
	}

	public void setJa(int ja) {
		this.ja = ja;
	}

	public int getNein() {
		return nein;
	}

	public void setNein(int nein) {
		this.nein = nein;
	}

	public int getEnthaltung() {
		return enthaltung;
	}

	public void setEnthaltung(int enthaltung) {
		this.enthaltung = enthaltung;
	}

	public String getAbstimmungTitel() {
		return abstimmung.getAbstimmungTitel();
	}

}
