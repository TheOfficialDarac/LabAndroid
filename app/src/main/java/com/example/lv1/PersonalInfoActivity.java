package com.example.lv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class PersonalInfoActivity extends AppCompatActivity {
    public String Ime;
    public String Prezime;
    public String DatumRodenja;
    public TextInputEditText tilBirthDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        ImageView imV = findViewById(R.id.imgProfile);
        imV.setImageResource(R.drawable.profile_pic);

        MaterialDatePicker Cal = MaterialDatePicker.Builder.datePicker().setTitleText("Odaberite datum").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        tilBirthDate = findViewById(R.id.tilBirthDate);
        tilBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cal.show(getSupportFragmentManager(), "Material_Date_Picker");
                Cal.addOnPositiveButtonClickListener(selection -> {
                    tilBirthDate.setText(Cal.getHeaderText());
                });
            }
        });

        Button btnNext = findViewById(R.id.btnStudentInfoActivity);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText please = findViewById(R.id.tilIme);
                Ime = please.getText().toString();

                TextInputEditText tiPrezime = findViewById(R.id.tilPrezime);
                Prezime = tiPrezime.getText().toString();

                TextInputEditText tiDate = findViewById(R.id.tilBirthDate);
                DatumRodenja = tiDate.getText().toString();

                if (Ime.isEmpty() || Prezime.isEmpty() || DatumRodenja.isEmpty()){
                    Toast.makeText(PersonalInfoActivity.this, "Value is Empty", Toast.LENGTH_SHORT).show();
                } else if (Ime.matches(".*\\d.*") || Prezime.matches(".*\\d.*")){
                    Toast.makeText(PersonalInfoActivity.this, "Ime sadrži broj", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent secondActivity = new Intent(getApplicationContext(), StudentInfoActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("Ime", Ime);
                    bundle.putString("Prezime", Prezime);
                    bundle.putString("DatumRodenja", DatumRodenja);

                    secondActivity.putExtras(bundle);
                    startActivity(secondActivity);
                    finish();
                }
            }
        });
    }
}