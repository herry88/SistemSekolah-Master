package com.tito.cyber.sistemsekolah.ui.guru.item;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cyber on 02/11/16.
 */

public class InputNilaiActivity extends AppCompatActivity {
TextView tvnip,tvnis,tvnama_siswa,tvkodemapel,tvmapel,tvkelas,tvsub_kelas;
EditText edtntugas,edtnuh,edtnuts,edtnuas,edtnakhir;
    RequestQueue requestQueue;
    String nip,nis,kode_mapel,nilai_tugas,nilai_uh,nilai_uts,nilai_uas,nilai_akhir;
    ButtonRectangle btnSimpan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputnilai);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        tvnip=(TextView)findViewById(R.id.tvNip);
        btnSimpan=(ButtonRectangle)findViewById(R.id.btnSimpannilai);
        tvnis=(TextView)findViewById(R.id.tvNis);
        tvnama_siswa=(TextView)findViewById(R.id.tvNamasiswa);
        tvkodemapel=(TextView)findViewById(R.id.tvKodemapel);
        tvmapel=(TextView)findViewById(R.id.tvMapel);
        tvkelas=(TextView)findViewById(R.id.tvKelas);
        tvsub_kelas=(TextView)findViewById(R.id.tvSubkelas);
        edtntugas=(EditText)findViewById(R.id.edtNtugas);
        edtnuh=(EditText)findViewById(R.id.edtNuh);
        edtnuts=(EditText)findViewById(R.id.edtNuts);
        edtnuas=(EditText)findViewById(R.id.edtNuas);
        Intent intent = getIntent();
        tvnip.setText(intent.getStringExtra(Config.NIP));
        tvnis.setText(""+intent.getStringExtra(Config.TAG_NIS));
        tvnama_siswa.setText(""+intent.getStringExtra(Config.TAG_NAMA_SISWA));
        tvkodemapel.setText(""+intent.getStringExtra(Config.TAG_KODE_MAPEL));
        tvmapel.setText(""+intent.getStringExtra(Config.TAG_MAPEL));
        tvkelas.setText(""+intent.getStringExtra(Config.TAG_KELAS));
        tvsub_kelas.setText(""+intent.getStringExtra(Config.TAG_SUB_KELAS));

btnSimpan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        insertNilai();
    }
});
    }
    private void insertNilai(){
final int akhir,na;

        nip = tvnip.getText().toString();
        nis = tvnis.getText().toString();
        kode_mapel = tvkodemapel.getText().toString();
        nilai_tugas = edtntugas.getText().toString();
        nilai_uh = edtnuh.getText().toString();
        nilai_uts = edtnuts.getText().toString();
        nilai_uas = edtnuas.getText().toString();

akhir=Integer.parseInt(nilai_tugas)+Integer.parseInt(nilai_uh)+Integer.parseInt(nilai_uts)+Integer.parseInt(nilai_uas);
        na=akhir/4;
        StringRequest request = new StringRequest(Request.Method.POST, Config.INSERT_NILAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(response.toString());
                ToastModel.ToashDataTersimpan(InputNilaiActivity.this);
            tvnip.setText("");
                tvnis.setText("");
                tvkodemapel.setText("");
                edtntugas.setText("");
                edtnuh.setText("");
                edtnuts.setText("");
                edtnuas.setText("");
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastModel.ToashDataGagalTersimpan(InputNilaiActivity.this);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("nip", nip);
                parameters.put("nis", nis);
                parameters.put("kode_mapel", kode_mapel);
                parameters.put("nilai_uh", nilai_uh);
                parameters.put("nilai_tugas", nilai_tugas);
                parameters.put("nilai_uts", nilai_uts);
                parameters.put("nilai_uas", nilai_uas);
                parameters.put("nilai_akhir", String.valueOf(na));
                parameters.put("img_nilai", "ic_nilai.png");

                return parameters;
            }

        };
        requestQueue.add(request);





    }
}
