package com.example.lv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Student> student;
    Spinner spinnerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);
        spinnerBtn = findViewById(R.id.langBtn);

        student = StudentSingleton.getInstance().getStudents();

        StudentAdapter studentAdapter = new StudentAdapter(this, student);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton btnNxt = this.findViewById(R.id.next_button);
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PersonalInfoActivity.class);

                startActivity(i);
            }
        });

        //src: https://developer.android.com/develop/ui/views/components/spinner
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.languages,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerBtn.setAdapter(adapter);

        ArrayList<Locale> listOfLocales = new ArrayList<Locale>();
        listOfLocales.add(new Locale("de"));
        listOfLocales.add(new Locale("en"));
        listOfLocales.add(new Locale("hr"));
        spinnerBtn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getResources().getConfiguration().setLocale(listOfLocales.get(0));
                        break;
                    case 1:
                        getResources().getConfiguration().setLocale(listOfLocales.get(1));
                        break;
                    case 2:
                        getResources().getConfiguration().setLocale(listOfLocales.get(2));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }
}