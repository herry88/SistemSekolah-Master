package com.tito.cyber.sistemsekolah.pdModel;

import android.app.ProgressDialog;
import android.content.Context;

import com.tito.cyber.sistemsekolah.R;

/**
 * Created by cyber on 22/09/16.
 */

public class pdModel {

    static ProgressDialog progressDialog;
    public static void pdLogin(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Mengolah proses login....");
        progressDialog.setTitle("Silahkan Tunggu");
        progressDialog.show();
    }
    public static void pdMenyiapkanDataJadwal(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Menyiapkan data jadwal...");
        progressDialog.setTitle("Silahkan Tunggu");
        progressDialog.show();

    }
    public static void pdPengumuman(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Menyiapkan data pengumuman...");
        progressDialog.setTitle("Silahkan Tunggu");
        progressDialog.show();

    }
    public static void pdMenyiapkanDataLOGIN(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Menyiapkan Data....");
        progressDialog.setTitle("Silahkan Tunggu");
        progressDialog.show();

    }


    public static void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
