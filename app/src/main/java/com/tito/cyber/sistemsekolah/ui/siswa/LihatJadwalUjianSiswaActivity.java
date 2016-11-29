package com.tito.cyber.sistemsekolah.ui.siswa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
import com.tito.cyber.sistemsekolah.json.ujian.adapter.UjianAdapter;
import com.tito.cyber.sistemsekolah.json.ujian.model.Ujian;
import com.tito.cyber.sistemsekolah.json.ujian.parsing.UjianJSONParser;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;

import java.util.List;

/**
 * Created by cyber on 06/11/16.
 */

public class LihatJadwalUjianSiswaActivity  extends AppCompatActivity implements ListView.OnItemClickListener {
    List<Ujian> ujianList;
    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Intent intent = getIntent();
        lv = (ListView) findViewById(R.id.myList);
        lv.setOnItemClickListener(this);
        requestData(Config.UJIAN_SISWA);
        pdModel.pdMenyiapkanDataJadwal(LihatJadwalUjianSiswaActivity.this);

    }
    public void requestData(String uri) {

        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ujianList = UjianJSONParser.parseData(response);
                        UjianAdapter adapter = new UjianAdapter(LihatJadwalUjianSiswaActivity.this, ujianList);
                        lv.setAdapter(adapter);
                        pdModel.hideProgressDialog();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LihatJadwalUjianSiswaActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        pdModel.hideProgressDialog();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetailUjianActivity.class);
        Ujian ujian = ujianList.get(i);
        intent.putExtra(Config.TAG_MAPEL, ujian.getMapel());
        intent.putExtra(Config.TAG_KELAS, ujian.getKelas());
        intent.putExtra(Config.TAG_SUB_KELAS, ujian.getSub_kelas());
        intent.putExtra(Config.TAG_TGL,ujian.getTgl_ujian());
        intent.putExtra(Config.TAG_JAM_MULAI,ujian.getJam_mulai());
        intent.putExtra(Config.TAG_JAM_SELESAI,ujian.getJam_selesai());
        intent.putExtra(Config.TAG_KET,ujian.getKet());

        startActivity(intent);


    }

    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.print, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        switch (paramMenuItem.getItemId()) {
            default:
                return super.onOptionsItemSelected(paramMenuItem);
            case R.id.action_print:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Config.SERVER_API+"cetak_jadwal_ujian.php")));
                return true;
            case R.id.action_muat:
                requestData(Config.UJIAN_SISWA);
                return true;
        }
    }

}
