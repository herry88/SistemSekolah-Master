package com.tito.cyber.sistemsekolah.ui.guru.item;

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
import com.tito.cyber.sistemsekolah.json.jadwalajar.adapter.JadwalAjarAdapter;
import com.tito.cyber.sistemsekolah.json.jadwalajar.model.JadwalAjar;
import com.tito.cyber.sistemsekolah.json.jadwalajar.parser.JadwalAjarJSONParser;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;

import java.util.List;

/**
 * Created by cyber on 02/11/16.
 */

public class JadwalajarGuruActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    List<JadwalAjar> jadwalList;
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
        requestData(Config.JADWAL_GURU+tvNip.getText().toString().trim());
        pdModel.pdMenyiapkanDataJadwal(JadwalajarGuruActivity.this);

    }
    public void requestData(String uri) {

        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        jadwalList = JadwalAjarJSONParser.parseData(response);
                        JadwalAjarAdapter adapter = new JadwalAjarAdapter(JadwalajarGuruActivity.this, jadwalList);
                        lv.setAdapter(adapter);
                        pdModel.hideProgressDialog();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(JadwalajarGuruActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        pdModel.hideProgressDialog();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, JaDetailActivity.class);
        JadwalAjar jadwal = jadwalList.get(i);
        intent.putExtra(Config.TAG_MAPEL, jadwal.getMapel());
        intent.putExtra(Config.TAG_KELAS, jadwal.getKelas());
        intent.putExtra(Config.TAG_SUB_KELAS, jadwal.getSub_kelas());
        intent.putExtra(Config.TAG_HARI,jadwal.getHari());
        intent.putExtra(Config.TAG_JAM_MULAI,jadwal.getJam_mulai());
        intent.putExtra(Config.TAG_JAM_SELESAI,jadwal.getJam_selesai());

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
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Config.SERVER_API+"cetak_jadwal_guru.php?nip="+tvNip.getText().toString().trim())));
                return true;
            case R.id.action_muat:
                requestData(Config.JADWAL_GURU+tvNip.getText().toString().trim());
                return true;
        }
    }
}
