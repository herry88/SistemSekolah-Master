package com.tito.cyber.sistemsekolah.ui.walimurid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tito.cyber.sistemsekolah.CarapengunaanActivity;
import com.tito.cyber.sistemsekolah.ProfilpengembangActivity;
import com.tito.cyber.sistemsekolah.R;
import com.tito.cyber.sistemsekolah.config.Config;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;
import com.tito.cyber.sistemsekolah.ui.pengumuman.PengumumanActivity;
import com.tito.cyber.sistemsekolah.ui.siswa.LihatAbsensiActivity;
import com.tito.cyber.sistemsekolah.ui.siswa.LihatJadwalSiswaActivity;
import com.tito.cyber.sistemsekolah.ui.siswa.LihatNilaiSiswaActivity;
import com.tito.cyber.sistemsekolah.ui.siswa.SiswaHomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cyber on 01/11/16.
 */

public class WalimuridHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvNio, tvnama,tvnis;
    public String nis_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_walimurid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        tvnis=(TextView)findViewById(R.id.tvNissiswa);
        tvNio = (TextView) findViewById(R.id.tvNioortu);
        tvnama = (TextView) findViewById(R.id.tvNamaortu);
        tvNio.setText(intent.getStringExtra(Config.NIO));
        nis_ = tvNio.getText().toString();
        ambilData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hubungi TU Jika Terjadi Kesalahan", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void ambilData() {
        pdModel.pdMenyiapkanDataLOGIN(WalimuridHomeActivity.this);
        String url = Config.AMBIL_DATA_WALIMURID + tvNio.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
                pdModel.hideProgressDialog();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WalimuridHomeActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        pdModel.hideProgressDialog();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String nama = "";
        String nis="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("profil");
            JSONObject collegeData = result.getJSONObject(0);
            nama = collegeData.getString("nama_ortu");
            nis = collegeData.getString("nis");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvnama.setText(nama);
        tvnis.setText(nis);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_main, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        switch (paramMenuItem.getItemId()) {
            default:
                return super.onOptionsItemSelected(paramMenuItem);
            case R.id.action_petunjuk:
                startActivity(new Intent(this, CarapengunaanActivity.class));
                return true;
            case R.id.action_profil:
                startActivity(new Intent(this, ProfilpengembangActivity.class));
                return true;
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_nilaisiswa) {

            Intent intent = new Intent(WalimuridHomeActivity.this,LihatNilaiSiswaActivity.class);
            intent.putExtra(Config.NIS, tvnis.getText().toString());
            startActivity(intent);
        } else if (id == R.id.nav_recordabsensi) {

            Intent intent = new Intent(WalimuridHomeActivity.this,LihatAbsensiActivity.class);
            intent.putExtra(Config.NIS, tvnis.getText().toString());
            startActivity(intent);
        } else if (id == R.id.nav_pengumuman) {
            Intent intent = new Intent(WalimuridHomeActivity.this, PengumumanActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
