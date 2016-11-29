package com.tito.cyber.sistemsekolah.ui.guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;
import com.tito.cyber.sistemsekolah.json.siswa.adapter.SiswaAdapter;
import com.tito.cyber.sistemsekolah.json.siswa.model.Siswa;
import com.tito.cyber.sistemsekolah.json.siswa.parser.SiswaJSONParser;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;
import com.tito.cyber.sistemsekolah.ui.guru.item.InputNilaiActivity;

import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class PilihAbsensiActivity  extends AppCompatActivity implements ListView.OnItemClickListener  {
    List<Siswa> siswaList;
    ListView lv;
    TextView tvNip;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Intent intent = getIntent();
        lv = (ListView) findViewById(R.id.myList);
        lv.setOnItemClickListener(this);
        tvNip=(TextView)findViewById(R.id.tvId);
        tvNip.setText(intent.getStringExtra(Config.NIP));
        requestData(Config.PILIH_SISWA+tvNip.getText().toString().trim());
        pdModel.pdMenyiapkanDataJadwal(PilihAbsensiActivity.this);

    }
    public void requestData(String uri) {

        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        siswaList = SiswaJSONParser.parseData(response);
                        SiswaAdapter adapter = new SiswaAdapter(PilihAbsensiActivity.this, siswaList);
                        lv.setAdapter(adapter);
                        pdModel.hideProgressDialog();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(PilihAbsensiActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        pdModel.hideProgressDialog();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, AbsensiActivity.class);
        Siswa siswa = siswaList.get(i);
        intent.putExtra(Config.TAG_NIS, siswa.getNis());
        intent.putExtra(Config.TAG_NAMA_SISWA, siswa.getNama_siswa());
        intent.putExtra(Config.TAG_KODE_MAPEL, siswa.getKode_mapel());
        intent.putExtra(Config.TAG_MAPEL,siswa.getMapel());
        intent.putExtra(Config.TAG_KELAS,siswa.getKelas());
        intent.putExtra(Config.TAG_SUB_KELAS,siswa.getSub_kelas());
        intent.putExtra(Config.NIP,tvNip.getText().toString());
        startActivity(intent);


    }

}
