package com.tito.cyber.sistemsekolah.toastModel;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by cyber on 22/09/16.
 */

public class ToastModel {
    static Context context;
    public static void ToashDataTersimpan(Context c){
        Toast.makeText(c.getApplicationContext(),"Data sudah terkirim....",Toast.LENGTH_SHORT).show();
    }
    public static void ToashDataGagalTersimpan(Context c){
        Toast.makeText(c.getApplicationContext(),"Data belum terkirim ,Silahkan coba lagi...",Toast.LENGTH_SHORT).show();
    }
    public static void ToashGagalLoginR(Context c){
        Toast.makeText(c.getApplicationContext(),"Email Atau Password Salah",Toast.LENGTH_SHORT).show();
    }

}
