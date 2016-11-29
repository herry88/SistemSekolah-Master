package com.tito.cyber.sistemsekolah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gc.materialdesign.views.ButtonRectangle;
import com.tito.cyber.sistemsekolah.config.Config;
import com.tito.cyber.sistemsekolah.pdModel.pdModel;
import com.tito.cyber.sistemsekolah.toastModel.ToastModel;

import com.tito.cyber.sistemsekolah.ui.guru.GuruHomeActivity;
import com.tito.cyber.sistemsekolah.ui.siswa.SiswaHomeActivity;
import com.tito.cyber.sistemsekolah.ui.walimurid.WalimuridHomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Spinner sphakakses;
    ButtonRectangle btnLogin;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (ButtonRectangle) findViewById(R.id.btnLogin);
        edtUsername =(EditText)findViewById(R.id.edtusername);
        edtPassword=(EditText)findViewById(R.id.edtpassword);
        sphakakses = (Spinner) findViewById(R.id.sphakakses);
        hakakses();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String hk = sphakakses.getSelectedItem().toString();
                if (hk.equals("Guru")) {
                    loginGuru();
                } else if (hk.equals("Walimurid")) {
                    loginWalimurid();
                } else if (hk.equals("Siswa")) {
                    loginSiswa();
                }
            }
        });
    }
    private void hakakses() {
        List<String> list = new ArrayList<String>();
        list.add("Guru");
        list.add("Walimurid");
        list.add("Siswa");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        sphakakses.setAdapter(dataAdapter);
    }

    private void loginWalimurid() {
        pdModel.pdLogin(MainActivity.this);
        final String nio = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL_WALIMURID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Config.LOGIN_SUCCESS)) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.USERNAME_SHARED_PREF_WALIMURID, nio);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, WalimuridHomeActivity.class);
                            pdModel.hideProgressDialog();
                            intent.putExtra(Config.NIO, edtUsername.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            ToastModel.ToashGagalLoginR(MainActivity.this);
                            pdModel.hideProgressDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Config.NIO, nio);
                params.put(Config.KEY_PASS, password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loginGuru() {
        pdModel.pdLogin(MainActivity.this);
        final String nip = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL_GURU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Config.LOGIN_SUCCESS)) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.USERNAME_SHARED_PREF_GURU, nip);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, GuruHomeActivity.class);
                            pdModel.hideProgressDialog();
                            intent.putExtra(Config.NIP, edtUsername.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            ToastModel.ToashGagalLoginR(MainActivity.this);
                            pdModel.hideProgressDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Config.NIP, nip);
                params.put(Config.KEY_PASS, password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loginSiswa() {
        pdModel.pdLogin(MainActivity.this);
        final String nis = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL_SISWA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Config.LOGIN_SUCCESS)) {
                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.USERNAME_SHARED_PREF_SISWA, nis);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, SiswaHomeActivity.class);
                            pdModel.hideProgressDialog();
                            intent.putExtra(Config.NIS, edtUsername.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            ToastModel.ToashGagalLoginR(MainActivity.this);
                            pdModel.hideProgressDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Config.NIS, nis);
                params.put(Config.KEY_PASS, password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
