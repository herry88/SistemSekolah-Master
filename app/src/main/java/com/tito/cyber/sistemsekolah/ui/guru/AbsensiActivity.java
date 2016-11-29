package com.tito.cyber.sistemsekolah.ui.guru;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ButtonRectangle;
import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;
import com.tito.cyber.sistemsekolah.toastModel.ToastModel;
import com.tito.cyber.sistemsekolah.ui.guru.item.InputNilaiActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cyber on 07/11/16.
 */

public class AbsensiActivity extends AppCompatActivity {
    TextView tvnip,tvnis,tvnama_siswa,tvkodemapel,tvmapel,tvkelas,tvsub_kelas;
   // EditText edtntugas,edtnuh,edtnuts,edtnuas,edtnakhir;
    RequestQueue requestQueue;
    String nip,nis,kode_mapel,status,ket;
    ButtonRectangle btnHadir,btnIzin,btnSakit,btnAlpha;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        tvnip=(TextView)findViewById(R.id.tvNip);
        btnHadir=(ButtonRectangle)findViewById(R.id.btnHadir);
        btnIzin=(ButtonRectangle)findViewById(R.id.btnIjin);
        btnSakit=(ButtonRectangle)findViewById(R.id.btnSakit);
        btnAlpha=(ButtonRectangle)findViewById(R.id.btnAlpha);

        tvnis=(TextView)findViewById(R.id.tvNis);
        tvnama_siswa=(TextView)findViewById(R.id.tvNamasiswa);
        tvkodemapel=(TextView)findViewById(R.id.tvKodemapel);
        tvmapel=(TextView)findViewById(R.id.tvMapel);
        tvkelas=(TextView)findViewById(R.id.tvKelas);
        tvsub_kelas=(TextView)findViewById(R.id.tvSubkelas);
        Intent intent = getIntent();
        tvnip.setText(intent.getStringExtra(Config.NIP));
        tvnis.setText(""+intent.getStringExtra(Config.TAG_NIS));
        tvnama_siswa.setText(""+intent.getStringExtra(Config.TAG_NAMA_SISWA));
        tvkodemapel.setText(""+intent.getStringExtra(Config.TAG_KODE_MAPEL));
        tvmapel.setText(""+intent.getStringExtra(Config.TAG_MAPEL));
        tvkelas.setText(""+intent.getStringExtra(Config.TAG_KELAS));
        tvsub_kelas.setText(""+intent.getStringExtra(Config.TAG_SUB_KELAS));

        btnHadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHadir();
            }
        });
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertAlpha();
            }
        });
        btnSakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertSakit();
            }
        });
        btnIzin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertIzin();
            }
        });
    }
    private void insertHadir(){
        nip = tvnip.getText().toString();
        nis = tvnis.getText().toString();
        kode_mapel = tvkodemapel.getText().toString();
        status = "Hadir";


        StringRequest request = new StringRequest(Request.Method.POST, Config.INSERT_ABSENSI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
                ToastModel.ToashDataTersimpan(AbsensiActivity.this);
                tvnip.setText("");
                tvnis.setText("");
                tvkodemapel.setText("");
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastModel.ToashDataGagalTersimpan(AbsensiActivity.this);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nip", nip);
                parameters.put("nis", nis);
                parameters.put("kode_mapel", kode_mapel);
                parameters.put("status", status);

                return parameters;
            }

        };
        requestQueue.add(request);





    }
    private void insertAlpha(){
        nip = tvnip.getText().toString();
        nis = tvnis.getText().toString();
        status = "Alpha";

        StringRequest request = new StringRequest(Request.Method.POST, Config.INSERT_ABSENSI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
                ToastModel.ToashDataTersimpan(AbsensiActivity.this);
                tvnip.setText("");
                tvnis.setText("");
                tvkodemapel.setText("");
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastModel.ToashDataGagalTersimpan(AbsensiActivity.this);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nip", nip);
                parameters.put("nis", nis);
                parameters.put("kode_mapel", kode_mapel);
                parameters.put("status", status);

                return parameters;
            }

        };
        requestQueue.add(request);





    }
    private void insertIzin(){
        nip = tvnip.getText().toString();
        nis = tvnis.getText().toString();
        status = "Izin";

        StringRequest request = new StringRequest(Request.Method.POST, Config.INSERT_ABSENSI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
                ToastModel.ToashDataTersimpan(AbsensiActivity.this);
                tvnip.setText("");
                tvnis.setText("");
                tvkodemapel.setText("");
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastModel.ToashDataGagalTersimpan(AbsensiActivity.this);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nip", nip);
                parameters.put("nis", nis);
                parameters.put("kode_mapel", kode_mapel);
                parameters.put("status", status);

                return parameters;
            }

        };
        requestQueue.add(request);





    }
    private void insertSakit(){
        nip = tvnip.getText().toString();
        nis = tvnis.getText().toString();
        status = "Sakit";

        StringRequest request = new StringRequest(Request.Method.POST, Config.INSERT_ABSENSI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
                ToastModel.ToashDataTersimpan(AbsensiActivity.this);
                tvnip.setText("");
                tvnis.setText("");
                tvkodemapel.setText("");
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastModel.ToashDataGagalTersimpan(AbsensiActivity.this);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nip", nip);
                parameters.put("nis", nis);
                parameters.put("kode_mapel", kode_mapel);
                parameters.put("status", status);

                return parameters;
            }

        };
        requestQueue.add(request);





    }

}
