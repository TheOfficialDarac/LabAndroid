package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class StudentInfoActivity extends AppCompatActivity {
    public String Predmet;
    public String ImeProfesora;
    public String satiPR;
    public String satiLV;
    public boolean isIzborni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Switch sw = findViewById(R.id.izborniBool);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isIzborni = true;
                } else {
                    isIzborni = false;
                }
            }
        });

        Button btnNext = findViewById(R.id.btnFinal);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText predmet = findViewById(R.id.tilPredmet);
                Predmet = predmet.getText().toString();

                TextInputEditText imeProf = findViewById(R.id.tilProffesorName);
                ImeProfesora = imeProf.getText().toString();

                TextInputEditText satilv = findViewById(R.id.satiLV);
                satiLV = satilv.getText().toString();

                TextInputEditText satipr = findViewById(R.id.satiPR);
                satiPR = satipr.getText().toString();

                if (Predmet.isEmpty() || ImeProfesora.isEmpty() || satiPR.isEmpty()) {
                    Toast.makeText(StudentInfoActivity.this, "Empty Value", Toast.LENGTH_SHORT).show();
                } else if (Predmet.matches(".*\\d.*") || ImeProfesora.matches(".*\\d.*")) {
                    Toast.makeText(StudentInfoActivity.this, "Predmet sadrži broj", Toast.LENGTH_SHORT).show();
                } else {
                    Intent openFinalActivity = new Intent(getApplicationContext(), SummaryActivity.class);
                    Intent i = getIntent();
                    Bundle extras = i.getExtras();

                    extras.putString("Predmet", Predmet);
                    extras.putString("ImeProfesora", ImeProfesora);
                    extras.putString("satiPR", satiPR);
                    extras.putString("satiLV", satiLV);
                    extras.putBoolean("izborni", isIzborni);

                    openFinalActivity.putExtras(extras);
                    startActivity(openFinalActivity);
                    finish();
                }
            }
        });
    }
}