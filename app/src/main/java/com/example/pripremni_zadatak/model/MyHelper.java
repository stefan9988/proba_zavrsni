package com.example.pripremni_zadatak.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pripremni_zadatak.model.Film;
import com.example.pripremni_zadatak.model.Glumac;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class MyHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="ormlite.db";
    private static final int DATABASE_VERSION=1;
    private Dao<Glumac,Integer>glumacDao=null;
    private Dao<Film,Integer>filmDao=null;

    public MyHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Glumac.class);
            TableUtils.createTable(connectionSource,Film.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Glumac.class,true);
            TableUtils.dropTable(connectionSource,Film.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public Dao<Glumac,Integer>getGlumacDao() throws SQLException{
        if (glumacDao==null){
            glumacDao=getDao(Glumac.class);
        }
        return glumacDao;
    }

    public Dao<Film,Integer>getFilmDao() throws SQLException{
        if (filmDao==null){
            filmDao=getDao(Film.class);
        }
        return filmDao;
    }

    @Override
    public void close() {
        super.close();
        glumacDao=null;
        filmDao=null;
    }
}
