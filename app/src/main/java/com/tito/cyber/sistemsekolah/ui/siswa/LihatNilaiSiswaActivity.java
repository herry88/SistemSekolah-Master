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
import com.tito.cyber.sistemsekolah.json.nilai.adapter.NilaiAdapter;
import com.tito.cyber.sistemsekolah.json.nilai.model.Nilai;
import com.tito.cyber.sistemsekolah.json.nilai.parsing.NilaiJSONParser;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;

import java.util.List;

/**
 * Created by cyber on 06/11/16.
 */

public class LihatNilaiSiswaActivity extends AppCompatActivity implements ListView.OnItemClickListener {
        List<Nilai> nilaiList;
        ListView lv;
        TextView tvNis;
@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
final Intent intent = getIntent();
        lv = (ListView) findViewById(R.id.myList);
        lv.setOnItemClickListener(this);
        tvNis=(TextView)findViewById(R.id.tvId);
        tvNis.setText(intent.getStringExtra(Config.NIS));
        requestData(Config.NILAI_SISWA+tvNis.getText().toString().trim());
        pdModel.pdMenyiapkanDataJadwal(LihatNilaiSiswaActivity.this);

        }
public void requestData(String uri) {

        StringRequest request = new StringRequest(uri,

        new Response.Listener<String>() {
@Override
public void onResponse(String response) {
        nilaiList = NilaiJSONParser.parseData(response);
        NilaiAdapter adapter = new NilaiAdapter(LihatNilaiSiswaActivity.this, nilaiList);
        lv.setAdapter(adapter);
        pdModel.hideProgressDialog();

        }
        },

        new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {

        Toast.makeText(LihatNilaiSiswaActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
        pdModel.hideProgressDialog();
        }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        }



@Override
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetailNilaiActivity.class);
        Nilai nilai = nilaiList.get(i);
        intent.putExtra(Config.TAG_MAPEL, nilai.getMapel());
        intent.putExtra(Config.TAG_N_TUGAS, nilai.getNilai_tugas());
        intent.putExtra(Config.TAG_N_UH, nilai.getNilai_uh());
        intent.putExtra(Config.TAG_N_UTS, nilai.getNilai_uts());
        intent.putExtra(Config.TAG_N_UAS,nilai.getNilai_uas());
        intent.putExtra(Config.TAG_N_AKHIR,nilai.getNilai_akhir());


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
                                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Config.SERVER_API+"cetak_nilai.php?nis="+tvNis.getText().toString().trim())));
                                return true;
                        case R.id.action_muat:
                                requestData(Config.NILAI_SISWA+tvNis.getText().toString().trim());
                                return true;
                }
        }

}
