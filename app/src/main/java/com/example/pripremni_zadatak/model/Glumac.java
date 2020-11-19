package com.example.pripremni_zadatak.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = Glumac.TABLENAME)
public class Glumac {
    public static final String TABLENAME="Glumac";
    public static final String ID_FIELD="Id";
    public static final String IME_FIELD="Ime";
    public static final String PREZIME_FIELD="Prezime";
    public static final String BIO_FIELD="Biografija";
    public static final String OCENA_FIELD="Ocena";
    public static final String DATUM_FIELD="Datum";
    public static final String FILMOVI_FIELD="Filmivi";

    @DatabaseField(columnName = ID_FIELD,generatedId = true)
    private int id;
    @DatabaseField(columnName =IME_FIELD ,canBeNull = false)
    private String ime;
    @DatabaseField(columnName =PREZIME_FIELD,canBeNull = false)
    private String prezime;
    @DatabaseField(columnName =BIO_FIELD ,canBeNull = false)
    private String bio;
    @DatabaseField(columnName =OCENA_FIELD,canBeNull = false)
    private float ocena;
    @DatabaseField(columnName =DATUM_FIELD ,canBeNull = false)
    private Date date;
    @ForeignCollectionField(columnName = FILMOVI_FIELD)
    private List<Film>films;

    public Glumac() {
    }

    public int getId() {
        return id;
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
