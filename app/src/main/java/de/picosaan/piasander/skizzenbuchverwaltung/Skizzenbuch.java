package de.picosaan.piasander.skizzenbuchverwaltung;

public class Skizzenbuch {

    private String buch_id;
    private String name;
    private long seitenzahl;
    private long start_datum;
    private long start_seitenzahl;
    private long aktuelle_seitenzahl;
    private long deadline;


    public Skizzenbuch(String buch_id, String name, long seitenzahl, long start_datum, long start_seitenzahl, long deadline) {
        this.buch_id = buch_id;
        this.name = name;
        this.seitenzahl = seitenzahl;
        this.start_datum = start_datum;
        this.start_seitenzahl = start_seitenzahl;
        this.deadline = deadline;
    }


    public String getBuch_id() {
        return buch_id;
    }

    public String getName() {
        return name;
    }

    public long getSeitenzahl() {
        return seitenzahl;
    }

    public long getStart_datum() {
        return start_datum;
    }

    public long getStart_seitenzahl() {
        return start_seitenzahl;
    }

    public  long getAktuelle_seitenzahl(){
        return  aktuelle_seitenzahl;
    }

    public long getDeadline() {
        return deadline;
    }


    public void setBuch_id(String buch_id) {
        this.buch_id = buch_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeitenzahl(long seitenzahl) {
        this.seitenzahl = seitenzahl;
    }

    public void setStart_datum(long start_datum) {
        this.start_datum = start_datum;
    }

    public void setStart_seitenzahl(long start_seitenzahl) {
        this.start_seitenzahl = start_seitenzahl;
    }

    public  void setAktuelle_seitenzahl(long aktuelle_seitenzahl){
        this.aktuelle_seitenzahl = aktuelle_seitenzahl;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

}
