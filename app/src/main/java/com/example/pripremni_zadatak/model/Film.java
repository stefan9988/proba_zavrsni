package com.example.pripremni_zadatak.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName =Film.TABLENAME )
public class Film {
    public static final String TABLENAME="Film";
    public static final String ID_FIELD="Id";
    public static final String IME_FIELD="Ime";
    public static final String ZANR_FIELD="Zanr";
    public static final String GODINA_FIELD="Godina";

    @DatabaseField(columnName = ID_FIELD,generatedId = true)
    private int id;
    @DatabaseField(columnName = IME_FIELD,canBeNull = false)
    private String ime;
    @DatabaseField(columnName = ZANR_FIELD,canBeNull = false)
    private String zanr;
    @DatabaseField(columnName = GODINA_FIELD,canBeNull = false)
    private Date godina;

    public Film() {
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

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public Date getGodina() {
        return godina;
    }

    public void setGodina(Date godina) {
        this.godina = godina;
    }
}
