package com.example.pripremni_zadatak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pripremni_zadatak.fragmenti.MainFragment;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Glumac>glumacList;
    private MyHelper myHelper=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //refresh();
        //addItem();

        MainFragment mainFragment=new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,mainFragment)
                .commit();
    }

    private void addItem(){
        Glumac glumac=new Glumac();
        glumac.setDate(new Date());
        glumac.setOcena(3);
        glumac.setBio("fdgfds");
        glumac.setPrezime("fdgdfg");
        glumac.setIme("dfgfdg");

        try {
            getMyhelper().getGlumacDao().create(glumac);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void refresh() {
        try {
            glumacList=new ArrayList<>();
            glumacList =getMyhelper().getGlumacDao().queryForAll();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MyHelper getMyhelper(){
        if (myHelper==null){
            myHelper= OpenHelperManager.getHelper(this,MyHelper.class);
        }
        return  myHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myHelper!= null){
            OpenHelperManager.releaseHelper();
            myHelper=null;
        }
    }
}