package com.example.inputdataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nik, nama, tempatlahir, tanggallahir, alamat;
    RadioButton jenisKelamin, statusPerkawinan;
    RadioGroup gender, status;
    Spinner pekerjaan;
    TextView spinnerValue;

    private Calendar calendar;
    private DatePicker datePicker;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Text Input
        nik = (EditText) findViewById(R.id.edit_nik);
        nama = (EditText) findViewById(R.id.edit_nama);
        tempatlahir = (EditText) findViewById(R.id.edit_tempat_lahir);
        tanggallahir = (EditText) findViewById(R.id.edit_tanggal_lahir);
        tanggallahir.addTextChangedListener(new TextWatcher() {

            private String current = "";
            private String ddmmyyyy ="DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count){
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]","");
                    String cleanC = current.replaceAll("[^\\d.]","");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6;i +=2) {
                        sel++;
                    }

                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() <8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else {
                        int day = Integer.parseInt(clean.substring(0,2));
                        int mon = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))?
                                cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day,mon,year);
                    }
                        clean = String.format("%s-%s-%s", clean.substring(0, 2),
                                clean.substring(2, 4),
                                clean.substring(4, 8));

                        sel = sel < 0 ? 0 : sel;
                        current = clean;
                        tanggallahir.setText(current);
                        tanggallahir.setSelection(sel < current.length()? sel : current.length());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        alamat = (EditText) findViewById(R.id.edit_alamat);

        //Radio Button
        gender = (RadioGroup) findViewById(R.id.jenis_kelamin);
        status = (RadioGroup) findViewById(R.id.status_perkawinan);

        //Spinner Pekerjaan
        pekerjaan = (Spinner) findViewById(R.id.pekerjaan);
        spinnerValue = (TextView) findViewById(R.id.view_pekerjaan);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pekerjaan, android.R.layout.simple_spinner_dropdown_item);
        pekerjaan.setAdapter(adapter);
        pekerjaan.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String pekerjaanSaya = adapterView.getItemAtPosition(i).toString();
        spinnerValue.setText(String.valueOf(pekerjaanSaya));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void submitregis(View view) {

        int radioId = gender.getCheckedRadioButtonId();
        jenisKelamin = (RadioButton) findViewById(radioId);
        int radioId1 = status.getCheckedRadioButtonId();
        statusPerkawinan = (RadioButton) findViewById(radioId1);


        if (!nik.getText().toString().equals("") &&
                !nama.getText().toString().equals("") &&
                !tempatlahir.getText().toString().equals("") &&
                !tanggallahir.getText().toString().equals("") &&
                !alamat.getText().toString().equals("") &&
                !jenisKelamin.getText().toString().equals("") &&
                !spinnerValue.getText().toString().equals("") &&
                !statusPerkawinan.getText().toString().equals("")) {
                Intent hubung = new Intent(this, HasilActivity.class);
                hubung.putExtra("nik", nik.getText().toString());
                hubung.putExtra("nama", nama.getText().toString());
                hubung.putExtra("tempatlahir", tempatlahir.getText().toString());
                hubung.putExtra("tanggallahir", tanggallahir.getText().toString());
                hubung.putExtra("alamat", alamat.getText().toString());
                hubung.putExtra("jenisKelamin", jenisKelamin.getText().toString());
                hubung.putExtra("pekerjaan", spinnerValue.getText().toString());
                hubung.putExtra("statusPerkawinan", statusPerkawinan.getText().toString());
                startActivity(hubung);
        }else {
            String msg = "Mohon Mengisi Semua Data!";
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }

    }
}