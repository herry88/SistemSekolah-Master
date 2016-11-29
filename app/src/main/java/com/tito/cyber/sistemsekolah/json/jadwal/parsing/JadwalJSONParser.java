package com.tito.cyber.sistemsekolah.json.jadwal.parsing;

import com.tito.cyber.sistemsekolah.json.jadwal.model.Jadwal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyber on 02/11/16.
 */

public class JadwalJSONParser {
    static List<Jadwal> jadwalList;

    public static List<Jadwal> parseData(String content) {

        JSONArray jadwal_arry = null;
        Jadwal jadwal = null;
        try {

            jadwal_arry = new JSONArray(content);
            jadwalList = new ArrayList<>();

            for (int i = 0; i < jadwal_arry.length(); i++) {

                JSONObject obj = jadwal_arry.getJSONObject(i);
                jadwal = new Jadwal();
                jadwal.setId_jadwal(obj.getString("id_jadwal"));
                jadwal.setNama_guru(obj.getString("nama_guru"));
                jadwal.setKelas(obj.getString("kelas"));
                jadwal.setSub_kelas(obj.getString("sub_kelas"));
                jadwal.setMapel(obj.getString("mapel"));
                jadwal.setHari(obj.getString("hari"));
                jadwal.setJam_mulai(obj.getString("jam_mulai"));
                jadwal.setJam_selesai(obj.getString("jam_selesai"));
                jadwal.setImage(obj.getString("img_jadwal"));

                jadwalList.add(jadwal);
            }
            return jadwalList;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
