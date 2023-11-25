package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SummaryActivity extends AppCompatActivity {
    TextView tvIme, tvPrezime, tvDatumRodenja, tvPredmet, tvProfesor, tvStatusPredmeta, tvSatiPR, tvSatiLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        tvIme = findViewById(R.id.tvName);
        tvIme.setText("Ime: " + bundle.getString("Ime"));

        tvPrezime = findViewById(R.id.tvPrezime);
        tvPrezime.setText("Prezime: " + bundle.getString("Prezime"));

        tvDatumRodenja = findViewById(R.id.tvDatumRodenja);
        tvDatumRodenja.setText("Datum rođenja: " + bundle.getString("DatumRodenja"));

        tvPredmet = findViewById(R.id.tvPredmet);
        tvPredmet.setText(bundle.getString("Predmet"));

        tvProfesor = findViewById(R.id.tvProfesor);
        tvProfesor.setText(bundle.getString("ImeProfesora"));

        tvStatusPredmeta = findViewById(R.id.tvStatusPredmeta);
        if(bundle.getBoolean("izborni"))
            tvStatusPredmeta.setText("Izborni predmet");
        else
            tvStatusPredmeta.setText("Obvezni predmet");

        tvSatiPR = findViewById(R.id.tvSatiPR);
        tvSatiPR.setText(bundle.getString("satiPR"));

        tvSatiLv = findViewById(R.id.tvSatiLv);
        tvSatiLv.setText(bundle.getString("satiLV"));

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentSingleton.getInstance().addStudent(new Student(tvIme.getText().toString(), tvPrezime.getText().toString(), tvPredmet.getText().toString()));

                Intent toHomeActivity = new Intent(getApplicationContext(), HomeActivity.class);

                startActivity(toHomeActivity);
                finish();
            }
        });
    }
}