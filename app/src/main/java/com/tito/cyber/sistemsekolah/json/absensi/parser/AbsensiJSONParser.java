package com.tito.cyber.sistemsekolah.json.absensi.parser;

import com.tito.cyber.sistemsekolah.json.absensi.model.Absensi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyber on 07/11/16.
 */

public class AbsensiJSONParser {
    static List<Absensi> absensiList;

    public static List<Absensi> parseData(String content) {

        JSONArray absensi_arry = null;
        Absensi absensi = null;
        try {

            absensi_arry = new JSONArray(content);
            absensiList = new ArrayList<>();

            for (int i = 0; i < absensi_arry.length(); i++) {

                JSONObject obj = absensi_arry.getJSONObject(i);
                absensi = new Absensi();
                absensi.setId_absensi(obj.getString("id_absensi"));
                absensi.setNis(obj.getString("nis"));
                absensi.setNama_siswa(obj.getString("nama_siswa"));
                absensi.setMapel(obj.getString("mapel"));
                absensi.setTgl_absensi(obj.getString("tgl_absensi"));
                absensi.setImage(obj.getString("img_siswa"));

                absensiList.add(absensi);
            }
            return absensiList;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


