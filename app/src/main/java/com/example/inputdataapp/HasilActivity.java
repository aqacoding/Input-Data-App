package com.example.inputdataapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HasilActivity extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        txt1 = (TextView) findViewById(R.id.nikUser);
        txt2 = (TextView) findViewById(R.id.namaUser);
        txt3 = (TextView) findViewById(R.id.tempatLahirUser);
        txt4 = (TextView) findViewById(R.id.tanggalLahirUser);
        txt5 = (TextView) findViewById(R.id.alamatUser);
        txt6 = (TextView) findViewById(R.id.genderUser);
        txt7 = (TextView) findViewById(R.id.pekerjaanUser);
        txt8 = (TextView) findViewById(R.id.statusUser);

        String nik = getIntent().getExtras().getString("nik");
        String nama = getIntent().getExtras().getString("nama");
        String tempatlahir = getIntent().getExtras().getString("tempatlahir");
        String tanggallahir = getIntent().getExtras().getString("tanggallahir");
        String alamat = getIntent().getExtras().getString("alamat");
        String jenisKelamin = getIntent().getExtras().getString("jenisKelamin");
        String pekerjaan = getIntent().getExtras().getString("pekerjaan");
        String statusPerkawinan = getIntent().getExtras().getString("statusPerkawinan");

        txt1.setText("NIK:\n" +nik);
        txt2.setText("Nama:\n" +nama);
        txt3.setText("Tempat Lahir:\n" +tempatlahir);
        txt4.setText("Tanggal Lahir:\n" +tanggallahir);
        txt5.setText("Alamat:\n" +alamat);
        txt6.setText("Jenis Kelamin:\n" +jenisKelamin);
        txt7.setText("Pekerjaan:\n" +pekerjaan);
        txt8.setText("Status Perkawinan:\n" +statusPerkawinan);
    }
}
