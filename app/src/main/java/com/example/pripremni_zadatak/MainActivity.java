package com.example.pripremni_zadatak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pripremni_zadatak.fragmenti.MainFragment;
import com.example.pripremni_zadatak.fragmenti.OpisGlumcaFragment;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MainFragment mainFragment=new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,mainFragment)
                .commit();
    }


}