package com.tito.cyber.sistemsekolah.ui.pengumuman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;
import com.tito.cyber.sistemsekolah.json.pengumuman.adapter.Pengumumanadapter;
import com.tito.cyber.sistemsekolah.json.pengumuman.model.Pengumuman;
import com.tito.cyber.sistemsekolah.json.pengumuman.parsing.PengumumanJSONParser;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;

import java.util.List;

/**
 * Created by cyber on 02/11/16.
 */

public class PengumumanActivity   extends AppCompatActivity implements ListView.OnItemClickListener {
    List<Pengumuman> pList;
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = (ListView) findViewById(R.id.myList);
        lv.setOnItemClickListener(this);
        requestData(Config.PENGUMUMAN);
        pdModel.pdPengumuman(PengumumanActivity.this);

    }
    public void requestData(String uri) {

        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pList = PengumumanJSONParser.parseData(response);
                        Pengumumanadapter adapter = new Pengumumanadapter(PengumumanActivity.this, pList);
                        lv.setAdapter(adapter);
                        pdModel.hideProgressDialog();

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(PengumumanActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        pdModel.hideProgressDialog();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetailPengumumanActivity.class);
        Pengumuman p = pList.get(i);
        intent.putExtra(Config.TAG_MAPEL, p.getJudul());
        intent.putExtra(Config.TAG_KELAS, p.getDetail());
        intent.putExtra(Config.TAG_SUB_KELAS, p.getTgl());
        intent.putExtra(Config.TAG_TGL,p.getImage());

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
            case R.id.action_muat:
                requestData(Config.PENGUMUMAN);
                return true;
        }
    }


}
